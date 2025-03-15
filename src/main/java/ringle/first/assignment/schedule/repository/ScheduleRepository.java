package ringle.first.assignment.schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ringle.first.assignment.schedule.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>, ScheduleCustomRepository {
}
