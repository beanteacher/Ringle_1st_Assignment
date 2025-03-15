package ringle.first.assignment.lecture.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ringle.first.assignment.lecture.dto.LectureTime;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ReadLectureRequest {
    private LocalDate lectureDate;
    private LectureTime lectureTime;
}
