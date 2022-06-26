package triple.pointApi.domain.review.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import triple.pointApi.domain.history.service.HistoryService;
import triple.pointApi.domain.place.service.PlaceService;
import triple.pointApi.domain.review.dto.ReviewDto;
import triple.pointApi.domain.review.repository.ReviewRepositroy;
import triple.pointApi.domain.user.service.UserService;
import triple.pointApi.global.entity.History;
import triple.pointApi.global.entity.Place;
import triple.pointApi.global.entity.Review;
import triple.pointApi.global.entity.User;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepositroy reviewRepository;

    private final UserService userService;
    private final PlaceService placeService;
    private final HistoryService historyService;


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


    public void createReview(ReviewDto reviewDto){
        String content = reviewDto.getContent();
        UUID userId = reviewDto.getUserId();
        UUID placeId = reviewDto.getPlaceId();
        UUID reviewId = reviewDto.getReviewId();

        User user = userService.getUser(userId);
        Place place = placeService.getPlace(placeId);

        // 포인트 계산 함수
        int point = calculateReviewPoint(reviewDto);

        // 포인트 적립 함수
        userService.addPoint(userId, point);

        // review entity 빌드
        Review review = Review.builder()
                .reviewId(reviewId)
                .content(content)
                .point(point)
                .build();

        review.setUser(user);
        review.setPlace(place);
        reviewRepository.save(review);

        //history build
        History history = History.builder()
                        .prePoint(0)
                        .newValue(point)
                        .resultPoint(point)
                        .build();

        historyService.saveHistory(history,user);
    }


    public int
    calculateReviewPoint(ReviewDto reviewDto){
        String content = reviewDto.getContent();
        String[] attachedPhotoIds = reviewDto.getAttachedPhotoIds();
        UUID placeId = reviewDto.getPlaceId();
        UUID reviewId = reviewDto.getReviewId();

        // 네이밍?
        Place place = placeService.getPlace(placeId);
        // null 이 아닌, 빈 배열이 나온다.
        List<Review> reviews = reviewRepository.findAllByPlace(place);
        int total = 0;

        if (content.length() >=1) total+=1;
        if(attachedPhotoIds.length >= 1) total +=1;
        if (reviews.size() == 0) total += 1;
        if (reviews.size() == 1){
            if(reviews.get(0).getReviewId().equals(reviewId)){
                total +=1;
            }
        }

        return total;
    }




    public void deleteReview(ReviewDto reviewDto){
//        UUID reviewId = reviewDto.getReviewId();
        UUID userId = reviewDto.getUserId();

        int reviewPoint = - calculateReviewPoint(reviewDto);


//        Review review = reviewRepository.findById(reviewId).
//                orElseThrow(()->new NullPointerException());

        // review find by user,plae id

//        int point = -review.getPoint();

        userService.addPoint(userId, reviewPoint);
//        reviewRepository.delete(review);
    }


    public void updateReview(ReviewDto reviewDto){

        UUID reviewId = reviewDto.getReviewId();
        UUID userId = reviewDto.getUserId();
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
