package org.cook.lab9.mapper;

import org.cook.lab9.entity.CountryEntity;
import org.cook.lab9.model.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    Country toModel(CountryEntity entity);

    @Mapping(target = "id", ignore = true)
    CountryEntity toEntity(Country model);

    List<Country> toModelList(List<CountryEntity> entities);

}
