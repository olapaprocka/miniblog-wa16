package pl.sda.jp.miniblogwa16.config;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import javax.swing.text.html.parser.Entity;

@ControllerAdvice
public class ExceptionHandlerController {


    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    //@org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)

    public ExceptionMessage handleNotFoundException(EntityNotFoundException ex){



        return new ExceptionMessage((ex.getMessage(), HttpStatus.NOT_FOUND.value()));



    }
}
