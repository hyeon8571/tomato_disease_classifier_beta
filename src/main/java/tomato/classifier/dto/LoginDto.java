package tomato.classifier.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import tomato.classifier.entity.Role;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginDto {

    @NotEmpty(message = "아이디를 입력하세요.")
    private String loginId;

    @NotEmpty(message = "비밀번호를 입력하세요.")
    private String password;

    private String nickname;

    private Role role;
}
