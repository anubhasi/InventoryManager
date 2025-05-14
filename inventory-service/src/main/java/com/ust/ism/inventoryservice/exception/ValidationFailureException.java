package com.ust.ism.inventoryservice.exception;

public class ValidationFailureException extends Exception{

    public ValidationFailureException(String msg){
        super(msg);
    }
}
