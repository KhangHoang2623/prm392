package com.example.prm392.web.error.ExceptionDefine;

import org.apache.logging.log4j.util.Strings;

public class BusinessException extends RuntimeException{
    private final String errorCode;
    private final String message;

    public BusinessException(String msg) {
        super(msg);
        this.errorCode = Strings.EMPTY;
        this.message = msg;
    }


    public BusinessException(String errorCode, String message, Throwable ex) {
        super(message, ex);
        this.errorCode = errorCode;
        this.message = message;
    }


    public BusinessException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }
}
