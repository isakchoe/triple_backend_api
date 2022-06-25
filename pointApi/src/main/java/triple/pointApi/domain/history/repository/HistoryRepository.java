package triple.pointApi.domain.history.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import triple.pointApi.global.entity.History;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
