package investDev.investAPI;

import investDev.investAPI.domain.Address;
import investDev.investAPI.domain.User;
import investDev.investAPI.domain.item.Credit;
import investDev.investAPI.domain.item.Product;
import investDev.investAPI.domain.item.ProductStatus;
import investDev.investAPI.domain.item.Property;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class initDB {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.initUserDB();
        initService.initProductDB();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    public static class InitService {
        private final EntityManager em;

        public void initProductDB() {
            Credit credit = new Credit();
            Property property = new Property();
            for(int i=0; i<10; i++){
                if(i % 2 == 0) {
                    credit.setTitle("개인신용 포트폴리오" + i);
                    credit.setTotal_rec_amt(5000000 + i*1000000);
                    credit.setCur_rec_amt(0);
                    credit.setRec_status(ProductStatus.OPEN);
                    credit.setRec_st_pred("20210909");
                    credit.setRec_ed_pred("20211001");
                    credit.setProfit_rate(70);
                    credit.setDescription("아주좋음");
                    credit.setGrade("SSS");
                    credit.setRisk_grade("5");
                    em.persist(credit);
                    credit = new Credit();
                } else{
                    property.setTitle("부동산 포트폴리오" + i);
                    property.setTotal_rec_amt(1000000 + i*1000000);
                    property.setCur_rec_amt(0);
                    property.setRec_status(ProductStatus.OPEN);
                    property.setRec_st_pred("2021090"+ i);
                    property.setRec_ed_pred("2021100"+ i);
                    property.setProfit_rate(70);
                    property.setDescription("아주좋음");
                    property.setLocation("서울");
                    property.setHousehold_num(50000);
                    em.persist(property);
                    property = new Property();
                }
            }

            credit = new Credit();
            credit.setTitle("개인신용 포트폴리오 날짜지남");
            credit.setTotal_rec_amt(999999999);
            credit.setCur_rec_amt(0);
            credit.setRec_status(ProductStatus.CLOSE);
            credit.setRec_st_pred("20210901");
            credit.setRec_ed_pred("20210905");
            credit.setProfit_rate(70);
            credit.setDescription("아주좋음");
            credit.setGrade("SSS");
            credit.setRisk_grade("5");
            em.persist(credit);

        }

        public void initUserDB() {
            User user = new User();

            user.setName("임시 유저 등록");
            user.setAge(100);
            user.setPhone("555555555555");
            Address address = new Address("서울", "아차산로", "05401");
            user.setAddress(address);

            em.persist(user);

        }


    }
}
