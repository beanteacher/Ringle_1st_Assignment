package ringle.first.assignment.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ringle.first.assignment.user.dto.request.CreateUserRequest;
import ringle.first.assignment.user.entity.User;
import ringle.first.assignment.user.repository.UserRepository;
import ringle.first.assignment.util.exception.CustomException;
import ringle.first.assignment.util.exception.ErrorCodeType;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(userId);

        List<GrantedAuthority> authList = new ArrayList<>();

        // user가 가지고 있는 권한을 권한 리스트에 추가
        authList.add(new SimpleGrantedAuthority(user.getUserRole().name()));

        return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getUserPwd(), authList); // Id, Pwd, 권한을 Security에서 관리하는 User 객체에 넣는다.
    }

    @Transactional
    public void createUser(CreateUserRequest request) {
        User user = userRepository.findByUserId(request.getUserId());
        if(user != null) {
            throw new CustomException(ErrorCodeType.EXIST_USER);
        }

        User newUser = User.builder()
                .userId(request.getUserId())
                .userRole(request.getUserRole())
                .build();
        newUser.changePasswordEncoder(request.getUserPwd());

        userRepository.save(newUser);
    }
}
