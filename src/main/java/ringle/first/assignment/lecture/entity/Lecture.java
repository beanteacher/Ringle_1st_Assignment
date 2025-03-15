package ringle.first.assignment.lecture.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ringle.first.assignment.user.entity.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_lecture")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long lectureSeq;

    @NotNull
    private LocalDateTime lectureTime;

    @NotNull
    @Enumerated(EnumType.STRING)
    private LectureStatus lectureStatus;

    private int lectureDeadLine;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userSeq")
    private User user;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime lectureRegDate;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lectureModDate;

    public void updateLectureDeadLine(int lectureDeadLine) {
        this.lectureDeadLine = lectureDeadLine;
    }

    public void updateLectureStatus(LectureStatus lectureStatus) {
        this.lectureStatus = lectureStatus;
    }

    @Builder
    protected Lecture(LocalDateTime lectureTime, LectureStatus lectureStatus, User user, int lectureDeadLine) {
        this.lectureTime = lectureTime;
        this.lectureStatus = lectureStatus;
        this.user = user;
        this.lectureDeadLine = lectureDeadLine;
    }
}
