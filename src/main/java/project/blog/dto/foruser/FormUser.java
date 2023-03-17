package project.blog.dto.foruser;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FormUser {

    @NotNull
    private String username;
    @Length(min = 5, max = 15)
    @NotNull
    private String password;

    @Email
    @NotNull
    private String email;
    @NotNull
    private String nickname;
}
