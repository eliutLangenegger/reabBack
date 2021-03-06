package cl.gendarmeria.reabback.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Christopher Langenegger
 * @version 1.0
 * @since 15-07-2022
 **/
@Getter @Setter @NoArgsConstructor
public class RecordDto {
    private Long id;
    private List<String> internals = new ArrayList<>();
    private Date visitDate;
    private Date outDate;
    private Integer unitCode;
    private String lawyer;
}
