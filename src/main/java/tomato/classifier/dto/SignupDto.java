package tomato.classifier.dto;

import lombok.Data;
import tomato.classifier.entity.Role;
import tomato.classifier.entity.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class SignupDto {

    @NotEmpty(message = "아이디를 입력하세요.")
    @Pattern(regexp = "[a-zA-Z0-9]{6,10}", message = "아이디는 영문, 숫자만 가능하며 6 ~ 10자리까지 가능합니다.")
    private String loginId;

    @NotEmpty(message = "비밀번호를 입력하세요.")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}", message = "비밀번호는 영문과 숫자 조합으로 8 ~ 16자리까지 가능합니다.")
    private String password;

    @NotEmpty(message = "비밀번호 확인을 위해 입력해주세요.")
    private String password2;

    @NotEmpty(message = "이름을 입력하세요.")
    private String username;

    @NotEmpty(message = "닉네임을 입력하세요")
    private String nickname;

    @NotEmpty(message = "이메일을 입력하세요.")
    @Pattern(regexp = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}", message = "올바르지 않은 이메일 형식입니다.")
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
