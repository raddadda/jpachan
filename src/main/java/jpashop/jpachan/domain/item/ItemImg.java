package jpashop.jpachan.domain.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ItemImg {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;
    //이미지파일 경로
    String filepath;
    //이미지파일 이름
    String filename;
}

