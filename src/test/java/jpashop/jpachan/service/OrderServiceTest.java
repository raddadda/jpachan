package jpashop.jpachan.service;

import jakarta.persistence.EntityManager;
import jpashop.jpachan.domain.Address;
import jpashop.jpachan.domain.Member;
import jpashop.jpachan.domain.Order;
import jpashop.jpachan.domain.OrderStatus;
import jpashop.jpachan.domain.item.Item;
import jpashop.jpachan.domain.item.Shoes;
import jpashop.jpachan.exception.NotEnoughStockException;
import jpashop.jpachan.repository.OrderRepository;
import org.assertj.core.api.SoftAssertionsProvider;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertEquals;


@SpringBootTest
@Transactional
public class OrderServiceTest{
    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception{
        //given
        Member member = createMember();

        Shoes shoes = createshoes("뉴발란스 574",70000,100);
        /*member.setName("최아무개");
        member.setAddress(new Address("서울","갈월동","7-26"));
        em.persist(member);

        Shoes shoes = new Shoes();
        shoes.setName("뉴발란스 574");
        shoes.setPrice(70000);
        shoes.setStockQuantity(100);
        em.persist(shoes);*/

        int orderCount=2;

        //when
        Long orderId = orderService.order(member.getId(), shoes.getId(), orderCount);
        //then
        Order getOrder = orderRepository.findOne(orderId);
        //예외가 발생하면 왼쪽 메시지를 출력한다.
        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.",1,getOrder.getOrderItems().size());
        assertEquals("주문 가격은 가격 * 수량이다.",70000 * orderCount, getOrder.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야 한다.",98,shoes.getStockQuantity());
    }



    @Test
    public void 상품주문_재고수량초과() throws Exception {
        //given
        Member member = createMember();

        Item item = createshoes("뉴발란스 574", 70000, 100);

        int orderCount = 101;
        //when
        NotEnoughStockException exception =
                assertThrows(NotEnoughStockException.class, () -> {
                    orderService.order(member.getId(), item.getId(), orderCount);
                });

        orderService.order(member.getId(), item.getId(), orderCount);
        //then
        fail("재고 수량 부족 예외가 발행해야 한다.");
    }

    private Shoes createshoes(String name,int price, int stockQuantitiy){
        Shoes shoes = new Shoes();
        shoes.setName(name);
        shoes.setPrice(price);
        shoes.setStockQuantity(stockQuantitiy);
        em.persist(shoes);
        return shoes;
    }

    private Member createMember(){
        Member member = new Member();
        member.setName("최아무개");
        member.setAddress(new Address("서울","갈월동","7-76"));
        em.persist(member);
        return member;
    }

    @Test
    public void 주문취소() throws Exception{

        //테스트에서 getter를 안쓰는 이유는?
        //given
        Member member = createMember();
        Shoes item = createshoes("뉴발란스 574",70000,100);
        int orderCount= 2;

        Long orderId = orderService.order(member.getId(), item.getId(),orderCount);

        //when
        orderService.cancelOrder(orderId);
        //then
        Order getOrder=  orderRepository.findOne(orderId);

        assertEquals("주문 취소시 상태는 CANCEL 이다.", OrderStatus.CANCEL,getOrder.getStatus());
        assertEquals("주문이 취소된 상품은 그만큼 재고가 증가해야 한다.", 100 , item.getStockQuantity());
    }


}
