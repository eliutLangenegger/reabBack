package cl.gendarmeria.reabback.exceptions;

import cl.gendarmeria.reabback.exceptions.dto.ErrorDto;
import org.springframework.http.HttpStatus;

import java.util.Collections;

public class NotFoundException extends ReabException {
    private static final long serialVersionUID = 1L;

    public NotFoundException(String code, String message) {
        super(code, HttpStatus.NOT_FOUND.value(), message);
    }

    public NotFoundException(String code, String message, ErrorDto data) {
        super(code, HttpStatus.NOT_FOUND.value(), message, Collections.singletonList(data));
    }
}
