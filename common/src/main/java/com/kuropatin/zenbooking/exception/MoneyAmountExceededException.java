package com.kuropatin.zenbooking.exception;

public class MoneyAmountExceededException extends RuntimeException {

    public MoneyAmountExceededException() {
        super("The maximum amount of money has been exceeded");
    }
}