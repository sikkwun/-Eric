package investDev.investAPI.service;

import investDev.investAPI.domain.Address;
import investDev.investAPI.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void registUser() {

        User user = new User();

        user.setName("투자자");
        user.setAge(15);
        user.setPhone("01012345678");
        Address address = new Address("서울", "아차산로", "05401");
        user.setAddress(address);

        Long userId = userService.registUser(user);
        assertNotEquals(0L, userId);
    }
    
}