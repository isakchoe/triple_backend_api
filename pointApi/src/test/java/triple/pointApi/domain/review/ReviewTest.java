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
import java.util.UUID;

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
    String[] photoIds = {"1" ,"2"};
    String[] emptyPhotoIds = {};

    User user = getUser();

    ReviewDto reviewCreateDto;
    ReviewDto reviewModDto;

    UUID reviewId = UUID.randomUUID();
    UUID placeId;
    UUID userId;


    @BeforeEach
    public void setEntity(){
        Place place = Place.builder()
                .placeName("연남동")
                .build();
        // save
        placeRepository.save(place);
        userRepository.save(user);

        // id 값 가져오기
        userId = user.getUserId();
        placeId =  place.getPlaceId();

        // 테스트 dto 2개 생성
        reviewCreateDto =  getReviewDto( "ADD", photoIds);
        reviewModDto = getReviewDto( "MOD", emptyPhotoIds);
    }

    public ReviewDto getReviewDto(String action, String[] photoIds){
        ReviewDto reviewDto = ReviewDto.builder()
                .reviewId(reviewId)
                .placeId(placeId)
                .userId(userId)
                .action(action)
                .content("좋아요")
                .type("REVIEW")
                .attachedPhotoIds(photoIds)
                .build();
        return reviewDto;
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
        int point = reviewService.calculateReviewPoint(reviewCreateDto);
        assertThat(point).isEqualTo(3);
    }


    @Test
    public void createReviewTest(){
        reviewService.createReview(reviewCreateDto);
        Review review = reviewRepositroy.findById(reviewCreateDto.getReviewId())
                .orElseThrow(()-> new NullPointerException());

        int point = user.getPoint();

        assertThat(point).isEqualTo(3);
        assertThat(review.getReviewId()).isEqualTo(reviewCreateDto.getReviewId());
    }


    @Test
    public void deleteReviewTest(){
        //given
        int review_point = reviewService.calculateReviewPoint(reviewCreateDto);
        reviewService.createReview(reviewCreateDto);
        int prePoint = user.getPoint();

        // when
        reviewService.deleteReview(reviewCreateDto);
        int newPoint = user.getPoint();

        // then
        int diff = prePoint - newPoint;
        assertThat(diff).isEqualTo(review_point);
    }

    @Test
    public void updateReviewTest(){
        //given
        reviewService.createReview(reviewCreateDto);
        int prePoint = user.getPoint();

        //when
        reviewService.updateReview(reviewModDto);
        int newPoint = user.getPoint();

        //then
        int diff = Math.abs(prePoint - newPoint);
        assertThat(diff).isEqualTo(1);
    }



}
