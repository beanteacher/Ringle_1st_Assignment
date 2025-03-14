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
    private LocalDateTime lectureStartTime;

    @NotNull
    private LocalDateTime lectureEndTime;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userSeq")
    private User user;

    @CreatedDate
    @Column(updatable = false)
    private String lectureRegDate;

    @LastModifiedDate
    @Column(insertable = false)
    private String lectureModDate;

    @Builder
    protected Lecture(LocalDateTime lectureStartTime, LocalDateTime lectureEndTime, User user) {
        this.lectureStartTime = lectureStartTime;
        this.lectureEndTime = lectureEndTime;
        this.user = user;
    }
}
