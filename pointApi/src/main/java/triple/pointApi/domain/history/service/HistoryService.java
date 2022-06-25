package triple.pointApi.domain.history.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import triple.pointApi.domain.history.repository.HistoryRepository;
import triple.pointApi.global.entity.History;
import triple.pointApi.global.entity.User;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;

    public void saveHistory(History history, User user){
        history.setUser(user);
        historyRepository.save(history);
    }
}
