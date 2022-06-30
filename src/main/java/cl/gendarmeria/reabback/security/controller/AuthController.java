package cl.gendarmeria.reabback.security.controller;

import cl.gendarmeria.reabback.dtos.Mensaje;
import cl.gendarmeria.reabback.security.dto.JwtDto;
import cl.gendarmeria.reabback.security.dto.LoginUsuario;
import cl.gendarmeria.reabback.security.entity.Usuario;
import cl.gendarmeria.reabback.security.jwt.JwtProvider;
import cl.gendarmeria.reabback.security.repository.RegistroLoginRepository;
import cl.gendarmeria.reabback.security.service.RolService;
import cl.gendarmeria.reabback.security.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    RolService rolService;
    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    private RegistroLoginRepository registroLoginRepository;
    private final String urlLdap = "http://test8.gendarmeria.gob.cl/ServiciosLDAP/ldapAuth/autenticar365?usuario=";

    private final ObjectMapper mapper = new ObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

/*
    //Inicio de sesión mediante call a LDAP con clave institucional
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        String pass = loginUsuario.getPassword();
        if (bindingResult.hasErrors()){
            return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        }else{
            if(usuarioService.existsByNombreUsuario(loginUsuario.getNombreUsuario())) {
                Usuario user = usuarioService.getByNombreUsuario(loginUsuario.getNombreUsuario()).get();
                if (user.isActivo()){
                    user.setPassword(passwordEncoder.encode(loginUsuario.getPassword()));
                    usuarioService.save(user);
                    String usuario;
                    String url = urlLdap + loginUsuario.getNombreUsuario() + "&pass=" + loginUsuario.getPassword();
                    UsuarioLDAP usuarioLDAP = new UsuarioLDAP();
                    try {
                        RestTemplate restTemplate = new RestTemplate();
                        String jsonSalida = restTemplate.getForObject(url, String.class);
                        usuarioLDAP = mapper.readValue(jsonSalida, new TypeReference<UsuarioLDAP>() {
                        });
                        if (usuarioLDAP.isSuccess()) {
                            usuario = usuarioLDAP.getData().getRut();
                            if (usuario != null) {

                                InetAddress ip = InetAddress.getLocalHost();
                                String ipS = ip.getHostAddress();
                                RegistroLogin registro = new RegistroLogin(new Date(), usuarioLDAP.getData().getUid(), ipS);
                                registroLoginRepository.save(registro);
                                LOGGER.info("Se registra LOGIN para el usuario: " + usuarioLDAP.getData().getUid());

                                Authentication authentication =
                                        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                                                (loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
                                SecurityContextHolder.getContext().setAuthentication(authentication);
                                String jwt = jwtProvider.generateToken(authentication);
                                JwtDto jwtDto = new JwtDto(jwt);
                                return new ResponseEntity<>(jwtDto, HttpStatus.OK);
                            }
                        }
                    } catch (JsonProcessingException | UnknownHostException e) {
                        e.printStackTrace();
                    }
                }else{
                return new ResponseEntity(new Mensaje("Usuario Inactivo"), HttpStatus.BAD_REQUEST);
            }
            }else{
                return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
            }
        }return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
    }*/


    @PostMapping("/refresh")
    public ResponseEntity<JwtDto> refreshToken(@RequestBody JwtDto jwtDto) throws ParseException {
        String token = jwtProvider.refreshToken(jwtDto);
        JwtDto jwt = new JwtDto(token);
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<JwtDto> loginOffline(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        System.out.println(loginUsuario.getNombreUsuario());
        String pass = loginUsuario.getPassword();
        if (bindingResult.hasErrors()){
            return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        }else{
            if(usuarioService.existsByNombreUsuario(loginUsuario.getNombreUsuario())) {
                Usuario user = usuarioService.getByNombreUsuario(loginUsuario.getNombreUsuario()).get();
                System.out.println("usuario existe");
                if (user.isActivo()) {
                    System.out.println("usuario activo");
                    user.setPassword(passwordEncoder.encode(loginUsuario.getPassword()));
                    usuarioService.save(user);
                    Authentication authentication =
                            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                                    (loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    String jwt = jwtProvider.generateToken(authentication);
                    JwtDto jwtDto = new JwtDto(jwt);
                    return new ResponseEntity<>(jwtDto, HttpStatus.OK);
                }else{
                    return new ResponseEntity(new Mensaje("Usuario inactivo"), HttpStatus.BAD_REQUEST);
                }
            }else{
                return new ResponseEntity(new Mensaje("Usuario o contraseña incorrectos"), HttpStatus.BAD_REQUEST);
            }
        }
    }



}
