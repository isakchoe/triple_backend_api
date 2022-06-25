package triple.pointApi.domain.place.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import triple.pointApi.global.entity.Place;

import java.util.Optional;


public interface PlaceRepository extends JpaRepository<Place, Long> {
//    Optional<Place> findById(Long placeId);
}
