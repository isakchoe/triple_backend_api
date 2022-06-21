package triple.pointApi.global.entity;

import lombok.Getter;

import javax.persistence.*;


@Entity
@Getter
public class Review {

    @Id
    @Column(name = "REVIEW_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String reviewId;

    @Column(name = "CONTENT")
    private String content;

    // 연관관계 매핑
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "PLACE_ID")
    private Place place;


    // 연관관계 편의메서드
}
