package ringle.first.assignment.schedule.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ringle.first.assignment.lecture.dto.response.ReadTeachTutorUserResponse;
import ringle.first.assignment.lecture.entity.LectureStatus;
import ringle.first.assignment.schedule.dto.request.ReadScheduleRequest;
import ringle.first.assignment.schedule.dto.response.ReadScheduleResponse;
import ringle.first.assignment.schedule.repository.ScheduleCustomRepository;
import ringle.first.assignment.user.entity.User;
import ringle.first.assignment.user.entity.UserRole;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static ringle.first.assignment.lecture.entity.QLecture.lecture;
import static ringle.first.assignment.schedule.entity.QSchedule.schedule;
import static ringle.first.assignment.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class ScheduleCustomRepositoryImpl implements ScheduleCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReadScheduleResponse> findAllByUserAndSearchFilter(ReadScheduleRequest request, User user) {
        return  queryFactory
                .select(Projections.constructor(ReadScheduleResponse.class,
                        schedule.scheduleSeq,
                        schedule.scheduleStartTime,
                        schedule.scheduleTime,
                        schedule.scheduleStatus,
                        schedule.user.userSeq.as("studentUserSeq"),
                        schedule.lecture.user.userSeq.as("tutorUserSeq")))
                .from(schedule)
                .join(schedule.user)
                .where(scheduleTimeGoeLoe(request))
                .fetch();
    }

    // 날짜 필터
    private BooleanExpression scheduleTimeGoeLoe(ReadScheduleRequest request) {

        LocalDate searchStartDate = request.getStartDate();
        LocalDate searchEndDate = request.getEndDate();

        //goe, loe 사용
        BooleanExpression isGoeStartDate = searchStartDate == null ? null : schedule.scheduleStartTime.goe(LocalDateTime.of(searchStartDate, LocalTime.MIN));
        BooleanExpression isLoeEndDate = searchEndDate == null ? null : schedule.scheduleStartTime.loe(LocalDateTime.of(searchEndDate, LocalTime.MAX).withNano(0));

        return Expressions.allOf(isGoeStartDate, isLoeEndDate);
    }
}
