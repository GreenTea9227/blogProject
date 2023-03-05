package project.blog.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class WriteFormData {
    @NotEmpty(message = "이름은 필수입니다.")
    private String username;
    @NotEmpty
    @Length(min = 10,max = 200,message = "너무 적은 글자 수 입니다.")
    private String content;
    @NotEmpty(message = "제목은 필수입니다.")
    private String subject;
}
