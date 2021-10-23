package investDev.investAPI.controller;

import investDev.investAPI.domain.Invest;
import investDev.investAPI.domain.item.ProductStatus;
import investDev.investAPI.service.InvestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class InvestApiController {
    private final InvestService investService;

    @PostMapping("/api/invest/product")
    public ProductStatus investProduct(@RequestHeader(value = "X-USER-ID", defaultValue = "0") Long userId,
                                       @RequestParam(value = "productId", defaultValue = "0") Long productId,
                                       @RequestParam(value = "amount", defaultValue = "0") long amount) {
        return investService.invest(userId, productId, amount);
    }

    @GetMapping("/api/invest/userInvestInfo")
    public List<Invest> findUserInvestInfo(@RequestHeader(value = "X-USER-ID", defaultValue = "0") Long userId) {
        return investService.findInvestByUserId(userId);
    }
}
