package triple.pointApi.domain.review.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ReviewDto {

    private Long reviewId;
    private String type;
    private String action;
    private String content;
    private String[] attachedPhotoIds;
    private Long userId;
    private Long placeId;
}
