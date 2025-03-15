package ringle.first.assignment.schedule.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ringle.first.assignment.lecture.dto.LectureTime;
import ringle.first.assignment.schedule.dto.request.CreateScheduleRequest;
import ringle.first.assignment.schedule.dto.request.ReadScheduleRequest;
import ringle.first.assignment.util.exception.CustomException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest
@Transactional
class ScheduleServiceTest {

    @Autowired
    ScheduleService scheduleService;

    private static Stream<Arguments> createScheduleFailParam() {
        return Stream.of(
                // 이미 예약된 Lecture를 예약하는 경우
                arguments(10, LocalDateTime.of(2025, 3, 14, 19, 0), LectureTime.THIRTY)
        );
    }

    @DisplayName("수업 예약 실패")
    @ParameterizedTest
    @MethodSource("createScheduleFailParam")
    void createScheduleFailTest(long lectureSeq, LocalDateTime lectureStartTime, LectureTime scheduleTime) {
        CreateScheduleRequest request = new CreateScheduleRequest(lectureSeq, lectureStartTime, scheduleTime);

        CustomException e = Assertions.assertThrows(CustomException.class, () -> scheduleService.createSchedule(request));
        assertThat(e.getMessage()).isEqualTo("해당 수업은 타인에 의해 이미 예약된 수업이거나 예약할 수 없는 수업입니다.");
    }

    private static Stream<Arguments> createScheduleParam() {
        return Stream.of(
                arguments(3, LocalDateTime.of(2025, 3, 14, 19, 0), LectureTime.THIRTY)
        );
    }

    @DisplayName("수업 예약 성공")
    @ParameterizedTest
    @MethodSource("createScheduleParam")
    void createScheduleTest(long lectureSeq, LocalDateTime lectureStartTime, LectureTime scheduleTime) {
        CreateScheduleRequest request = new CreateScheduleRequest(lectureSeq, lectureStartTime, scheduleTime);

        assertDoesNotThrow(() -> scheduleService.createSchedule(request));
    }

    private static Stream<Arguments> readScheduleFailParam() {
        return Stream.of(
                // endDate가 startDate보다 작은 경우
                arguments(LocalDate.of(2025, 3, 14), LocalDate.of(2025, 3, 13))
        );
    }

    @DisplayName("수업 예약 조회 실패")
    @ParameterizedTest
    @MethodSource("readScheduleFailParam")
    void readScheduleFailTest(LocalDate startDate, LocalDate endDate) {
        ReadScheduleRequest request = new ReadScheduleRequest(startDate, endDate);

        CustomException e = Assertions.assertThrows(CustomException.class, () -> scheduleService.readSchedule(request));
        assertThat(e.getMessage()).isEqualTo("해당 수업은 타인에 의해 이미 예약된 수업이거나 예약할 수 없는 수업입니다.");
    }

    private static Stream<Arguments> readScheduleParam() {
        return Stream.of(
                arguments(LocalDate.of(2025, 3, 12), LocalDate.of(2025, 3, 16))
        );
    }

    @DisplayName("수업 예약 조회 성공")
    @ParameterizedTest
    @MethodSource("readScheduleParam")
    void readScheduleTest(LocalDate startDate, LocalDate endDate) {
        ReadScheduleRequest request = new ReadScheduleRequest(startDate, endDate);

        assertDoesNotThrow(() -> scheduleService.readSchedule(request));
    }
}