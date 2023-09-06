package tomato.classifier.domain.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Disease {

    @Id
    private String id;

    @Column
    private String d_name;

    @Column
    private String solution;

    @Column
    private String src;


}
