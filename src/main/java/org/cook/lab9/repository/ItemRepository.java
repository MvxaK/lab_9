package org.cook.lab9.repository;

import org.cook.lab9.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    List<ItemEntity> findAllByManufacturerId(Long manufacturer_id);
}
