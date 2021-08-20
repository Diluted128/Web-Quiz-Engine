package com.webquiz.webquiz.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WrongQuestionIdException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    private String handleMessage(WrongQuestionIdException e) {
        return "BAD ID";
    }
}
