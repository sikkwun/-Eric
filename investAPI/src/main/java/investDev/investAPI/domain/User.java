package investDev.investAPI.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class User {
    @Id @GeneratedValue
    @Column(name="user_id")
    private Long id;            // 유저 id

    private String name;        // 이름

    private int age;            // 나이

    private String phone;       // 폰번호

    @Embedded
    private Address address;    // 주소

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Invest> invests = new ArrayList<>();

    public String nameLike(String userName) {
        if(!StringUtils.hasText(userName)) {
            return null;
        }
        return "%" + userName + "%";
    }

}
