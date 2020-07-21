package nl.novi.dbexample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DogNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(DogNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String dogNotFoundHandler(DogNotFoundException ex) {
        return ex.getMessage();
    }
}
