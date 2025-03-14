package ringle.first.assignment.lecture.repository.impl;

import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ringle.first.assignment.lecture.dto.LectureTime;
import ringle.first.assignment.lecture.dto.request.ReadLectureRequest;
import ringle.first.assignment.lecture.repository.LectureRepositoryCustom;

import java.util.List;

import static ringle.first.assignment.lecture.entity.QLecture.lecture;

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

    private BooleanExpression lectureTimeEq(LectureTime lectureTime) {
        return lectureTime == LectureTime.SIXTY ? null : lecture.lectureDeadLine.eq(30);
    }
}
