package triple.pointApi.domain.user.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import triple.pointApi.domain.user.dto.UserPointDto;
import triple.pointApi.domain.user.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{idx}/point")
    public UserPointDto getPoint(@PathVariable("idx") String account) {
        UserPointDto userPointDto = userService.getUserPoint(account);
        return userPointDto;
    }

}
