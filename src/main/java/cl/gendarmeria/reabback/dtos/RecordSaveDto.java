package cl.gendarmeria.reabback.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Christopher Langenegger
 * @version 1.0
 * @since 15-07-2022
 **/
@Getter
public class RecordSaveDto {
    @NotNull (message = "Lawyer Run cant by null")
    private String lawyerRun;
    @NotEmpty(message = "Internal list cannot be empty")
    private List<String> internals;
    @NotNull(message = "Unit code cannot be empty or null")
    private int unitCode;
}
