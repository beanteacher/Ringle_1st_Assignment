package ringle.first.assignment.schedule.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ringle.first.assignment.schedule.entity.ScheduleStatus;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReadScheduleResponse {
    private long scheduleSeq;
    private LocalDateTime scheduleStartTime;
    private int scheduleTime;
    private ScheduleStatus scheduleStatus;
    private long studentUserSeq;
    private long tutorUserSeq;
}
