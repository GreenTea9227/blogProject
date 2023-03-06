package project.blog.vo;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Question {

    @Id @GeneratedValue
    private Long id;
    private String username;

    private String content;
    private String subject;

    public Question(String username, String content, String subject) {
        this.username = username;
        this.content = content;
        this.subject = subject;
    }
}
