package cl.gendarmeria.reabback.security.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioLDAP {
    private boolean success;
    private Data data;
}
