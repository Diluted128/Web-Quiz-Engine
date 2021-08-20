package com.webquiz.webquiz.Exception;

public class WrongQuestionIdException extends Throwable {
    public WrongQuestionIdException(String content){
        super(content);
    }
}
