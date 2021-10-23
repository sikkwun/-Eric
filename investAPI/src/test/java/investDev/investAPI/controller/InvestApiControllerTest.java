package investDev.investAPI.controller;

import investDev.investAPI.domain.Invest;
import investDev.investAPI.domain.item.ProductStatus;
import investDev.investAPI.service.InvestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.*;
import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InvestApiControllerTest {

    @Autowired
    private InvestApiController investApiController;

    @Test
    public void investTest(){
        ProductStatus status = investApiController.investProduct(1L,2L, 1000000);
        assertEquals(status, ProductStatus.OPEN);

        List<Invest> invests = investApiController.findUserInvestInfo(1L);
        assertEquals(invests.size(), 1);

        status = investApiController.investProduct(1L,3L, 2000000);
        assertEquals(status, ProductStatus.CLOSE);

        invests = investApiController.findUserInvestInfo(1L);
        assertEquals(invests.size(), 2);
    }

}