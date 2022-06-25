package triple.pointApi.global.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class History {

    @Id
    @Column(name = "HISTORY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;

    @Column(name = "PRE_POINT")
    private int prePoint;

    @Column(name = "NEW_VALUE")
    private int newValue;

    @Column(name = "RESULT_POINT")
    private int resultPoint;

    // 연관관계 매핑
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;


    // 연관관계 편의 메서드
    public void setUser(User user){
        this.user = user;
    }
}
