package com.designX.loans.exception;

public class LoanAlreadyExsistsException extends RuntimeException {
    public LoanAlreadyExsistsException(String message) {
        super(message);
    }
}
