package com.example.javahealthrisks.controllers.exceptions.handlers;

import java.io.Serializable;

public class StandardException implements Serializable {

    private String error;
    private String message;

    public StandardException() {
    }

    public StandardException(String error, String message) {
        this.error = error;
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
