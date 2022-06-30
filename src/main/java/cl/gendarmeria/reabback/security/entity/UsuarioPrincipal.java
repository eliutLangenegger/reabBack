package cl.gendarmeria.reabback.security.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioPrincipal implements UserDetails {
    private String nombre;
    private String nombreUsuario;
    private String mail;
    private String password;
    private String unidad;
    private String cargo;
    private Collection<? extends GrantedAuthority> authorities;

    public UsuarioPrincipal(String nombre, String nombreUsuario, String mail, String password, Collection<? extends GrantedAuthority> authorities, String unidad, String cargo) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.mail = mail;
        this.password = password;
        this.authorities = authorities;
        this.unidad = unidad;
        this.cargo = cargo;
    }

    public static UsuarioPrincipal build(Usuario usuario){
        List<GrantedAuthority> authorities =
                usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol
                .getRolNombre().name())).collect(Collectors.toList());
        return new UsuarioPrincipal(usuario.getNombres(), usuario.getNombreUsuario(), usuario.getMail(),
                usuario.getPassword(), authorities, usuario.getUnidad(), usuario.getCargo());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nombreUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return mail;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
