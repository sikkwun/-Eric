package investDev.investAPI.repository;

import investDev.investAPI.domain.Invest;
import investDev.investAPI.domain.item.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {
    private final EntityManager em;

    public void save(Product product) {
        if(product.getId() == null){
            em.persist(product);
        } else{
            em.merge(product);
        }

    }

    // 상품 1개 조회
    public Product findByProductId(Long id) {
        return em.find(Product.class, id);
    }

    // 상품 전체 조회
    public List<Product> findAllProducts() {
        return em.createQuery("select p from Product p", Product.class).getResultList();
    }

    public List<Product> findAllRecProducts(String curDate) {
        return em.createQuery(
                "select p from Product p " +
                        " where p.rec_st_pred <= :curDate " +
                        "   and p.rec_ed_pred >= :curDate " , Product.class)
                .setParameter("curDate", curDate)
                .getResultList();
    }


}
