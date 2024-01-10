package com.Wanderlust.Exception;

public class TransactionFaliureException extends RuntimeException{
    public TransactionFaliureException() {

    }
    public TransactionFaliureException(String msg) {
        super(msg);
    }
}
