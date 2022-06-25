package triple.pointApi.global.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @Column(name = "REVIEW_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "POINT")
    private int point;

    // 연관관계 매핑
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
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
