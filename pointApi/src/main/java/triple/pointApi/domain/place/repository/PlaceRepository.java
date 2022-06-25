package triple.pointApi.domain.place.repository;


import triple.pointApi.global.entity.Place;

import java.util.Optional;

public interface PlaceRepository {
    Optional<Place> findById(Long placeId);
}
