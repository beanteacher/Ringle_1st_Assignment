package ringle.first.assignment.util.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    ErrorCodeType errorCode;

    public CustomException(ErrorCodeType errorCode) {
        this.errorCode = errorCode;
    }
}