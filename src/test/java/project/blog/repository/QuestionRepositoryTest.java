package project.blog.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import project.blog.vo.Question;
import project.blog.vo.User;

import java.util.List;
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
    @DisplayName("question 저장")
    public void save1() {
        Question question = Question.builder()
                .content("안녕하세요")
                .subject("가입인사")
                .username("yohan").build();
        questionRepository.save(question);
        Optional<Question> findQuestion = questionRepository.findById(question.getId());
        assertThat(findQuestion).isNotEmpty();
        assertThat(findQuestion.get()).isEqualTo(question);
    }

    @Test
    @DisplayName("question 삭제")
    public void delete1() {
        //given
        Question question = Question.builder()
                .content("안녕하세요")
                .subject("가입인사")
                .username("yohan").build();
        questionRepository.save(question);
        Long questionId = question.getId();

        Optional<Question> findQuestion = questionRepository.findById(questionId);
        assertThat(findQuestion).isNotEmpty();
        assertThat(findQuestion.get()).isEqualTo(question);

        //when
        questionRepository.deleteById(questionId);

        //then
        Optional<Question> deletedQuestion = questionRepository.findById(questionId);
        assertThat(deletedQuestion).isEmpty();
    }

    @Test
    @DisplayName("user1 question1 저장")
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
    @DisplayName("question 여러개 paging test")
    void pageTest() {
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
        assertThat(questions.getContent()).contains(questionRepository.findById(2L).get());
    }

    @Test
    @DisplayName("user삭제시 question도 삭제")
    void userDeleteCascade() {
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

        Long userId = user.getId();
        Long questionId = question.getId();

        userRepository.deleteById(userId);
        Optional<User> deleteUser = userRepository.findById(userId);
        assertThat(deleteUser).isEmpty();

        Optional<Question> deleteQuestion = questionRepository.findById(questionId);
        assertThat(deleteQuestion).isEmpty();
    }

    @Test
    @DisplayName("제목으로 찾기 like연산")
    public void findBySubjectContaining() {
        //given
        Question question = Question.builder()
                .content("안녕하세요")
                .subject("가입인사")
                .username("yohan").build();
        questionRepository.save(question);

        //when
        Page<Question> findQuestions = questionRepository.findBySubjectContaining("입인", PageRequest.of(0, 10));
        List<Question> content = findQuestions.getContent();

        //then
        assertThat(content).containsExactly(question);
    }

    @Test
    @DisplayName("제목으로 찾기 like연산 다른 버전")
    public void findByParamNameLike() {
        //given
        Question question = Question.builder()
                .content("안녕하세요")
                .subject("가입인사")
                .username("yohan").build();
        questionRepository.save(question);

        //when
        Page<Question> findQuestions = questionRepository.findByParamNameLike("입인", PageRequest.of(0, 10));
        List<Question> content = findQuestions.getContent();

        //then
        assertThat(content).containsExactly(question);
    }


}