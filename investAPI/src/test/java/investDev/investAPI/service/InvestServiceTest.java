package investDev.investAPI.service;

import investDev.investAPI.controller.ProductApiController;
import investDev.investAPI.domain.Address;
import investDev.investAPI.domain.Invest;
import investDev.investAPI.domain.User;
import investDev.investAPI.domain.item.ProductStatus;
import investDev.investAPI.initDB;
import investDev.investAPI.repository.InvestRepository;
import investDev.investAPI.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class InvestServiceTest {
    @Autowired
    InvestService investService;
    @Autowired
    UserService userService;


    @Autowired
    initDB initDB;

    @PostConstruct
    public void UserDB() {
        User user = new User();

        user.setName("투자자11");
        user.setAge(30);
        user.setPhone("1111111111");
        Address address = new Address("서울11", "아차산로11", "05401");
        user.setAddress(address);

        userService.registUser(user);

        user = new User();
        user.setName("투자자22");
        user.setAge(30);
        user.setPhone("222222222");
        address = new Address("서울22", "아차산로22", "05402");
        user.setAddress(address);

        userService.registUser(user);
    }

    // 개인 상품 투자 조회
    @Test
    public void investProducts(){

        List<User> users = userService.findByName("투자자11");
        // 상품 투자
        ProductStatus productStatus = investService.invest(users.get(0).getId(),2L, 1000000);
        assertEquals(productStatus, ProductStatus.OPEN);

        // 상품 투자2
        productStatus = investService.invest(users.get(0).getId(),3L, 1000000);
        assertEquals(productStatus, ProductStatus.OPEN);

        // 유저가 투자한 상품 정보 조회
        List<Invest> invests = investService.findInvestByUserId(users.get(0).getId());
        assertEquals(invests.size(), 2);

        printInvestInfo(invests);

    }

    // 투자 금액 초과시 예외 테스트
    @Test
    public void investProductsExp(){

        List<User> users = userService.findLikeByName("투자자");

        // 상품 투자
        ProductStatus productStatus = investService.invest(users.get(0).getId(),2L, 1000000);
        assertEquals(productStatus, ProductStatus.OPEN);

        // 상품 투자 금액 초과 투자
        ProductStatus productStatus2 = investService.invest(users.get(1).getId(),2L, 5000000);
        assertEquals(productStatus2, ProductStatus.SOLDOUT);

        // 유저가 투자한 상품 정보 조회
        List<Invest> invests = investService.findInvestByUserId(users.get(0).getId());

        printInvestInfo(invests);

    }

    // 총 투자금액 일치시 모집종료 처리
    @Test
    public void investEqualTotalAmt(){

        List<User> users = userService.findLikeByName("투자자");

        // 상품 투자
        ProductStatus productStatus = investService.invest(users.get(0).getId(),4L, 7000000);
        assertEquals(productStatus, ProductStatus.CLOSE);

        // 유저가 투자한 상품 정보 조회
        List<Invest> invests = investService.findInvestByUserId(users.get(0).getId());

        printInvestInfo(invests);

    }

    private void printInvestInfo(List<Invest> invests) {

        for(int i=0; i<invests.size(); i++){
            System.out.println("투자자 id : " + invests.get(i).getUser().getId()
                    + "\t 상품 id : " + invests.get(i).getProduct().getId()
                    + "\t 상품 명 : " + invests.get(i).getProduct().getTitle()
                    + "\t 투자자 명 : " + invests.get(i).getUser().getName()
                    + "\t 투자금액 : " + invests.get(i).getAmount()
                    + "\t 투자일시 : " + invests.get(i).getDate()
                    + "\t 총 모집금액 : " + invests.get(i).getProduct().getTotal_rec_amt()
                    + "\t 현재 모집금액 : " + invests.get(i).getProduct().getCur_rec_amt()
                    + "\t 모집 상태 : " + invests.get(i).getProduct().getRec_status());
        }

    }


}