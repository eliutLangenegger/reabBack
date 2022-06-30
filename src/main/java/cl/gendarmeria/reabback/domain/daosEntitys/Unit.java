package cl.gendarmeria.reabback.domain.daosEntitys;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Christopher Langenegger
 * @version 1.0
 * @since 29-06-2022
 **/
@Entity
@Getter
@Table(name = "TG_UNIDADES", schema = "PERSONAL")
public class Unit {
    @Id
    private Integer codigo;
    private String descripcion;
    private Integer region;
}
