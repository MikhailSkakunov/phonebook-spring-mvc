package ru.sunrise.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@ControllerAdvice
public class ExceptionAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public ModelAndView handleNotFoundException(NotFoundException ex) {
        return new ModelAndView(
                "not_found",
                Map.of("message", ex.getLocalizedMessage()));
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler
    public ModelAndView handleAlreadyExistException(AlreadyExistException ex) {
        return new ModelAndView(
                "already_exist",
                Map.of("message", ex.getLocalizedMessage()));
    }
}
