package ringle.first.assignment.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "tbl_user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userSeq;

    @NotNull
    private String userId;

    @NotNull
    private String userPwd;

    @Enumerated(EnumType.STRING) @NotNull
    private UserRole userRole;

    @CreatedDate @Column(updatable = false)
    private String userRegDate;

    @LastModifiedDate @Column(insertable = false)
    private String userModDate;

    @Builder
    protected User(String userId, UserRole userRole) {
        this.userId = userId;
        this.userRole = userRole;
    }

    public void changePasswordEncoder(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.userPwd = bCryptPasswordEncoder.encode(password);
    }
}