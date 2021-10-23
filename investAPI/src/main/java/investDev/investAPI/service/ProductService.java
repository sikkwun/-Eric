package investDev.investAPI.service;

import investDev.investAPI.domain.item.Product;
import investDev.investAPI.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    // 상품 전체 검색
    public List<Product> findAllProducts() {
        return productRepository.findAllProducts();
    }

    // 모집중 상품 전체 검색
    public List<Product> findAllRecProducts(String curDate) {
        return productRepository.findAllRecProducts(curDate);
    }

    public Product findByProductId(Long productId) {
        return productRepository.findByProductId(productId);
    }

}
