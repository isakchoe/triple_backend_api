package triple.pointApi.domain.user.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import triple.pointApi.domain.user.dto.UserPointDto;
import triple.pointApi.domain.user.repository.UserRepository;
import triple.pointApi.global.entity.User;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public User getUser(UUID userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NullPointerException());
        return user;
    }

    public UserPointDto getUserPoint(String account){
        User user = userRepository.findByAccount(account);
        int point = user.getPoint();
        UserPointDto userPointDto = new UserPointDto(account, point);
        return userPointDto;
    }


    public void addPoint(UUID userId, int point){
        User user = getUser(userId);
        int prePoint = user.getPoint();
        int newPoint = prePoint + point;
        user.chagePoint(newPoint);
        userRepository.save(user);
    }

}
