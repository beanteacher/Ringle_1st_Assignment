package ringle.first.assignment.schedule.repository;

import ringle.first.assignment.schedule.dto.request.ReadScheduleRequest;
import ringle.first.assignment.schedule.dto.response.ReadScheduleResponse;
import ringle.first.assignment.user.entity.User;

import java.util.List;

public interface ScheduleCustomRepository {
    List<ReadScheduleResponse> findAllByUserAndSearchFilter(ReadScheduleRequest request, User user);
}
