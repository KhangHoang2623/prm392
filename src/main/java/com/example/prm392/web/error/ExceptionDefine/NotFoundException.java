package com.example.prm392.web.error.ExceptionDefine;

import lombok.Getter;
import org.apache.logging.log4j.util.Strings;


@Getter
public class NotFoundException extends RuntimeException{
    private final String errorCode;
    private final String message;

    public NotFoundException(String msg) {
        super(msg);
        this.errorCode = Strings.EMPTY;
        this.message = msg;
    }


    public NotFoundException(String errorCode, String message, Throwable ex) {
        super(message, ex);
        this.errorCode = errorCode;
        this.message = message;
    }


    public NotFoundException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }
}
