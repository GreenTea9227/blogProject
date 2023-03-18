package project.blog.dto.foruser;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser {

    @NotBlank(message = "빈 값일 수 없습니다. 제대로 입력하세요")
    private String username;

    @NotBlank(message = "빈 값일 수 없습니다. 제대로 입력하세요")
    private String password;
}
