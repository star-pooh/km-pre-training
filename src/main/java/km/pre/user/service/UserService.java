package km.pre.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import km.pre.user.dto.request.LoginRequestDto;
import km.pre.user.entity.User;
import km.pre.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public User login(LoginRequestDto dto) {
        User foundedUser = userRepository.findByEmail(dto.getEmail())
        .orElseThrow(() -> new IllegalArgumentException("Email does not exist."));

        if (!passwordEncoder.matches(dto.getPassword(), foundedUser.getPassword())) {
            throw new IllegalArgumentException("Password does not match.");
        }

        return foundedUser;
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
