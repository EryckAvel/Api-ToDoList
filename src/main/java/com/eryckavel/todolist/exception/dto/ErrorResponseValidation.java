package com.eryckavel.todolist.exception.dto;

import java.util.Map;

public class ErrorResponseValidation extends ErrorResponse{

    private Map<String, String> validationErrors;

    public ErrorResponseValidation() {
    }

    public ErrorResponseValidation(Integer status, String error, String message, String path) {
        super(status, error, message, path);
    }

    public Map<String, String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(Map<String, String> validationErrors) {
        this.validationErrors = validationErrors;
    }

}
