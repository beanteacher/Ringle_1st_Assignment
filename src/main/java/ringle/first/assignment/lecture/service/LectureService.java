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
import ringle.first.assignment.util.exception.CustomException;
import ringle.first.assignment.util.exception.ErrorCodeType;

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

    @Transactional
    public void deleteLecture(Long lectureSeq) {
        User user = JwtUtil.getAuthenticatedUser();
        Lecture lecture = lectureRepository.findById(lectureSeq).orElseThrow(() -> new CustomException(ErrorCodeType.NOT_EXIST_LECTURE));
        if(!lecture.getUser().getUserId().equals(user.getUsername())) {
            throw new CustomException(ErrorCodeType.UNAUTHORIZED_LECTURE);
        }
        lectureRepository.deleteById(lectureSeq);
    }
}
