package org.cook.lab9;

import org.cook.lab9.entity.CountryEntity;
import org.cook.lab9.entity.ItemEntity;
import org.cook.lab9.mapper.ItemMapper;
import org.cook.lab9.model.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ItemMapperTest {

    @Autowired
    private ItemMapper itemMapper;

    @Test
    void shouldReturnEntity(){
        CountryEntity country = new CountryEntity(123L, "Germany", "GER");

        Item model = new Item(123L, "Monitor Samsung", 10000, 5, 123L);

        ItemEntity entity = itemMapper.toEntity(model);
        entity.setManufacturer(country);

        assertNotNull(entity, "Result should not be null");
        assertNull(entity.getId(), "Id should be null");
        assertEquals(entity.getName(), model.getName(), "Names should be equal");
        assertEquals(entity.getPrice(), model.getPrice(), "Prices should be equal");
        assertEquals(entity.getQuantity(), model.getQuantity(), "Quantities should be equal");
        assertEquals(entity.getManufacturer().getId(), model.getManufacturerId(), "Manufacturer Id should be equal");
    }

    @Test
    void shouldReturnModel(){
        CountryEntity country = new CountryEntity(124L, "France", "FRA");
        ItemEntity entity = new ItemEntity(124L, "Monitor Samsung", 10000, 5, country);

        Item model = itemMapper.toModel(entity);

        assertNotNull(entity, "Result should not be null");
        assertEquals(entity.getId(), model.getId(), "Id should be equal");
        assertEquals(entity.getName(), model.getName(), "Names should be equal");
        assertEquals(entity.getPrice(), model.getPrice(), "Prices should be equal");
        assertEquals(entity.getQuantity(), model.getQuantity(), "Quantities should be equal");
        assertEquals(entity.getManufacturer().getId(), model.getManufacturerId(), "Manufacturer Id should be equal");
    }

    @Test
    void shouldReturnModelList(){
        CountryEntity countryEntity1 = new CountryEntity(234L, "Italy", "ITA");
        CountryEntity countryEntity2 = new CountryEntity(235L, "Belgium", "BLG");

        ItemEntity itemEntity1 = new ItemEntity(123L, "Item1", 123000, 123, countryEntity1);
        ItemEntity itemEntity2 = new ItemEntity(124L, "Item2", 124000, 124, countryEntity2);

        List<ItemEntity> entities = new ArrayList<>();
        entities.add(itemEntity1);
        entities.add(itemEntity2);

        List<Item> modelList = itemMapper.toModelList(entities);

        assertNotNull(modelList);
        assertEquals(2, modelList.size());

        Item checkItem = modelList.get(0);

        assertEquals(checkItem.getName(), itemEntity1.getName());
        assertEquals(checkItem.getPrice(), itemEntity1.getPrice());
        assertEquals(checkItem.getQuantity(), itemEntity1.getQuantity());
        assertEquals(checkItem.getManufacturerId(), itemEntity1.getManufacturer().getId());

        for (Item item: modelList){
            assertNotNull(item.getId());
            assertNotNull(item.getName());
            assertNotNull(item.getPrice());
            assertNotNull(item.getQuantity());
            assertNotNull(item.getManufacturerId());
            assertNotNull(item.getManufacturerId());
        }
    }

}
