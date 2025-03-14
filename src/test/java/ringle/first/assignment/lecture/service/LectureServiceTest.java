package ringle.first.assignment.lecture.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ringle.first.assignment.lecture.dto.CreateLectureRequest;
import ringle.first.assignment.user.dto.CreateUserRequest;
import ringle.first.assignment.user.entity.UserRole;
import ringle.first.assignment.user.service.UserService;
import ringle.first.assignment.util.exception.CustomException;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest
@Transactional
class LectureServiceTest {

    @Autowired
    LectureService lectureService;

    private static Stream<Arguments> createLectureFailParam() {



        return Stream.of(
                // 종료시간보다 시작시간이 크거나 같은 경우
                arguments(LocalDateTime.of(2025, 3, 14, 19, 0), LocalDateTime.of(2025, 3, 14, 18, 30)),
                // 30분 단위가 아닌 경우
                arguments(LocalDateTime.of(2025, 3, 14, 17, 0), LocalDateTime.of(2025, 3, 14, 18, 40))
        );
    }

    @DisplayName("수업 생성")
    @ParameterizedTest
    @MethodSource("createLectureFailParam")
    void createUserFailTest(LocalDateTime lectureStartTime, LocalDateTime lectureEndTime) {
        CreateLectureRequest request = new CreateLectureRequest(lectureStartTime, lectureEndTime);

        CustomException e = Assertions.assertThrows(CustomException.class, () -> lectureService.createLecture(request));
        assertThat(e.getMessage()).isEqualTo("시간은 정각 혹은 30분 단위로 설정할 수 있습니다.");
        assertThat(e.getMessage()).isEqualTo("수업종료 시간은 수업시작 시간보다 늦은 시간이어야 합니다.");
    }
}