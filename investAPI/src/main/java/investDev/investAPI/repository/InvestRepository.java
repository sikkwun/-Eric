package investDev.investAPI.repository;

import investDev.investAPI.domain.Invest;
import investDev.investAPI.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class InvestRepository {
    private final EntityManager em;

    public void save(Invest invest) {
        em.persist(invest);
    }

    public Invest findByInvestId(Long InvestId) {
        return em.find(Invest.class, InvestId);
    }

    public List<Invest> findInvestByUserId(Long userId) {
        return em.createQuery(
                "select i from Invest i" +
                        " join fetch i.user u" +
                        " join fetch i.product p " +
                        " where i.user.id = :userId", Invest.class)
                .setParameter("userId", userId)
                .getResultList();
    }





}
