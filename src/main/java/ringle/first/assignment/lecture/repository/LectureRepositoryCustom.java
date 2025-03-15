package ringle.first.assignment.lecture.repository;

import ringle.first.assignment.lecture.dto.request.ReadLectureRequest;
import ringle.first.assignment.lecture.dto.request.ReadTeachTutorUserRequest;
import ringle.first.assignment.lecture.dto.response.ReadTeachTutorUserResponse;
import ringle.first.assignment.lecture.entity.Lecture;
import ringle.first.assignment.schedule.dto.request.CreateScheduleRequest;

import java.util.List;

public interface LectureRepositoryCustom {
    List<String> findAllByLectureDateAndLectureTime(ReadLectureRequest request);

    List<ReadTeachTutorUserResponse> findAllByTimeSlotAndLectureDeadLine(ReadTeachTutorUserRequest request);

    Lecture findByTimeSlotAndLectureDeadLineAndTutor(CreateScheduleRequest request);
}
