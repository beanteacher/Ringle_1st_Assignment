package ringle.first.assignment.lecture.repository;

import ringle.first.assignment.lecture.dto.request.ReadLectureRequest;

import java.util.List;

public interface LectureRepositoryCustom {
    List<String> findAllByLectureDateAndLectureTime(ReadLectureRequest request);
}
