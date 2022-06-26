package triple.pointApi.global.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @Column(columnDefinition = "BINARY(16)", name = "REVIEW_ID")
    private UUID reviewId;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "POINT")
    private int point;

    // 연관관계 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLACE_ID")
    private Place place;


    // 연관관계 편의메서드
    public void setUser(User user){
       this.user = user;
    }

    public void setPlace(Place place){
        this.place = place;
    }

    public void chagnePoint(int point) {
        this.point = point;
    }
}
