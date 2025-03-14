package ringle.first.assignment.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ringle.first.assignment.user.entity.UserRole;

@Getter
@Setter
@AllArgsConstructor
public class CreateUserRequest {
    private String userId;
    private String userPwd;
    private UserRole userRole;
}
