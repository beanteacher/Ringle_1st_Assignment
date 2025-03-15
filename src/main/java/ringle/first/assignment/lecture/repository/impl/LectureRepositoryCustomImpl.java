package ringle.first.assignment.lecture.repository.impl;

import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ringle.first.assignment.lecture.dto.LectureTime;
import ringle.first.assignment.lecture.dto.request.ReadLectureRequest;
import ringle.first.assignment.lecture.dto.request.ReadTeachTutorUserRequest;
import ringle.first.assignment.lecture.dto.response.ReadTeachTutorUserResponse;
import ringle.first.assignment.lecture.entity.Lecture;
import ringle.first.assignment.lecture.entity.LectureStatus;
import ringle.first.assignment.lecture.repository.LectureRepositoryCustom;
import ringle.first.assignment.schedule.dto.request.CreateScheduleRequest;
import ringle.first.assignment.user.entity.UserRole;

import java.util.List;

import static ringle.first.assignment.lecture.entity.QLecture.lecture;
import static ringle.first.assignment.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class LectureRepositoryCustomImpl implements LectureRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    @Override
    public List<String> findAllByLectureDateAndLectureTime(ReadLectureRequest request) {
        return queryFactory
                .select(Expressions.stringTemplate(
                                "DATE_FORMAT({0}, {1})"
                                , lecture.lectureTime
                                , ConstantImpl.create("%H:%i")))
                .distinct()
                .from(lecture)
                .where(lecture.lectureTime.year().eq(request.getLectureDate().getYear())
                        .and(lecture.lectureTime.month().eq(request.getLectureDate().getMonthValue()))
                        .and(lecture.lectureTime.dayOfMonth().eq(request.getLectureDate().getDayOfMonth())),
                        lectureTimeEq(request.getLectureTime()))
                .fetch();


    }

    @Override
    public List<ReadTeachTutorUserResponse> findAllByTimeSlotAndLectureDeadLine(ReadTeachTutorUserRequest request) {
        return queryFactory
                .select(Projections.constructor(ReadTeachTutorUserResponse.class,
                        user.userSeq,
                        lecture.lectureTime,
                        lecture.lectureStatus))
                .from(lecture)
                .join(lecture.user, user)
                .where(user.userRole.eq(UserRole.ROLE_TUTOR),
                        lecture.lectureTime.eq(request.getRequestLectureTimeslot()),
                        lectureTimeEq(request.getLectureTime()))
                .fetch();
    }

    @Override
    public Lecture findByTimeSlotAndLectureDeadLineAndTutor(CreateScheduleRequest request) {
        return queryFactory
                .selectFrom(lecture)
                .join(lecture.user, user)
                .where(user.userRole.eq(UserRole.ROLE_TUTOR),
                        lecture.lectureSeq.eq(request.getLectureSeq()),
                        lecture.lectureTime.eq(request.getScheduleStartTime()),
                        lectureTimeEq(request.getScheduleTime()),
                        lecture.lectureStatus.eq(LectureStatus.AVAILABLE))
                .fetchOne();
    }

    private BooleanExpression lectureTimeEq(LectureTime lectureTime) {
        return lectureTime == LectureTime.SIXTY ?  lecture.lectureDeadLine.eq(60) : null;
    }
}
