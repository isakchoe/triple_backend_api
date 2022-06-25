package triple.pointApi.domain.review.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import triple.pointApi.domain.place.repository.PlaceRepository;
import triple.pointApi.domain.review.dto.ReviewDto;
import triple.pointApi.domain.review.repository.ReviewRepositroy;
import triple.pointApi.domain.user.repository.UserRepository;
import triple.pointApi.domain.user.service.UserService;
import triple.pointApi.global.entity.Place;
import triple.pointApi.global.entity.Review;
import triple.pointApi.global.entity.User;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepositroy reviewRepository;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;

    private final UserService userService;


    public void doReviewEvent(ReviewDto reviewDto){
        String action = reviewDto.getAction();
        if (action.equals("ADD")){
            createReview(reviewDto);
        }
        else if(action.equals("MOD")) {
            updateReview(reviewDto);
        }
        else{
            deleteReview(reviewDto);
        }
    }


    private void createReview(ReviewDto reviewDto){
        String contet = reviewDto.getContent();
        Long userId = reviewDto.getUserId();

        // 포인트 계산 함수
        int point = calculateReviewPoint(reviewDto);

        // 포인트 적립 함수
        userService.addPoint(userId, point);

        // review entity 빌드
        Review review = Review.builder()
                .reviewId(reviewDto.getReviewId())
                .content(contet)
                .point(point)
                .build();

        setUser(review, reviewDto);
        setPlace(review, reviewDto);

        reviewRepository.save(review);
    }

    private void setUser(Review review, ReviewDto reviewDto){
        Long userId = reviewDto.getUserId();
        User user = userRepository.findById(userId).
                orElseThrow(()-> new NullPointerException());

        review.setUser(user);
    }

    private void setPlace(Review review, ReviewDto reviewDto){
        Long placeId = reviewDto.getPlaceId();
        Place place = placeRepository.findById(placeId).
                orElseThrow(() -> new NullPointerException());
        review.setPlace(place);
    }


    private int calculateReviewPoint(ReviewDto reviewDto){
        String content = reviewDto.getContent();
        String[] attachedPhotoIds = reviewDto.getAttachedPhotoIds();
        Long placeId = reviewDto.getPlaceId();
        // null 이 아닌, 빈 배열이 나온다.
        Review[] reviews = reviewRepository.findAllByPlaceId(placeId);
        int total = 0;

        if (content.length() >=1) total+=1;
        if(attachedPhotoIds.length >= 1) total +=1;
        if (reviews.length == 0) total += 1;

        return total;
    }




    private void deleteReview(ReviewDto reviewDto){

        Long reviewId = reviewDto.getReviewId();
        Long userId = reviewDto.getUserId();

        Review review = reviewRepository.findById(reviewId).
                orElseThrow(()->new NullPointerException());

        // review find by user,plae id

        int point = -review.getPoint();

        userService.addPoint(userId, point);
        reviewRepository.delete(review);
    }


    private void updateReview(ReviewDto reviewDto){

        Long reviewId = reviewDto.getReviewId();
        Long userId = reviewDto.getUserId();
        int newPoint = calculateReviewPoint(reviewDto);

        Review review = reviewRepository.findById(reviewId).
                orElseThrow(()->new NullPointerException());

        // review find by user,plae id

        int prePoint = review.getPoint();
        int diffPoint = newPoint - prePoint;

        review.chagnePoint(newPoint);
        userService.addPoint(userId, diffPoint);
        reviewRepository.save(review);
    }

}
