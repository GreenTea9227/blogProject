package project.blog.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Question {

    private String username;

    private String content;
    private String subject;
}
