package cl.gendarmeria.reabback.exceptions;

import cl.gendarmeria.reabback.exceptions.dto.ErrorDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ReabException extends Exception{
    private static final long serialVersionUID = 1L;
    private final String code;
    private final int reponseCode;
    private final List<ErrorDto> errorList = new ArrayList<>();

    public ReabException(String code, int reponseCode, String message){
        super(message);
        this.code=code;
        this.reponseCode=reponseCode;
    }

    public ReabException(String code, int reponseCode, String message, List<ErrorDto> errorList){
        super(message);
        this.code=code;
        this.reponseCode=reponseCode;
        this.errorList.addAll(errorList);
    }

}
