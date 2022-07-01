package cl.gendarmeria.reabback.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.Date;

/**
 * @author Christopher Langenegger
 * @version 1.0
 * @since 28-06-2022
 **/
@Getter
@Setter
@NoArgsConstructor
public class LawyerDto {
    //in
    private String run;
    private String name;
    private String surname;
    private Date expirationDate;
    private String type;
    private String upUser;
    private boolean active;
    //out
    private Date upDate;
    private Date lastVisit;
    private String lastUnit;
    private Page<RecordDto> records;
}
