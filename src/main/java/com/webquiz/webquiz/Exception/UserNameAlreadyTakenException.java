package com.webquiz.webquiz.Exception;

public class UserNameAlreadyTakenException extends Throwable{
    public UserNameAlreadyTakenException(String context){
        super(context);
    }
}
