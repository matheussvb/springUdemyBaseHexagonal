package br.com.matheussvb.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResponseStatusException.class)
    public ApiErrors handlerfindAllNotFound(ResponseStatusException exception){
        System.out.println(exception);
        return new ApiErrors(exception.getReason());
    }

}
