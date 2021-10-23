package investDev.investAPI.domain;

import investDev.investAPI.domain.item.Product;
import investDev.investAPI.domain.item.ProductStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Invest {
    @Id @GeneratedValue
    @Column(name = "invest_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;                  // 유저정보

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;            // 상품정보

    private long amount;                // 투자금액

    private String date;                // 투자일시

    public void setUser(User user){
        this.user = user;
        user.getInvests().add(this);
    }

    // 상품 투자
    public static Invest createInvestProduct(User user, Product product, long amount){
         Invest invest = new Invest();
         invest.setUser(user);
         invest.setAmount(amount);
         invest.setDate(LocalDateTime.now().toString());
         product.addCurrentAmt(amount);
         product.addInvestorNum(1);
         invest.setProduct(product);
         return invest;
    }

}
