package triple.pointApi.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import triple.pointApi.global.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByAccount(String account);
}
