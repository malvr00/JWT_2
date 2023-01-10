package me.slivernine.tutorial.repository;

import me.slivernine.tutorial.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = "authorities")    // EntityGraph 해당 쿼리가 Lazy 조회가 아니고 Eager 조회로 authorities 정보 같이 가져옴
    Optional<User> findOnWithAuthoritiesByUsername(String username);

}
