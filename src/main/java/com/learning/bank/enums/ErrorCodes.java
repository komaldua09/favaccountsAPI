package com.learning.bank.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCodes {

    INVALID_INPUT_PARAMETERS("5000", "The input parameters provided are invalid.", HttpStatus.BAD_REQUEST),
    CUSTOMER_NOT_FOUND("5000", "Customer not found with the given Id.", HttpStatus.PRECONDITION_FAILED),
    ACCOUNT_NOT_FOUND("5000", "Account not found with the given Id.", HttpStatus.PRECONDITION_FAILED),
    BANK_NOT_FOUND("5000", "Bank not found with the given IBAN.", HttpStatus.PRECONDITION_FAILED),
    BANK_ACCOUNT_LIMIT_EXCEEDED("5000", "You have exceeded the maximum limit of 20.", HttpStatus.PRECONDITION_FAILED),
    ALREADY_EXISTS("5000", "This favourite account already exists", HttpStatus.PRECONDITION_FAILED),
    INTERNAL_SERVER_ERROR("3001", "An internal server error occurred and processing could not be completed.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String description;
    private final HttpStatus httpStatus;

}
