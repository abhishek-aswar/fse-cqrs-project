package com.fse.query.domain.exception;

public class EmployeeNotFoundException extends Exception{
    private String message;
    private String name;

    public EmployeeNotFoundException(String name, String message) {
        super(message);
        this.name = name;
    }
}
