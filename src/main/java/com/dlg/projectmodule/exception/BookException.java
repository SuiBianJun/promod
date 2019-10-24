package com.dlg.projectmodule.exception;

public class BookException extends GlobalException {

    public BookException(String message){
        super(message);
        System.out.println("exception message: " + message);
    }

}
