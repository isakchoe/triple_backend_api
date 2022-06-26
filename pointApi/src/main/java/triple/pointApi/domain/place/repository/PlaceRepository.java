package triple.pointApi.domain.place.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import triple.pointApi.global.entity.Place;

import java.util.UUID;


public interface PlaceRepository extends JpaRepository<Place, UUID> {
//    Optional<Place> findById(Long placeId);
}
