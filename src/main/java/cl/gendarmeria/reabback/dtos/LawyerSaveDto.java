package cl.gendarmeria.reabback.dtos;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Christopher Langenegger
 * @version 1.0
 * @since 15-07-2022
 **/
@Getter
public class LawyerSaveDto {
    @NotNull(message = "Run cant by null")
    private String run;
    @NotNull(message = "Name cant by null")
    private String name;
    @NotNull(message = "Surname cant by null")
    private String surname;
    @NotNull(message = "Date cant by null")
    private Date expirationDate;
    @NotNull(message = "Type cant by null")
    private String type;
    @NotNull(message = "User cant by null")
    private String userUp;
}
