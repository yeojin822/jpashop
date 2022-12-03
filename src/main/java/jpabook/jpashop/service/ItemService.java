package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
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
    public void saveItem(Item item) {
        itemRepository.save(item);
    }


    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Item findItem = itemRepository.findOne(itemId);
        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);

        //영속상태의 객체라 추가로 save를 호출할 필요가 없다 -> 자동으로 flush
    }

//    @Transactional
//    public void updateItem(Long itemId, Book book) {
//        Item findItem = itemRepository.findOne(itemId);
//        findItem.setPrice(book.getPrice());
//        findItem.setName(book.getName());
//        findItem.setStockQuantity(book.getStockQuantity());
//
//        //영속상태의 객체라 추가로 save를 호출할 필요가 없다 -> 자동으로 flush
//    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
