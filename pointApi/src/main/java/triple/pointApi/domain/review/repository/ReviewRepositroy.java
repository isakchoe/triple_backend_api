package triple.pointApi.domain.review.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import triple.pointApi.global.entity.Review;

public interface ReviewRepositroy extends JpaRepository<Review, Long> {

    Review[] findAllByPlaceId(Long placeId);
}
