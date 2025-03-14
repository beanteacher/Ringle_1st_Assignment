package ringle.first.assignment.lecture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ringle.first.assignment.lecture.entity.Lecture;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
