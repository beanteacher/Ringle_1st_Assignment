package ringle.first.assignment.util.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCodeType {

    LOGIN_ERROR(HttpStatus.BAD_REQUEST, "LOGIN_ERROR_001", "아이디 또는 비밀번호가 일치하지 않아 로그인에 실패하였습니다."),

    EXIST_USER(HttpStatus.BAD_REQUEST, "USER_ERROR_001", "이미 존재하는 아이디입니다."),
    NOT_EXIST_USER(HttpStatus.UNAUTHORIZED, "USER_ERROR_001", "인증에 실패하였습니다."),

    TIME_REQUEST_ERROR_ONE(HttpStatus.BAD_REQUEST, "TIME_REQUEST_ERROR_001", "시간은 정각 혹은 30분 단위로 설정할 수 있습니다."),
    TIME_REQUEST_ERROR_TWO(HttpStatus.BAD_REQUEST, "TIME_REQUEST_ERROR_002", "수업종료 시간은 수업시작 시간보다 늦은 시간이어야 합니다."),

    NOT_EXIST_LECTURE(HttpStatus.BAD_REQUEST, "LECTURE_ERROR_001", "존재하지 않는 수업입니다."),
    UNAUTHORIZED_LECTURE(HttpStatus.UNAUTHORIZED, "LECTURE_ERROR_002", "해당 수업을 삭제할 권한이 없습니다."),
    POSSIBLE_LECTURE(HttpStatus.BAD_REQUEST, "LECTURE_ERROR_003", "해당 수업은 타인에 의해 이미 예약된 수업이거나 예약할 수 없는 수업입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}