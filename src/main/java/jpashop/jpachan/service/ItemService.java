package jpashop.jpachan.service;

import jpashop.jpachan.domain.item.Item;
import jpashop.jpachan.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

        private final ItemRepository itemRepository;

        @Transactional
        public void saveITem(Item item){
            itemRepository.save(item);
        }
        public List<Item> findItems(){
            return itemRepository.findAll();
        }
        public Item findOne(Long itemId){
            return itemRepository.findOne(itemId);
        }

}
