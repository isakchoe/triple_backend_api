package triple.pointApi.domain.review.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import triple.pointApi.global.entity.Place;
import triple.pointApi.global.entity.Review;

import java.util.List;
import java.util.UUID;

public interface ReviewRepositroy extends JpaRepository<Review, UUID> {

    List<Review> findAllByPlace(Place place);
}
