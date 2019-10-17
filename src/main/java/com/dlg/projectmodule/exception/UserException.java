package com.dlg.projectmodule.exception;

public class UserException extends GlobalException {

    public UserException(String message){
        super(message);
        System.out.println("exception message: " + message);
    }

}
