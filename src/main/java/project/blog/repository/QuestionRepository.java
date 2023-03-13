package project.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.blog.vo.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Page<Question> findAll(Pageable pageable);

    @Query("select q from Question q where q.subject like %:param%")
    Page<Question> findByParamNameLike(@Param(value = "param") String param, Pageable pageable); //변경

    Page<Question> findBySubjectContaining(String subject,Pageable pageable);
}
