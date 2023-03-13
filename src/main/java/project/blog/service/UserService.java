package project.blog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.blog.dto.FormUser;
import project.blog.repository.UserRepository;
import project.blog.vo.Question;
import project.blog.vo.User;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<Question> findPrivateQuestion(Long id) {

        User user = userRepository.findQuestionsWithId(id);
        return user.getQuestions();
    }


    public boolean checkUser(String nickname , String email) {
        Optional<User> findNickname = userRepository.findByNickname(nickname);
        Optional<User> findByEmail = userRepository.findByEmail(email);

        return findNickname.isEmpty() && findByEmail.isEmpty();
    }

    public void saveUser(FormUser formUser) {
        userRepository.save(User.builder()
                        .password(formUser.getPassword())
                        .username(formUser.getUsername())
                        .email(formUser.getEmail())
                        .nickname(formUser.getNickname())
                .build());
    }
}
