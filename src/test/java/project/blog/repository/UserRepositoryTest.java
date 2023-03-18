package project.blog.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.blog.service.QuestionService;
import project.blog.service.UserService;
import project.blog.vo.Question;
import project.blog.vo.User;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;



    @Test
    @DisplayName("user 저장")
    void saveOne() {
        User user = User.builder()
                .email("hello@naver.com")
                .password("1111")
                .username("yohan")
                .nickname("greenTea").build();
        userRepository.save(user);

        Optional<User> findUser = userRepository.findById(user.getId());

        assertThat(user.getEmail()).isEqualTo("hello@naver.com");
        assertThat(user.getUsername()).isEqualTo("yohan");
        assertThat(user.getNickname()).isEqualTo("greenTea");
    }

    @Test
    @DisplayName("user 삭제")
    void deleteOne() {
        //given
        User user = User.builder()
                .email("hello@naver.com")
                .password("1111")
                .username("yohan")
                .nickname("greenTea").build();
        userRepository.save(user);

        Long userId = user.getId();
        Optional<User> findUser = userRepository.findById(userId);

        //when
        userRepository.deleteById(userId);

        //then
        Optional<User> byId = userRepository.findById(userId);
        assertThat(byId).isEmpty();
    }

    @Test
    @DisplayName("생성날짜 테스트")
    void userDateTest() {
        User save = userRepository.save(User.builder().build());
        LocalDateTime now = LocalDateTime.now();

        Optional<User> findUser = userRepository.findById(save.getId());

        User user = findUser.orElseGet(User::new);
        assertThat(user.getCreatedDate()).isBefore(now);
    }

    @Test
    @DisplayName("nickname으로 user 찾아오기")
    public void findByNickname() {
        //given
        User user = User.builder()
                .email("hello@naver.com")
                .password("1111")
                .username("yohan_")
                .nickname("greenTea_").build();
        userRepository.save(user);

        //when
        Optional<User> byNickname = userRepository.findByNickname(user.getNickname());

        //then
        assertThat(byNickname).isNotEmpty();
        assertThat(byNickname.get()).isEqualTo(user);
    }

    @Test
    @DisplayName("emnail로 user 찾아오기")
    public void findByEmail() {
        //given
        User user = User.builder()
                .email("hello@naver.com")
                .password("1111")
                .username("yohan_")
                .nickname("greenTea_").build();
        userRepository.save(user);

        //when
        Optional<User> byNickname = userRepository.findByEmail(user.getEmail());

        //then
        assertThat(byNickname).isNotEmpty();
        assertThat(byNickname.get()).isEqualTo(user);
    }

    @Test
    @DisplayName("questions 컬렉션 패치 테스트")
    void userDeleteCascade() {
        //given
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

        Question question1 = Question.builder()
                .content("안녕하세요")
                .subject("가입인사")
                .username("yohan").build();
        question1.addUser(user);
        questionRepository.save(question1);

        //when
        Long userId = user.getId();
        Long questionId = question.getId();

        User questionsWithId = userRepository.findQuestionsWithId(userId);

        //then
        assertThat(questionsWithId).isEqualTo(user);
        assertThat(questionsWithId.getQuestions()).containsExactly(question,question1);

    }
}