package jpashop.jpachan.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue

    @Column(name = "member_id")
    private Long id;

    private String name;

    private String nickname;

    private String password;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "member")
    private List<Order> orders;

    /*public Member(){
        orders = new ArrayList<>();
    }*/
}