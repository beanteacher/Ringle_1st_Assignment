package ringle.first.assignment.lecture.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ringle.first.assignment.lecture.dto.LectureTime;
import ringle.first.assignment.lecture.dto.request.CreateLectureRequest;
import ringle.first.assignment.lecture.dto.request.ReadLectureRequest;
import ringle.first.assignment.lecture.dto.request.ReadTeachTutorUserRequest;
import ringle.first.assignment.util.exception.CustomException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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

    @DisplayName("수업 생성 실패")
    @ParameterizedTest
    @MethodSource("createLectureFailParam")
    void createLectureFailTest(LocalDateTime lectureStartTime, LocalDateTime lectureEndTime) {
        CreateLectureRequest request = new CreateLectureRequest(lectureStartTime, lectureEndTime);

        CustomException e = Assertions.assertThrows(CustomException.class, () -> lectureService.createLecture(request));
        assertThat(e.getMessage()).isEqualTo("시간은 정각 혹은 30분 단위로 설정할 수 있습니다.");
        assertThat(e.getMessage()).isEqualTo("수업종료 시간은 수업시작 시간보다 늦은 시간이어야 합니다.");
    }

    private static Stream<Arguments> createLectureParam() {
        return Stream.of(
                arguments(LocalDateTime.of(2025, 3, 14, 19, 0), LocalDateTime.of(2025, 3, 14, 19, 30))
        );
    }

    @DisplayName("수업 생성 성공")
    @ParameterizedTest
    @MethodSource("createLectureParam")
    void createLectureTest(LocalDateTime lectureStartTime, LocalDateTime lectureEndTime) {
        CreateLectureRequest request = new CreateLectureRequest(lectureStartTime, lectureEndTime);

        assertDoesNotThrow(() -> lectureService.createLecture(request));
    }

    private static Stream<Arguments> deleteLectureFailParam() {
        return Stream.of(
                arguments(1),
                arguments(2)
        );
    }

    @DisplayName("수업 삭제 실패")
    @ParameterizedTest
    @MethodSource("deleteLectureFailParam")
    void deleteLectureFailTest(Long lectureSeq) {

        CustomException e = Assertions.assertThrows(CustomException.class, () -> lectureService.deleteLecture(lectureSeq));
        assertThat(e.getMessage()).isEqualTo("존재하지 않는 수업입니다.");
        assertThat(e.getMessage()).isEqualTo("해당 수업을 삭제할 권한이 없습니다.");
    }

    private static Stream<Arguments> deleteLectureParam() {
        return Stream.of(
                arguments(5)
        );
    }

    @DisplayName("수업 삭제 성공")
    @ParameterizedTest
    @MethodSource("deleteLectureParam")
    void deleteLecture(Long lectureSeq) {

        assertDoesNotThrow(() -> lectureService.deleteLecture(lectureSeq));
    }

    private static Stream<Arguments> readLectureParam() {
        return Stream.of(
                arguments(LocalDate.of(2025, 3, 14), LectureTime.SIXTY)
        );
    }

    @DisplayName("수업 시간대 조회 성공")
    @ParameterizedTest
    @MethodSource("readLectureParam")
    void readLectureTest(LocalDate lectureDate, LectureTime lectureTime) {
        ReadLectureRequest request = new ReadLectureRequest(lectureDate, lectureTime);

        assertDoesNotThrow(() -> lectureService.readLecture(request));
    }

    private static Stream<Arguments> readTeachTutorUserParam() {
        return Stream.of(
                arguments(LocalDateTime.of(2025, 3, 14, 17, 30), LectureTime.SIXTY)
        );
    }

    @DisplayName("수업 시간대 조회 성공")
    @ParameterizedTest
    @MethodSource("readTeachTutorUserParam")
    void readTeachTutorUserTest(LocalDateTime requestLectureTimeslot, LectureTime lectureTime) {
        ReadTeachTutorUserRequest request = new ReadTeachTutorUserRequest(requestLectureTimeslot, lectureTime);

        assertDoesNotThrow(() -> lectureService.readTeachTutorUser(request));
    }
}