package ringle.first.assignment.lecture.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ringle.first.assignment.util.anotation.LectureTimeCheck;

import java.time.LocalDateTime;

@LectureTimeCheck(startDate = "lectureStartTime", endDate = "lectureEndTime")
@Getter
@Setter
@AllArgsConstructor
public class CreateLectureRequest {
    // end는 start보다 늦은 시간이어야 한다.
    // start와 end는 모두 00분 혹은 30분 단위이여야만 한다.
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull
    private LocalDateTime lectureStartTime; // 수업 시작 가능 시간
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull
    private LocalDateTime lectureEndTime; // 수업 종료 가능 시간
}
