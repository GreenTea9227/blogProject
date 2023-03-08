package project.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import project.blog.vo.Question;
import project.blog.vo.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u.questions from User u")
    List<Question> findByUserid(Long userid);
}
