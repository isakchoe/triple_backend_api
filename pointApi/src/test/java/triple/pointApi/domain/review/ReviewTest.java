package triple.pointApi.domain.review;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import triple.pointApi.domain.review.dto.ReviewDto;
import triple.pointApi.domain.review.service.ReviewService;
import triple.pointApi.global.entity.Place;


import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class ReviewTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private EntityManager entityManager;

    Long placeId = new Long(1);
    Long reviewId = new Long(1);
    String[] photoIds = {"1" ,"2"};

    @BeforeEach
    public void setEntity(){
        Place place = Place.builder()
                .placeName("연남동")
                .build();
        entityManager.persist(place);
    }

    @Test
    public void calculatePointTest(){
        ReviewDto reviewDto = ReviewDto.builder()
                .reviewId(reviewId)
                .placeId(placeId)
                .action("ADD")
                .content("좋아요")
                .type("REVIEW")
                .attachedPhotoIds(photoIds)
                .build();

        int point = reviewService.calculateReviewPoint(reviewDto);
        assertThat(point).isEqualTo(3);
    }

}
