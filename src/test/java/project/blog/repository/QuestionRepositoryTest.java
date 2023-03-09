package project.blog.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import project.blog.vo.Question;
import project.blog.vo.User;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void save() {

        User user = User.builder()
                .email("hello@naver.com")
                .password("1111")
                .username("yohan_")
                .nickname("greenTea_").build();
        userRepository.save(user);

        Question question = Question.builder()
                .content("안녕하세요")
                .subject("가입인사")
                .username("yohan").build();
        question.addUser(user);
        questionRepository.save(question);
        Long id = question.getId();

        Optional<Question> findQuestion = questionRepository.findById(id);
        assertThat(findQuestion).isNotEmpty();
        assertThat(findQuestion.get()).isEqualTo(question);
        assertThat(findQuestion.get().getUser()).isEqualTo(user);
    }

    @Test
    void findAll() {
        for (int i = 0; i < 30; i++) {
            Question question = Question.builder()
                    .content("안녕하세요")
                    .subject("가입인사")
                    .username("yohan").build();
            questionRepository.save(question);
        }

        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Question> questions = questionRepository.findAll(pageRequest);
        assertThat(questions.getContent()).size().isEqualTo(10);
        assertThat(questions.isFirst()).isTrue();
        assertThat(questions.getTotalPages()).isEqualTo(3);
        assertThat(questions.getContent()).contains(questionRepository.findById(2L).get());
    }
}