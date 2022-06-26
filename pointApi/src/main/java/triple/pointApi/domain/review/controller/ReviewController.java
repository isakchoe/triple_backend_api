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
    private String ReviewEvent(@RequestBody ReviewDto reviewDto){
        reviewService.doReviewEvent(reviewDto);
        String success = "{success : ok}";

        return success;
    }


}
