package jpashop.jpachan.repository;

import jakarta.persistence.EntityManager;
import jpashop.jpachan.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;
}
