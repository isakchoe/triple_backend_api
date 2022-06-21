package triple.pointApi.global.entity;


import javax.persistence.*;

@Entity
public class Place {

    @Id
    @Column(name = "PLACE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String placeId;

    @Column(name = "PLACE_NAME")
    private String placeName;

}
