package cl.gendarmeria.reabback.domain.objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * @author Christopher Langenegger
 * @version 1.0
 * @since 28-06-2022
 **/
@Data
@SuperBuilder
@JsonInclude(NON_NULL)
public class CustomResponse {
    protected LocalDateTime dateTime;
    protected int statusCode;
    protected HttpStatus status;
    protected String reason;
    protected String message;
    protected String developerMessage;
    protected Map<?,?> data;
}
