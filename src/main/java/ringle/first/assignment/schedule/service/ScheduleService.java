package ringle.first.assignment.schedule.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ringle.first.assignment.lecture.dto.LectureTime;
import ringle.first.assignment.lecture.entity.Lecture;
import ringle.first.assignment.lecture.entity.LectureStatus;
import ringle.first.assignment.lecture.repository.LectureRepository;
import ringle.first.assignment.schedule.dto.request.CreateScheduleRequest;
import ringle.first.assignment.schedule.dto.request.ReadScheduleRequest;
import ringle.first.assignment.schedule.dto.response.ReadScheduleResponse;
import ringle.first.assignment.schedule.entity.Schedule;
import ringle.first.assignment.schedule.repository.ScheduleRepository;
import ringle.first.assignment.security.JwtUtil;
import ringle.first.assignment.user.entity.User;
import ringle.first.assignment.user.repository.UserRepository;
import ringle.first.assignment.util.exception.CustomException;
import ringle.first.assignment.util.exception.ErrorCodeType;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final LectureRepository lectureRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public void createSchedule(CreateScheduleRequest request) {
        Lecture lecture = lectureRepository.findByTimeSlotAndLectureDeadLineAndTutor(request);
        if(lecture == null) {
            throw new CustomException(ErrorCodeType.POSSIBLE_LECTURE);
        }
        lecture.updateLectureStatus(LectureStatus.POSSIBLE); // 수업 예약 시 해당 수업 예약 못하도록 POSSIBLE 상태로 변경

        org.springframework.security.core.userdetails.User authenticatedUser = JwtUtil.getAuthenticatedUser();
        User user = userRepository.findByUserId(authenticatedUser.getUsername());

        Schedule schedule = Schedule.builder()
                .scheduleStartTime(request.getScheduleStartTime())
                .scheduleTime(request.getScheduleTime() == LectureTime.SIXTY ? 60 : 30)
                .lecture(lecture)
                .user(user)
                .build();

        scheduleRepository.save(schedule);
    }

    public List<ReadScheduleResponse> readSchedule(ReadScheduleRequest request) {
        org.springframework.security.core.userdetails.User authenticatedUser = JwtUtil.getAuthenticatedUser();
        User user = userRepository.findByUserId(authenticatedUser.getUsername());
        return scheduleRepository.findAllByUserAndSearchFilter(request, user);
    }
}
