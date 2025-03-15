package ringle.first.assignment.schedule.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ringle.first.assignment.lecture.dto.LectureTime;
import ringle.first.assignment.lecture.entity.Lecture;
import ringle.first.assignment.lecture.entity.LectureStatus;
import ringle.first.assignment.user.entity.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_schedule")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long scheduleSeq;

    @NotNull
    private LocalDateTime scheduleStartTime; // 수업의 시작 시간

    private int scheduleTime; // 수업의 길이

    @NotNull
    @Enumerated(EnumType.STRING)
    private ScheduleStatus scheduleStatus; // 수업의 상태

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "lectureSeq")
    private Lecture lecture;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userSeq")
    private User user;

    @CreatedDate
    @Column(updatable = false)
    private String scheduleRegDate;

    @LastModifiedDate
    @Column(insertable = false)
    private String scheduleModDate;

    @Builder
    protected Schedule(LocalDateTime scheduleStartTime, int scheduleTime, User user, Lecture lecture) {
        this.scheduleStartTime = scheduleStartTime;
        this.scheduleTime = scheduleTime;
        this.user = user;
        this.lecture = lecture;
        this.scheduleStatus = ScheduleStatus.CANCELABLE;
    }
}
