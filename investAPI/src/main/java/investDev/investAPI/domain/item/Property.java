package investDev.investAPI.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter @Setter
public class Property extends Product{
    private String location;    //위치
    private int household_num;  //세대수
}
