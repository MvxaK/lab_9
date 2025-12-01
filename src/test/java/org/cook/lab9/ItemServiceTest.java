package org.cook.lab9;

import jakarta.persistence.EntityNotFoundException;
import org.cook.lab9.model.Item;
import org.cook.lab9.service.interfaces.ItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Test
    void shouldReturnItemList(){
        List<Item> items = itemService.getAllItems();

        for(Item item: items){
            assertNotNull(item);
            assertNotNull(item.getName());
            assertNotNull(item.getPrice());
            assertNotNull(item.getQuantity());
            assertNotNull(item.getManufacturerId());
        }
    }

    @Test
    void shouldReturnItmById(){
        Random random = new Random();
        int randomIndex = random.nextInt(itemService.getAllItems().size());

        Long someItemId = itemService.getAllItems().get(randomIndex).getId();

        Item item = itemService.getItemById(someItemId);

        assertNotNull(item);
        assertNotNull(item.getName());
        assertNotNull(item.getPrice());
        assertNotNull(item.getQuantity());
        assertNotNull(item.getManufacturerId());
    }

    @Test
    void shouldCreateItem(){
        Item item = new Item(123L, "Acer nitro 5", 500000, 50, 1L);

        Item createdItem = itemService.createItem(item);

        assertNotNull(createdItem);
        assertNotNull(createdItem.getName());
        assertNotNull(createdItem.getPrice());
        assertNotNull(createdItem.getQuantity());
        assertNotNull(createdItem.getManufacturerId());

        assertEquals(item.getName(), createdItem.getName());
        assertEquals(item.getPrice(), createdItem.getPrice());
        assertEquals(item.getQuantity(), createdItem.getQuantity());
        assertEquals(item.getManufacturerId(), createdItem.getManufacturerId());

        Item checkItem = itemService.getItemById(createdItem.getId());

        assertNotNull(checkItem);
        assertEquals(checkItem.getId(), createdItem.getId());
        assertEquals(checkItem.getName(), createdItem.getName());
        assertEquals(checkItem.getPrice(), createdItem.getPrice());
        assertEquals(checkItem.getQuantity(), createdItem.getQuantity());
        assertEquals(checkItem.getManufacturerId(), createdItem.getManufacturerId());

    }

    @Test
    void shouldUpdateItem(){
        Random random = new Random();
        int randomIndex = random.nextInt(itemService.getAllItems().size());

        Long someItemIndex = itemService.getAllItems().get(randomIndex).getId();

        Item item = new Item(123L, "Acer nitro 5", 500000, 50, 1L);

        Item updatedItem = itemService.updateItem(someItemIndex, item);

        assertNotNull(updatedItem);
        assertNotNull(updatedItem.getId());
        assertNotNull(updatedItem.getName());
        assertNotNull(updatedItem.getPrice());
        assertNotNull(updatedItem.getQuantity());
        assertNotNull(updatedItem.getManufacturerId());

        assertEquals(updatedItem.getName(), item.getName());
        assertEquals(updatedItem.getPrice(), item.getPrice());
        assertEquals(updatedItem.getQuantity(), item.getQuantity());
        assertEquals(updatedItem.getManufacturerId(), item.getManufacturerId());

        assertThrows(EntityNotFoundException.class, () -> {
            Item updatedItem2 = itemService.updateItem(-1L, item);
        });
    }

    @Test
    void shouldDeleteItem(){
        Random random = new Random();
        int randomIndex = random.nextInt(itemService.getAllItems().size());

        Long someItemId = itemService.getAllItems().get(randomIndex).getId();

        itemService.deleteItem(someItemId);

        assertThrows(EntityNotFoundException.class, () -> {
            itemService.deleteItem(someItemId);
        });

        assertThrows(EntityNotFoundException.class, () -> {
            itemService.deleteItem(-1L);
        });
    }

}
