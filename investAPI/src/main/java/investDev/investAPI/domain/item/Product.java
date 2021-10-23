package investDev.investAPI.domain.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import investDev.investAPI.domain.Invest;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
public abstract class Product {
    @Id @GeneratedValue
    @Column(name="product_id")
    private Long id;        // 상품 id

    private String title;    // 상품 명

    private long total_rec_amt;  // 총모집금액

    private long cur_rec_amt;    // 현재모집금액

    private int investor_num;    // 투자자수

    @Enumerated(EnumType.STRING)
    private ProductStatus rec_status;   // 모집상태

    private String rec_st_pred;     // 모집시작기간

    private String rec_ed_pred;     // 모집종료기간

    private double profit_rate;     // 수익률

    private String description;     // 설명

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Invest> invests = new ArrayList<>();

    /** 비지니스 로직 **/
    // 현재 모집금액 증가 - 투자처리
    public void addCurrentAmt(long amount) {
        this.cur_rec_amt += amount;
    }

    // 투자자수 증가 - 투자처리
    public void addInvestorNum(int investor_num) {
        this.investor_num += investor_num;
    }

    // 투자상태 조회
    public ProductStatus compareToTotalAmt(Product product, long amount) {
        if(product.getTotal_rec_amt() > product.getCur_rec_amt() + amount){
            return ProductStatus.OPEN;
        } else if(product.getTotal_rec_amt() == product.getCur_rec_amt() + amount){
            return ProductStatus.CLOSE;
        } else{
            return ProductStatus.SOLDOUT;
        }
    }


}
