package org.cook.lab9.mapper;

import org.cook.lab9.entity.ItemEntity;
import org.cook.lab9.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(target = "manufacturerId", source = "manufacturer.id")
    Item toModel(ItemEntity entity);

    @Mapping(target = "manufacturer", ignore = true)
    ItemEntity toEntity(Item model);

}
