package ringle.first.assignment.lecture.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ringle.first.assignment.lecture.dto.request.CreateLectureRequest;
import ringle.first.assignment.lecture.dto.request.ReadLectureRequest;
import ringle.first.assignment.lecture.service.LectureService;

import java.util.List;

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

    @DeleteMapping("/tutor/{lectureSeq}")
    @Operation(summary = "Delete Lecture", description = "생성된 수업 삭제")
    public ResponseEntity<Void> deleteLecture(@PathVariable Long lectureSeq) {
        lectureService.deleteLecture(lectureSeq);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    @Operation(summary = "Read Lecture", description = "수업 가능한 Lecture 시간대 조회")
    public ResponseEntity<List<String>> readLecture(@ModelAttribute ReadLectureRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(lectureService.readLecture(request));
    }
}
