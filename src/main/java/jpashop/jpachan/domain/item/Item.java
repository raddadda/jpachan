package jpashop.jpachan.domain.item;

import java.util.ArrayList;
import java.util.List;

public class Item {
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    private List<ItemImg> Img = new ArrayList<>();
}
