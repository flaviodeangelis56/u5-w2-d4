package flaviodeangelis.u6w2d2.exception;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
public class BadRequestException extends Exception {
    private List<ObjectError> errorsList;

    public BadRequestException(String massage) {
        super(massage);
    }

    public BadRequestException(List<ObjectError> errors) {
        this.errorsList = errors;
    }
}
