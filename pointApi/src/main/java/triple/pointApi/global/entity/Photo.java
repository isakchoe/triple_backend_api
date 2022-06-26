package triple.pointApi.global.entity;

import javax.persistence.*;

@Entity
public class Photo {

    @Id
    @Column(name = "PHOTO_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long photoId;

    // 연관관계 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLACE_ID")
    private Place place;

}
