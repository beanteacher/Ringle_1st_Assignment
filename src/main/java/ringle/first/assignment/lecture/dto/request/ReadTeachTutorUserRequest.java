package ringle.first.assignment.lecture.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ringle.first.assignment.lecture.dto.LectureTime;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ReadTeachTutorUserRequest {
    LocalDateTime requestLectureTimeslot;
    LectureTime lectureTime;
}
