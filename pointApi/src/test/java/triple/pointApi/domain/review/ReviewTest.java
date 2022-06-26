package triple.pointApi.domain.review;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import triple.pointApi.domain.place.repository.PlaceRepository;
import triple.pointApi.domain.review.dto.ReviewDto;
import triple.pointApi.domain.review.repository.ReviewRepositroy;
import triple.pointApi.domain.review.service.ReviewService;
import triple.pointApi.domain.user.repository.UserRepository;
import triple.pointApi.global.entity.Place;
import triple.pointApi.global.entity.Review;
import triple.pointApi.global.entity.User;


import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class ReviewTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private  ReviewRepositroy reviewRepositroy;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private UserRepository userRepository;


    // dto -- entity 아이디를 일치시켜야 한다.
    Long id = new Long(1);
    String[] photoIds = {"1" ,"2"};

    ReviewDto reviewDto = ReviewDto.builder()
            .reviewId(id)
            .placeId(id)
            .userId(id)
            .action("ADD")
            .content("좋아요")
            .type("REVIEW")
            .attachedPhotoIds(photoIds)
            .build();

    @BeforeEach
    public void setEntity(){
        Place place = Place.builder()
                .placeName("연남동")
                .build();
        User user = getUser();

        placeRepository.save(place);
        userRepository.save(user);
    }

    public User getUser(){
        User user = User.builder()
                .account("test")
                .password("test")
                .point(0)
                .build();
        return user;
    }



    @Test
    public void calculatePointTest(){
        int point = reviewService.calculateReviewPoint(reviewDto);
        assertThat(point).isEqualTo(3);
    }


    @Test
    public void createReviewTest(){
        reviewService.createReview(reviewDto);
        Review review = reviewRepositroy.findById(reviewDto.getReviewId())
                .orElseThrow(()-> new NullPointerException());
        assertThat(review.getReviewId()).isEqualTo(reviewDto.getReviewId());
    }



}
