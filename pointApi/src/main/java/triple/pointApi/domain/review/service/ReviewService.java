package triple.pointApi.domain.review.service;


import triple.pointApi.domain.review.dto.ReviewDto;
import triple.pointApi.domain.review.repository.ReviewRepositroy;
import triple.pointApi.domain.user.service.UserService;
import triple.pointApi.global.entity.Review;

public class ReviewService {

    private final ReviewRepositroy reviewRepository;

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
        Long userId = reviewDto.getUserId();

        Review review = Review.buidler()

        // review entity 빌드
        reviewRepository.save();

        // 포인트 계산 함수
        int point = getReviewPoint(reviewDto);

        // 포인트 적립 함수
        userService.addPoint(userId, point);

    }

    private int getReviewPoint(ReviewDto reviewDto){
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

    }


    private void updateReview(ReviewDto reviewDto){

    }

}
