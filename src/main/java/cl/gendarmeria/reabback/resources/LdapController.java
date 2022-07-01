package cl.gendarmeria.reabback.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
    public ResponseEntity<JSONObject> buscarInterno(@PathVariable("rut") String rut)  throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String url = "http://serviciosgenchi.gendarmeria.gob.cl/ServiciosInternos/internos/find/?rutInterno=" + rut + "&format=JSON&token=48774kIqw";
        String jsonSalida = "";
        try {
            RestTemplate restTemplate = new RestTemplate();
            JSONObject json = restTemplate.getForObject(url, JSONObject.class);
            return new ResponseEntity<>(json, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error en ejecución");
        }
    }

    @GetMapping("busquedaInternoNoVigente/{rut}")
    public ResponseEntity<JSONObject> buscarInternoNoVigente(@PathVariable("rut") String rut)  throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String url = "http://serviciosgenchi.gendarmeria.gob.cl/ServiciosInternos/internos/findNoVigentes/?rutInterno="+rut+
                "&format=JSON&token=48774kIqw";
        String jsonSalida = "";
        try {
            RestTemplate restTemplate = new RestTemplate();
            JSONObject json = restTemplate.getForObject(url, JSONObject.class);
            return new ResponseEntity<>(json, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error en ejecución");
        }
    }

    @GetMapping("fotografiaInterno/{codigo}")
    public ResponseEntity<JSONObject> fotografiaInterno(@PathVariable("codigo") String codigo)  throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String url = "http://serviciosgenchi.gendarmeria.gob.cl/ServiciosInternos/internos/getFotoB64ByCodigoInterno/?codigoInterno="
                +codigo+"&format=JSON&token=48774kIqw";
        String jsonSalida = "";
        try {
            RestTemplate restTemplate = new RestTemplate();
            JSONObject json = restTemplate.getForObject(url, JSONObject.class);
            return new ResponseEntity<>(json, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error en ejecución");
        }
    }

    @GetMapping("visitaInternos/{rut}")
    public ResponseEntity<JSONObject> visitaInternos(@PathVariable("rut") String rut)  throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String url = "http://serviciosgenchi.gendarmeria.gob.cl/ServiciosVisitas/visitas/find/?runVisita="
                +rut+"&format=JSON&token=48774kIqw";
        String jsonSalida = "";
        try {
            RestTemplate restTemplate = new RestTemplate();
            JSONObject json = restTemplate.getForObject(url, JSONObject.class);
            return new ResponseEntity<>(json, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error en ejecución");
        }
    }

    @GetMapping("datosFuncionarios/{rut}")
    public ResponseEntity<JSONObject> datosFuncionarios(@PathVariable("rut") String rut)  throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String url = "http://serviciosgenchi.gendarmeria.gob.cl/ServiciosPersonal/personal/find/?runFuncionario="
                +rut+"&format=JSON&token=48774kIqw";
        String jsonSalida = "";
        try {
            RestTemplate restTemplate = new RestTemplate();
            JSONObject json = restTemplate.getForObject(url, JSONObject.class);
            return new ResponseEntity<>(json, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error en ejecución");
        }
    }
}
