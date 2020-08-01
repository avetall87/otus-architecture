package ru.spb.avetall.hwarch.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import ru.spb.avetall.hwarch.domain.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserById(@Param("id") Long id);
    List<User> findByFirstName(@Param("firstName") String firstName);
    User findByEmail(@Param("email") String email);
}
