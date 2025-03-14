package ringle.first.assignment.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ringle.first.assignment.user.dto.CreateUserRequest;
import ringle.first.assignment.user.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserRequest createUserRequest) {
        userService.createUser(createUserRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}