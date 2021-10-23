package investDev.investAPI.controller;

import investDev.investAPI.domain.User;
import investDev.investAPI.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;

    @GetMapping("/api/search/user")
    public User findByUserId(@RequestHeader(value = "X-USER-ID", defaultValue = "0") Long userId) {
        return userService.findByUserId(userId);
    }



}
