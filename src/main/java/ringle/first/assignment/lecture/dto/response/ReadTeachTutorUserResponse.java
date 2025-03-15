package ringle.first.assignment.lecture.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ringle.first.assignment.lecture.entity.LectureStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ReadTeachTutorUserResponse {
    // 튜터 정보
    // 가능한 시간대
    long userSeq;
    LocalDateTime lectureTime;
    LectureStatus lectureStatus;
}
