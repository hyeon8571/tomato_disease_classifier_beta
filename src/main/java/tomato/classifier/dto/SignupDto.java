package tomato.classifier.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SignupDto {

    @NotEmpty(message = "아이디를 입력하세요.")
    private String loginId;

    @NotEmpty(message = "비밀번호를 입력하세요.")
    private String password;

    @NotEmpty(message = "이름을 입력하세요.")
    private String username;

    @NotEmpty(message = "닉네임을 입력하세요")
    private String nickname;

    @NotEmpty(message = "이메일을 입력하세요.")
    private String email;

}
