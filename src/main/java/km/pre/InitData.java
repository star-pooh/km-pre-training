package km.pre;

import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import km.pre.user.entity.User;
import km.pre.user.service.UserService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InitData {
    
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @PostConstruct
    @Transactional
    public void init() {
        List<User> userList = 
        List.of(
            new User("user1", passwordEncoder.encode("1111"), "user1@test.com"),
            new User("user2", passwordEncoder.encode("2222"), "user2@test.com"),
            new User("user3", passwordEncoder.encode("3333"), "user3@test.com"));

        for (User user : userList) {
            userService.save(user);
        }
    }
}
