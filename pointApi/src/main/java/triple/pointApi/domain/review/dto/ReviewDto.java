package triple.pointApi.domain.review.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class ReviewDto {

    private UUID reviewId;
    private String type;
    private String action;
    private String content;
    private String[] attachedPhotoIds;
    private UUID userId;
    private UUID placeId;
}
