package km.pre.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import km.pre.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
