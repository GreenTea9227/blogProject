package project.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.blog.vo.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select  distinct u from User u join fetch u.questions where u.id = :userid")
    User findQuestionsWithId(@Param("userid") Long userid);

    Optional<User> findByNickname(String nickname);

    Optional<User> findByEmail(String email);
}
