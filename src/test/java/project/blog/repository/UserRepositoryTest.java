package project.blog.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.blog.vo.User;

import java.util.Optional;

@Transactional
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void prevSaveUser() {
        for (int i = 1; i < 50; i++) {
            User user = User.builder()
                    .email(i + "hello@naver.com")
                    .password("1111")
                    .username("yohan_" + i)
                    .nickname("greenTea_" + i).build();
            userRepository.save(user);
        }
    }

    @Test
    void findOne() {

        Optional<User> findUser = userRepository.findById(1L);
        User user = findUser.orElseThrow(() -> {
            throw new RuntimeException("No");
        });
        Assertions.assertThat(user.getEmail()).isEqualTo("1hello@naver.com");
        Assertions.assertThat(user.getPassword()).isEqualTo("1111");
        Assertions.assertThat(user.getUsername()).isEqualTo("yohan_1");
    }

    @Test
    void findQuestion() {

    }
}