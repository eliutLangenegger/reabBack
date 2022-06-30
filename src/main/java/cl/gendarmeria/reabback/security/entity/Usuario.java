package cl.gendarmeria.reabback.security.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Usuario {
    @Id
    private String rut;
    @NotNull
    private String nombres;
    @NotNull
    private String apellidos;
    @NotNull
    @Column(unique = true)
    private String nombreUsuario;
    @NotNull
    private String cargo;
    @NotNull
    private String unidad;
    @NotNull
    private String mail;
    private String password;

    private boolean activo;
    private boolean autorizado;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();

    public Usuario(String nombres, String apellidos, String uid, String cargo, String unidad, String mail, String rut) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nombreUsuario = uid;
        this.cargo = cargo;
        this.unidad = unidad;
        this.mail = mail;
        this.rut = rut;
    }
}
