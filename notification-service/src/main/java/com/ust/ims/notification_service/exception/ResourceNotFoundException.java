package com.ust.ims.notification_service.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){
        super();
    }

    public  ResourceNotFoundException(String msg){
        super(msg);
    }
}
