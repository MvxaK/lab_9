package org.cook.lab9.mapper;

import org.cook.lab9.entity.CountryEntity;
import org.cook.lab9.model.Country;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    Country toModel(CountryEntity entity);
    CountryEntity toEntity(Country model);

}
