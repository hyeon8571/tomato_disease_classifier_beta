package tomato.classifier.dto;

import lombok.Data;
import tomato.classifier.entity.Role;
import tomato.classifier.entity.User;

import javax.validation.constraints.NotEmpty;

@Data
public class SignupDto {

    @NotEmpty(message = "아이디를 입력하세요.")
    private String loginId;

    @NotEmpty(message = "비밀번호를 입력하세요.")
    private String password;

    @NotEmpty(message = "비밀번호 확인을 위해 입력해주세요.")
    private String password2;

    @NotEmpty(message = "이름을 입력하세요.")
    private String username;

    @NotEmpty(message = "닉네임을 입력하세요")
    private String nickname;

    @NotEmpty(message = "이메일을 입력하세요.")
    private String email;

    public User convertEntity(SignupDto target) {
        return new User(
                target.getLoginId(),
                target.getPassword(),
                target.getUsername(),
                target.getNickname(),
                target.getEmail(),
                Role.USER,
                null
        );
    }
}
