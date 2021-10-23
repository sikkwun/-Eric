package investDev.investAPI.service;

import investDev.investAPI.domain.Invest;
import investDev.investAPI.domain.User;
import investDev.investAPI.domain.item.Product;
import investDev.investAPI.domain.item.ProductStatus;
import investDev.investAPI.repository.InvestRepository;
import investDev.investAPI.repository.ProductRepository;
import investDev.investAPI.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InvestService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final InvestRepository investRepository;

    /** 상품 투자 처리 **/
    @Transactional
    public ProductStatus invest(Long userId, Long productId, long amount) {
        //상품 엔티티 조회
        Product product = productRepository.findByProductId(productId);
        ProductStatus str = product.compareToTotalAmt(product, amount);    // 투자 가능여부 체크
        if(str.equals(ProductStatus.SOLDOUT)){
            return ProductStatus.SOLDOUT;
        } else {
            product.setRec_status(str);
        }
        //유저 엔티티 조회
        User user = userRepository.findByUserId(userId);
        //투자 생성
        Invest invest = Invest.createInvestProduct(user, product, amount);
        //투자 저장
        investRepository.save(invest);
        return invest.getProduct().getRec_status();
    }

    public List<Invest> findInvestByUserId(Long userId){
        return investRepository.findInvestByUserId(userId);
    }


}
