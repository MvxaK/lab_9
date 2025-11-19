package org.cook.lab9.service;

import org.cook.lab9.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {

    Item getItemById(Long id);
    List<Item> getAllItems();
    Item createItem(Item model);
    Item updateItem(Long id, Item model);
    void deleteItem(Long id);

}
