package cl.gendarmeria.reabback.resources;

import cl.gendarmeria.reabback.domain.objects.CustomResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.OK;

/**
 * @author Christopher Langenegger
 * @version 1.0
 * @since 01-07-2022
 **/
@RestController
@RequestMapping("/ldap")
@CrossOrigin("*")
@AllArgsConstructor
public class LdapController {

    @GetMapping("busquedaInterno/{rut}")
    public ResponseEntity<CustomResponse> buscarInterno(@PathVariable("rut") String rut)  throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String url = "http://serviciosgenchi.gendarmeria.gob.cl/ServiciosInternos/internos/find/?rutInterno=" + rut + "&format=JSON&token=48774kIqw";
        Map<String,JSONObject> data = new HashMap<>();
        try {
            RestTemplate restTemplate = new RestTemplate();
            JSONObject json = restTemplate.getForObject(url, JSONObject.class);
            data.put("interno",json);
            return ResponseEntity.ok(
                    CustomResponse.builder()
                            .dateTime(now())
                            .data(data)
                            .message("Internal retrieved")
                            .status(OK)
                            .statusCode(OK.value())
                            .build()
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error en ejecución");
        }
    }

    @GetMapping("busquedaInternoNoVigente/{rut}")
    public ResponseEntity<CustomResponse> buscarInternoNoVigente(@PathVariable("rut") String rut)  throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String url = "http://serviciosgenchi.gendarmeria.gob.cl/ServiciosInternos/internos/findNoVigentes/?rutInterno="+rut+
                "&format=JSON&token=48774kIqw";
        Map<String,JSONObject> data = new HashMap<>();
        try {
            RestTemplate restTemplate = new RestTemplate();
            JSONObject json = restTemplate.getForObject(url, JSONObject.class);
            data.put("interno",json);
            return ResponseEntity.ok(
                    CustomResponse.builder()
                            .dateTime(now())
                            .data(data)
                            .message("Not Internal retrieved")
                            .status(OK)
                            .statusCode(OK.value())
                            .build()
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error en ejecución");
        }
    }

    @GetMapping("fotografiaInterno/{codigo}")
    public ResponseEntity<CustomResponse> fotografiaInterno(@PathVariable("codigo") String codigo)  throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String url = "http://serviciosgenchi.gendarmeria.gob.cl/ServiciosInternos/internos/getFotoB64ByCodigoInterno/?codigoInterno="
                +codigo+"&format=JSON&token=48774kIqw";
        Map<String,JSONObject> data = new HashMap<>();
        try {
            RestTemplate restTemplate = new RestTemplate();
            JSONObject json = restTemplate.getForObject(url, JSONObject.class);
            data.put("fotografia",json);
            return ResponseEntity.ok(
                    CustomResponse.builder()
                            .dateTime(now())
                            .data(data)
                            .message("Photo retrieved")
                            .status(OK)
                            .statusCode(OK.value())
                            .build()
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error en ejecución");
        }
    }

    @GetMapping("datosFuncionarios/{rut}")
    public ResponseEntity<CustomResponse> datosFuncionarios(@PathVariable("rut") String rut)  throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String url = "http://serviciosgenchi.gendarmeria.gob.cl/ServiciosPersonal/personal/find/?runFuncionario="
                +rut+"&format=JSON&token=48774kIqw";
        Map<String,JSONObject> data = new HashMap<>();
        try {
            RestTemplate restTemplate = new RestTemplate();
            JSONObject json = restTemplate.getForObject(url, JSONObject.class);
            data.put("funcionario",json);
            return ResponseEntity.ok(
                    CustomResponse.builder()
                            .dateTime(now())
                            .data(data)
                            .message("Lawyer retrieved")
                            .status(OK)
                            .statusCode(OK.value())
                            .build()
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error en ejecución");
        }
    }
}
