package triple.pointApi.domain.user.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import triple.pointApi.domain.user.dto.UserPointDto;
import triple.pointApi.domain.user.repository.UserRepository;
import triple.pointApi.global.entity.User;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserPointDto getUserPoint(String account){

        User user = userRepository.findByAccount(account);

        int point = user.getPoint();

        UserPointDto userPointDto = new UserPointDto(account, point);

        return userPointDto;
    }


    public void addPoint(Long userId, int point){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new NullPointerException());

        int prePoint = user.getPoint();
        int newPoint = prePoint + point;

        user.chagePoint(newPoint);
    }

}
