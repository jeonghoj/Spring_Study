package jeonghoj.communityboard.repository;

import jeonghoj.communityboard.domain.Board;
import jeonghoj.communityboard.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
