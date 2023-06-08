package tomato.classifier.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class User {

    @Id
    private String loginId;

    private String password;

    private String username;

    private String nickname;

    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    @OneToMany(mappedBy = "user")
    private List<Article> articles;

}
