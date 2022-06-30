package cl.gendarmeria.reabback.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NuevoUsuario {
    @NotBlank
    private String nombres;
    @NotBlank
    private String apellidos;
    @NotBlank
    private String uid;
    @NotBlank
    private String cargo;
    @NotBlank
    private String departamento;
    @Email
    private String mail;
    @NotBlank
    private String rut;

    private Set<String> roles = new HashSet<>();



}
