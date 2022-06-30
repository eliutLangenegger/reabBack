package cl.gendarmeria.reabback.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author Christopher Langenegger
 * @version 1.0
 * @since 28-06-2022
 **/
@Getter @Setter @NoArgsConstructor
public class RecordDto {
    private long id;
    private String internals;
    private String unit;
    private int unitCode;
    private Date date;
    private Date out;

    //out
    private String lawyer;
    private String lawyerRun;
}
