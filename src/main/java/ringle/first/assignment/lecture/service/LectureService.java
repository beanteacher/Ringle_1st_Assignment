package ringle.first.assignment.lecture.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ringle.first.assignment.lecture.dto.request.CreateLectureRequest;
import ringle.first.assignment.lecture.dto.request.ReadLectureRequest;
import ringle.first.assignment.lecture.dto.response.ReadLectureResponse;
import ringle.first.assignment.lecture.entity.Lecture;
import ringle.first.assignment.lecture.entity.LectureStatus;
import ringle.first.assignment.lecture.repository.LectureRepository;
import ringle.first.assignment.security.JwtUtil;
import ringle.first.assignment.user.entity.User;
import ringle.first.assignment.user.repository.UserRepository;
import ringle.first.assignment.util.exception.CustomException;
import ringle.first.assignment.util.exception.ErrorCodeType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final UserRepository userRepository;

    @Value("${interval}")
    private int interval;

    @Transactional
    public void createLecture(CreateLectureRequest request) {
        org.springframework.security.core.userdetails.User authenticatedUser = JwtUtil.getAuthenticatedUser();
        User user = userRepository.findByUserId(authenticatedUser.getUsername());

        LocalDateTime startDateTime = request.getLectureStartTime();
        LocalDateTime endDateTime = request.getLectureEndTime();
        List<LocalDateTime> timeList = new ArrayList<>();

        while(!startDateTime.isAfter(endDateTime)) {
            timeList.add(startDateTime);
            startDateTime = startDateTime.plusMinutes(interval);
        }

        List<Lecture> lectureList = new ArrayList<>();
        for(LocalDateTime time : timeList) {
            Lecture lecture = Lecture.builder()
                    .lectureTime(time)
                    .lectureStatus(LectureStatus.AVAILABLE)
                    .user(user)
                    .build();

            lectureList.add(lecture);
        }

        lectureRepository.saveAll(lectureList);
    }

    @Transactional
    public void deleteLecture(Long lectureSeq) {
        org.springframework.security.core.userdetails.User user = JwtUtil.getAuthenticatedUser();
        Lecture lecture = lectureRepository.findById(lectureSeq).orElseThrow(() -> new CustomException(ErrorCodeType.NOT_EXIST_LECTURE));
        if(!lecture.getUser().getUserId().equals(user.getUsername())) {
            throw new CustomException(ErrorCodeType.UNAUTHORIZED_LECTURE);
        }
        lectureRepository.deleteById(lectureSeq);
    }

    public ReadLectureResponse readLecture(ReadLectureRequest request) {
        lectureRepository.findAllByLectureDateAndLectureTime(request);
        return null;
    }
}
