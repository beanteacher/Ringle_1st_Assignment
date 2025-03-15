package ringle.first.assignment.schedule.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ringle.first.assignment.schedule.dto.request.CreateScheduleRequest;
import ringle.first.assignment.schedule.dto.request.ReadScheduleRequest;
import ringle.first.assignment.schedule.dto.response.ReadScheduleResponse;
import ringle.first.assignment.schedule.service.ScheduleService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schedule")
@RequiredArgsConstructor
@Tag(name = "Schedule", description = "수업 신청 관리 API")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    @Operation(summary = "Create Schedule", description = "수업 신청")
    public ResponseEntity<Void> createSchedule(@RequestBody CreateScheduleRequest request) {
        scheduleService.createSchedule(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @Operation(summary = "Read Schedule", description = "신청 완료된 수업 일정 조회")
    public ResponseEntity<List<ReadScheduleResponse>> readSchedule(@ModelAttribute @Valid ReadScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.readSchedule(request));
    }
}
