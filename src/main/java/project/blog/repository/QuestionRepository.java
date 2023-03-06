package project.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import project.blog.vo.Question;

public interface QuestionRepository extends JpaRepository<Question,Long> {

    Page<Question> findAll(Pageable pageable);
}
