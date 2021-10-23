package investDev.investAPI.controller;

import investDev.investAPI.domain.item.Product;
import investDev.investAPI.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductApiController {
    private final ProductService productService;

    @GetMapping("/api/search/allproduct")
    public List<Product> findAllProducts() {
        return productService.findAllProducts();
    }

    @GetMapping("/api/search/products")
    public List<Product> findAllRecProducts() {
        return productService.findAllRecProducts(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
    }

}
