package com.social.ConnectSphere.Exception;

public class ApiResponse extends  RuntimeException{
    public ApiResponse(String m, boolean b){
        super(m);
    }
}
