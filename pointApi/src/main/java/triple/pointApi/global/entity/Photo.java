package triple.pointApi.global.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Photo {

    @Id
    @Column(name = "PHOTO_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID photoId;

    // 연관관계 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLACE_ID")
    private Place place;

}
