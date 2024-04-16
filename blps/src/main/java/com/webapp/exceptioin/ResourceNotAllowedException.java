package com.webapp.exceptioin;

public class ResourceNotAllowedException extends RuntimeException {
    public ResourceNotAllowedException(String msg){
        super(msg);
    }
}
