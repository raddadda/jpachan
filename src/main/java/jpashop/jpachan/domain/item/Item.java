package jpashop.jpachan.domain.item;

import jakarta.persistence.*;
//import jpashop.jpachan.domain.Category;
import jpashop.jpachan.domain.Category;
import jpashop.jpachan.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "dtype")
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name= "item_id")
    private Long id;

    private String name;
    //상품 가격
    private int price;
    //재고
    private int StockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
    @OneToMany(mappedBy = "item")
    private List<ItemImg> Img = new ArrayList<>();

    public void addStock(int quantity){
        this.StockQuantity +=  quantity;
    }

    //재고 감소
    public void removeStock(int quantity){
        int restStock=this.StockQuantity - quantity;
        if(restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.StockQuantity = restStock;
    }

}
