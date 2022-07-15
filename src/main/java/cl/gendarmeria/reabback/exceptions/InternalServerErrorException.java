package cl.gendarmeria.reabback.exceptions;

import cl.gendarmeria.reabback.exceptions.dto.ErrorDto;
import org.springframework.http.HttpStatus;

import java.util.Collections;

public class InternalServerErrorException  extends ReabException {
    private static final long serialVersionUID = 1L;

    public InternalServerErrorException(String code, String message) {
        super(code, HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    public InternalServerErrorException(String code, String message, ErrorDto data) {
        super(code, HttpStatus.INTERNAL_SERVER_ERROR.value(), message, Collections.singletonList(data));
    }
}
