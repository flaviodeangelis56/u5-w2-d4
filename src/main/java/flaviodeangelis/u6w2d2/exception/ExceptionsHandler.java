package flaviodeangelis.u6w2d2.exception;

import flaviodeangelis.u6w2d2.payload.ErrorResponseDTO;
import flaviodeangelis.u6w2d2.payload.ErrorsListResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsListResponseDTO handleBadRequest(BadRequestException e) {
        if (e.getErrorsList() != null) {
            List<String> errorsList = e.getErrorsList().stream().map(error -> error.getDefaultMessage()).toList();
            return new ErrorsListResponseDTO(e.getMessage(), new Date(), errorsList);
        } else {
            return new ErrorsListResponseDTO(e.getMessage(), new Date(), new ArrayList<>());
        }

    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO handleNotFound(NotFoundException e) {
        return new ErrorResponseDTO(e.getMessage(), new Date());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDTO handleGeneric(Exception e) {
        e.printStackTrace();
        return new ErrorResponseDTO("Problema lato server...giuro che lo risolveremo presto", new Date());
    }
}