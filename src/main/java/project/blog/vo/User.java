package project.blog.vo;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class User {

    @Id @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String email;
    private String nickname;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();


}
