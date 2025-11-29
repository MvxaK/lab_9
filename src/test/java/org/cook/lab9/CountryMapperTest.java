package org.cook.lab9;

import org.cook.lab9.entity.CountryEntity;
import org.cook.lab9.mapper.CountryMapper;
import org.cook.lab9.model.Country;
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
public class CountryMapperTest {

    @Autowired
    private CountryMapper countryMapper;

    @Test
    void shouldReturnEntity(){
        Country model = new Country(123L, "Germany", "GER");

        CountryEntity entity = countryMapper.toEntity(model);

        assertNotNull(entity, "Result should not be null");
        assertNull(entity.getId(), "Id should be null");
        assertEquals(model.getName(), entity.getName(), "Names should be equal");
        assertEquals(model.getCode(), entity.getCode(), "Codes should be equal");
    }

    @Test
    void shouldReturnModel() {
        CountryEntity entity = new CountryEntity(124L, "France", "FRA");

        Country model = countryMapper.toModel(entity);

        assertNotNull(entity, "Result should not be null");
        assertEquals(entity.getId(), model.getId(), "Id should be equal");
        assertEquals(entity.getName(), model.getName(), "Names should be equal");
        assertEquals(entity.getCode(), model.getCode(), "Codes should be equal");
    }

    @Test
    void shouldReturnModelList() {
        CountryEntity entity1 = new CountryEntity(234L, "Italy", "ITA");
        CountryEntity entity2 = new CountryEntity(235L, "Belgium", "BLG");
        List<CountryEntity> entities = new ArrayList<>();
        entities.add(entity1);
        entities.add(entity2);

        List<Country> modelList = countryMapper.toModelList(entities);

        assertNotNull(modelList, "List should not be null");
        assertEquals(2, modelList.size(), "Size should be 2");

        Country model1 = modelList.get(0);

        assertEquals(entity1.getName(), model1.getName(), "Names should be equal");
        assertEquals(entity1.getCode(), model1.getCode(), "Codes should be equal");
    }

}
