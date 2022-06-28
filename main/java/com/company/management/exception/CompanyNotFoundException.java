package com.company.management.exception;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException() {
    }

    public CompanyNotFoundException(String message) {
        super(message);
    }
}
