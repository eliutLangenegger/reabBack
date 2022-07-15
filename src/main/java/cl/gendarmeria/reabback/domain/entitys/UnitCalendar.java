package cl.gendarmeria.reabback.domain.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Christopher Langenegger
 * @version 1.0
 * @since 01-07-2022
 **/
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitCalendar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer unitId;
    private String dependence;
    private Integer day;
    private Date hourInit;
    private Date hourFinish;
}
