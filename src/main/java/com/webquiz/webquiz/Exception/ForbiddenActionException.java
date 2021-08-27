package com.webquiz.webquiz.Exception;

public class ForbiddenActionException extends Throwable{
    public ForbiddenActionException(String context){
        super(context);
    }
}
