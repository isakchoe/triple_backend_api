package triple.pointApi.domain.review.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import triple.pointApi.domain.review.dto.ReviewDto;
import triple.pointApi.domain.review.service.ReviewService;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    //post
    @PostMapping("/review")
    private void ReviewEvent(@RequestBody ReviewDto reviewDto){

        String action = reviewDto.getAction();

        reviewService.doReviewEvent(reviewDto);

    }


}
