package ringle.first.assignment.lecture.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ringle.first.assignment.lecture.dto.LectureTime;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ReadLectureRequest {
    private LocalDate lectureDate;
    private LectureTime lectureTime;
}
