package cl.gendarmeria.reabback.security.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class RegistroLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date fecha;
    private String usuario;
    private String isp;


    public RegistroLogin(Date fecha, String usuario, String isp) {
        this.fecha = fecha;
        this.usuario = usuario;
        this.isp = isp;
    }
}
