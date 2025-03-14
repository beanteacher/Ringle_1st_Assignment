package ringle.first.assignment.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ringle.first.assignment.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);
}
