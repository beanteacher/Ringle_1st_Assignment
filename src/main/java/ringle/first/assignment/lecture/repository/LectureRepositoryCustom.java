package ringle.first.assignment.lecture.repository;

import ringle.first.assignment.lecture.dto.request.ReadLectureRequest;
import ringle.first.assignment.lecture.dto.request.ReadTeachTutorUserRequest;
import ringle.first.assignment.lecture.dto.response.ReadTeachTutorUserResponse;

import java.util.List;

public interface LectureRepositoryCustom {
    List<String> findAllByLectureDateAndLectureTime(ReadLectureRequest request);

    List<ReadTeachTutorUserResponse> findAllByTimeSlotAndLectureDeadLine(ReadTeachTutorUserRequest request);
}
