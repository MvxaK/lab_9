package org.cook.lab9.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.cook.lab9.entity.CountryEntity;
import org.cook.lab9.entity.ItemEntity;
import org.cook.lab9.mapper.ItemMapper;
import org.cook.lab9.model.Item;
import org.cook.lab9.repository.CountryRepository;
import org.cook.lab9.repository.ItemRepository;
import org.cook.lab9.service.interfaces.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final CountryRepository countryRepository;
    private final ItemMapper itemMapper;

    @Override
    public Item getItemById(Long id) {
        return itemRepository.findById(id).map(itemMapper::toModel)
                .orElseThrow(() -> new EntityNotFoundException("There is no item with id -> " + id));
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll().stream()
                .map(itemMapper::toModel)
                .toList();
    }

    @Override
    public Item createItem(Item model) {
        ItemEntity item = itemMapper.toEntity(model);

        if(model.getManufacturerId() != null) {
            CountryEntity country = countryRepository.findById(model.getManufacturerId())
                    .orElseThrow(() -> new EntityNotFoundException("There is no country with id -> " + model.getManufacturerId()));

            item.setManufacturer(country);
        }else
            item.setManufacturer(null);

        return itemMapper.toModel(itemRepository.save(item));
    }

    @Override
    public Item updateItem(Long id, Item model) {
        ItemEntity item = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no item with id -> " + id));

        if(model.getManufacturerId() != null) {
            CountryEntity country = countryRepository.findById(model.getManufacturerId())
                    .orElseThrow(() -> new EntityNotFoundException("There is no country with id -> " + model.getManufacturerId()));

            item.setManufacturer(country);
        }else
            item.setManufacturer(null);

        item.setName(model.getName());
        item.setPrice(model.getPrice());
        item.setQuantity(model.getQuantity());

        return itemMapper.toModel(itemRepository.save(item));
    }

    @Override
    public void deleteItem(Long id) {
        if(!itemRepository.existsById(id)){
            throw new EntityNotFoundException("There is no item with id -> " + id);
        }

        itemRepository.deleteById(id);
    }

}
