package triple.pointApi.global.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "ACCOUNT")
    private String account;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "POINT")
    private int point;

    public void chagePoint(int point){
        this.point = point;
    }
}
