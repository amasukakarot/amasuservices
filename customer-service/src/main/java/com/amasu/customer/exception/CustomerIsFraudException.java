package com.amasu.customer.exception;

public class CustomerIsFraudException extends RuntimeException {
    public CustomerIsFraudException(String fraudster) {
        super(fraudster);
    }
}
