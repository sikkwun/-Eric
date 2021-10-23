package investDev.investAPI.service;

import investDev.investAPI.domain.User;
import investDev.investAPI.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // 유저 등록
    @Transactional
    public Long registUser(User user) {
        userRepository.save(user);
        return user.getId();
    }

    public User findByUserId(Long id) {
        return userRepository.findByUserId(id);
    }

    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    public List<User> findLikeByName(String name) {
        User user = new User();
        String likeName = user.nameLike(name);
        return userRepository.findLikeByName(likeName);
    }

}
