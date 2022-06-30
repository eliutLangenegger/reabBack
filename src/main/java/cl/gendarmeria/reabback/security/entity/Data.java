package cl.gendarmeria.reabback.security.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Data {
    private String uid;
    private String cn;
    private String rut;
    private String nombres;
    private String apellidos;
    private String mail;
    private String vigenteRRHH;
}
