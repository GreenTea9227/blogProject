package project.blog.vo;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question extends BaseTime {

    @Id
    @GeneratedValue
    private Long id;
    private String username;

    private String content;
    private String subject;
    private String tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void addUser(User user) {
        this.user = user;
    }

    @Builder
    public Question(String username, String content, String subject) {
        this.username = username;
        this.content = content;
        this.subject = subject;
    }

    @Builder
    public Question(String username, String content, String subject, String tag) {
        this.username = username;
        this.content = content;
        this.subject = subject;
        this.tag = tag;
    }

    private void addQuestion() {
        user.getQuestions().add(this);
    }
}
