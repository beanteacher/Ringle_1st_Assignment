package ringle.first.assignment.user.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ringle.first.assignment.user.dto.request.CreateUserRequest;
import ringle.first.assignment.user.entity.UserRole;
import ringle.first.assignment.util.exception.CustomException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;

    private static Stream<Arguments> createUserFailParam_존재하는아이디기입() {

        return Stream.of(
                arguments("userId1", "1234", UserRole.ROLE_STUDENT)
        );
    }

    @DisplayName("회원가입")
    @ParameterizedTest
    @MethodSource("createUserFailParam_존재하는아이디기입")
    void createUserFailTest_존재하는아이디기입(String userId, String userPwd, UserRole userRole) {
        CreateUserRequest request = new CreateUserRequest(userId, userPwd, userRole);

        CustomException e = Assertions.assertThrows(CustomException.class, () -> userService.createUser(request));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 아이디입니다.");
    }
}