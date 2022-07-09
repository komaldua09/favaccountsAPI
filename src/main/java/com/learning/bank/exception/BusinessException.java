package com.learning.bank.exception;

import com.learning.bank.enums.ErrorCodes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusinessException extends RuntimeException {
    private String message;
    private String errorCode;
    private HttpStatus httpStatus;

    public static BusinessException businessException(ErrorCodes errorCode) {

        return BusinessException.builder()
                .message(errorCode.getDescription())
                .errorCode(errorCode.getCode())
                .httpStatus(errorCode.getHttpStatus())
                .build();
    }

}

