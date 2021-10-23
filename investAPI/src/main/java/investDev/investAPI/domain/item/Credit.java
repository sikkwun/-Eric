package investDev.investAPI.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter @Setter
public class Credit extends Product{
    private String grade;       // 신용등급
    private String risk_grade;  // 위험등급
}
