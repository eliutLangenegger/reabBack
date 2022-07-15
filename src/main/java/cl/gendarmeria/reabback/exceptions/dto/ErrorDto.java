package cl.gendarmeria.reabback.exceptions.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String value;
}
