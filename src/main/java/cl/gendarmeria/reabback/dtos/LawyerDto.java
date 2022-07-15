package cl.gendarmeria.reabback.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author Christopher Langenegger
 * @version 1.0
 * @since 15-07-2022
 **/
@Getter @Setter @NoArgsConstructor
public class LawyerDto {
    private String run;
    private String name;
    private String surname;
    private Date upDate;
    private Date expirationDate;
    private String type;
    private boolean active;
    private String docId;
    private String userUp;
    private List<RecordDto> records;
}
