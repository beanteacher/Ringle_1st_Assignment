package ringle.first.assignment.util.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCodeType {

    LOGIN_ERROR(HttpStatus.BAD_REQUEST, "LOGIN_ERROR_001", "아이디 또는 비밀번호가 일치하지 않아 로그인에 실패하였습니다."),
    ;
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}