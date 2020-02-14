package com.genting.moneyexchanger.util;

public enum ErrorMessagesEnum {
    CURRENCY_RATES_NOT_FOUND(2, "CURRENCY_RATES_NOT_FOUND"),
    VALIDATION_ERROR(3, "VALIDATION ERROR"),
    INTERNAL_SERVER_ERROR(1, "INTERNAL SERVER ERROR"),
    TRANSACTION_NOT_FOUND(4, "TRANSACTION_NOT_FOUND");

    ErrorMessagesEnum(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    private int errorCode;
    private String errorMessage;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
