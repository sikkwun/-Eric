package investDev.investAPI.service;

import investDev.investAPI.domain.item.Product;
import investDev.investAPI.initDB;
import investDev.investAPI.repository.ProductRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {
    @Autowired
    ProductService productService;
    @Autowired
    initDB initDB;  // 기본 상품 등록

    @Test
    public void searchAllRecProducts(){
        List<Product> products = productService.findAllRecProducts("20210910");
        assertTrue(products.size() > 1);
        System.out.println(products.size());
        assertEquals(10, products.size());
    }

}