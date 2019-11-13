package com.dlg.projectmodule.exception;

public class GlobalException extends Exception {

    public String message;

    public GlobalException(){}

    public GlobalException(String message){
        System.out.println("exception message: " + message);
        this.message = message;
    }

//    重写getMessage，否则会拿到null的message
    @Override
    public String getMessage() {
        return this.message;
    }


}
