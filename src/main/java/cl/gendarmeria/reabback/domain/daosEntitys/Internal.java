package cl.gendarmeria.reabback.domain.daosEntitys;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Christopher Langenegger
 * @version 1.0
 * @since 28-06-2022
 **/
@Entity
@Getter
@Table(name = "CP_INT_POB_PENAL", schema = "GENCHI1")
public class Internal {
    @Id
    private String codIntPobPenal;
    private String rut;
    private String nombres;
    private String apellidos;
    private Integer sexo;
    private String nacionalidad;
    private String tipoSistema;
    private String estadoInterno;
    private String unidad;
    private Integer region;
    private Date F_H_INGRESO_DETENCION;
    private String lugarNac;
    private String comuna;
    private String direccion;
    private String destino;
}
