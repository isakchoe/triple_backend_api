package triple.pointApi.global.entity;


import javax.persistence.*;

@Entity
public class History {

    @Id
    @Column(name = "HISTORY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;

    @Column(name = "PRE_POINT")
    private int prePoint;

    @Column(name = "NEW_VALUE")
    private int newVAlue;

    @Column(name = "RESULT_POINT")
    private int resultPoint;


    // 연관관계 매핑
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;


}
