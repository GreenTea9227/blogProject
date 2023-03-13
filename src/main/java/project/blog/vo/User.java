package project.blog.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(indexes = @Index(name = "email",columnList = "email"))
public class User extends BaseTime {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String email;
    private String nickname;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();

    @Builder
    public User(String username, String password, String email, String nickname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
    }

    public void addQuestion(Question question) {
        questions.add(question);
        question.addUser(this);
    }
}
