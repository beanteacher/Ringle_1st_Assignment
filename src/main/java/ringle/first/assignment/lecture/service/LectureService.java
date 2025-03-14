package ringle.first.assignment.lecture.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ringle.first.assignment.lecture.dto.CreateLectureRequest;
import ringle.first.assignment.lecture.entity.Lecture;
import ringle.first.assignment.lecture.repository.LectureRepository;
import ringle.first.assignment.security.JwtUtil;
import ringle.first.assignment.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createLecture(CreateLectureRequest request) {
        User user = JwtUtil.getAuthenticatedUser();

        Lecture lecture = Lecture.builder()
                .lectureStartTime(request.getLectureStartTime())
                .lectureEndTime(request.getLectureEndTime())
                .user(userRepository.findByUserId(user.getUsername()))
                .build();

        lectureRepository.save(lecture);
    }
}
