package investDev.investAPI.controller;

import investDev.investAPI.domain.item.Product;
import investDev.investAPI.initDB;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductApiControllerTest {
    @Autowired
    ProductApiController productApiController;
    @Autowired
    initDB initDB;

    //전체 상품 조회
    @Test @Disabled
    public void searchAllProducts() {
        List<Product> products = productApiController.findAllProducts();
        products.forEach(product -> {
            System.out.println(product.getId() + "\t" + product.getTitle()
                    + "\t" + product.getTotal_rec_amt() + "\t" + product.getCur_rec_amt()
                    + "\t" + product.getInvestor_num()  + "\t" + product.getRec_status()
                    + "\t" + product.getRec_st_pred() + "\t" + product.getRec_ed_pred()); });
    }

    // 모집일이 초과된 상품 조회
    @Test
    public void searchCurRecProducts() {
        List<Product> products = productApiController.findAllRecProducts();
        assertEquals(products.size(), 10);
    }

}