package ringle.first.assignment.lecture.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ringle.first.assignment.lecture.dto.CreateLectureRequest;
import ringle.first.assignment.lecture.service.LectureService;

@RestController
@RequestMapping("/api/v1/lecture")
@RequiredArgsConstructor
@Tag(name = "Lecture", description = "수업관리 API")
public class LectureController {
    private final LectureService lectureService;

    @PostMapping("/tutor")
    @Operation(summary = "Create Lecture", description = "수업 생성")
    public ResponseEntity<Void> createLecture(@RequestBody @Valid CreateLectureRequest request) {
        lectureService.createLecture(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
