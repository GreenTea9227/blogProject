package project.blog.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import project.blog.vo.BaseTime;
import project.blog.vo.Question;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FormUser {

    @NotNull
    private String username;
    @Length(min = 5,max = 15)
    @NotNull
    private String password;

    @Email
    @NotNull
    private String email;
    @NotNull
    private String nickname;



}
