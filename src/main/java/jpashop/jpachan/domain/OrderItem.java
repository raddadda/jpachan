package jpashop.jpachan.domain;


import jakarta.persistence.*;
import jpashop.jpachan.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderItem {
   @Id @GeneratedValue
   @Column(name = "orderItem_id")
   private Long id;

   @ManyToOne
   @JoinColumn(name = "item_id")
   private Item item;

   @ManyToOne
   @JoinColumn(name = "order_id")
   private Order order;

   private int orderPrice;
   private int count;
}
