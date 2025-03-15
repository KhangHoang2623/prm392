package com.example.prm392.web.error.ExceptionDefine;

import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Getter
public class AuthenticationException extends  RuntimeException{
    private final String errorCode;
    private final String message;

    public AuthenticationException(String msg) {
        super(msg);
        this.errorCode = Strings.EMPTY;
        this.message = msg;
    }


    public AuthenticationException(String errorCode, String message, Throwable ex) {
        super(message, ex);
        this.errorCode = errorCode;
        this.message = message;
    }


    public AuthenticationException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }
    public AuthenticationException(String message, String errorCode, String message1){
        super(message);
        this.errorCode = errorCode;
        this.message = message1;
    }
}
