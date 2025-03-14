package ringle.first.assignment.lecture.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CreateLectureDTO {
    LocalDateTime lectureStartTime;
    LectureTime lectureTime;
}
