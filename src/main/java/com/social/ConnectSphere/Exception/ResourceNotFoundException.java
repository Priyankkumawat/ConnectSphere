package com.social.ConnectSphere.Exception;

import org.springframework.http.HttpStatusCode;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String sourceClass, String sourceEntity, String value){
        super(String.format("%s not found with %s : %s",sourceClass,sourceEntity,value));
    }
}
