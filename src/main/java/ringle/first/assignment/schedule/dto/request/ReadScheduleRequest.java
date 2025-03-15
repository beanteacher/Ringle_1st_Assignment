package ringle.first.assignment.schedule.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ringle.first.assignment.util.anotation.LocalDateCheck;

import java.time.LocalDate;

@Setter
@Getter
@LocalDateCheck(startDate = "startDate", endDate = "endDate")
@AllArgsConstructor
public class ReadScheduleRequest {
    LocalDate startDate;
    LocalDate endDate;
}
