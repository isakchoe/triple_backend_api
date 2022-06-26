package triple.pointApi.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import triple.pointApi.global.entity.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    //NPE 처리를 해야할까?
    User findByAccount(String account);

}
