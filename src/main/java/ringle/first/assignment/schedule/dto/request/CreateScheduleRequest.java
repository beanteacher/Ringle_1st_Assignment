package ringle.first.assignment.schedule.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ringle.first.assignment.lecture.dto.LectureTime;
import ringle.first.assignment.lecture.entity.LectureStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CreateScheduleRequest {
    // 수업의 정보
    long lectureSeq;
    LocalDateTime scheduleStartTime; // 수업 시작 시간
    LectureTime scheduleTime; // 수업의 길이(시간)
}
