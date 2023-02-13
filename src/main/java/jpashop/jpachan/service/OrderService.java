package jpashop.jpachan.service;

import jpashop.jpachan.domain.*;
import jpashop.jpachan.domain.item.Item;
import jpashop.jpachan.repository.ItemRepository;
import jpashop.jpachan.repository.MemberRepository;
import jpashop.jpachan.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId,Long itemId, int count){
        //엔티티
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);
        //주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(),count);

        //유지보수를 위해

        //주문 생성
        Order order = Order.createOrder(member, delivery,orderItem);
        //주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    //주문취소

    @Transactional
    public void cancelOrder(Long orderId){
        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);

     //주문 취소
        order.cancel();
    }

    //검색
   /* pulbic List<Order> findOrders(OrderSearch orderSearch){

    }*/
}
