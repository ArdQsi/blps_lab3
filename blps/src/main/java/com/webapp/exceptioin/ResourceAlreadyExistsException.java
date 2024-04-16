package com.webapp.exceptioin;

public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(String msg){
        super(msg);
    }
}
