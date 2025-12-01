package org.cook.lab9;

import jakarta.persistence.EntityNotFoundException;
import org.cook.lab9.model.Country;
import org.cook.lab9.service.impl.CountryServiceImpl;
import org.junit.jupiter.api.Assertions;
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
public class CountryServiceTest {

    @Autowired
    private CountryServiceImpl countryService;

    @Test
    void shouldReturnCountryList(){
        List<Country> countries = countryService.getAllCountries();

        for (Country country: countries){
            assertNotNull(country);
            assertNotNull(country.getId());
            assertNotNull(country.getName());
            assertNotNull(country.getCode());
        }
    }

    @Test
    void shouldReturnCountryById(){
        Random random = new Random();
        int randomIndex = random.nextInt(countryService.getAllCountries().size());

        Long randomCountryId = countryService.getAllCountries().get(randomIndex).getId();

        Country country = countryService.getCountryById(randomCountryId);

        assertNotNull(country, "Result should not be null");
        assertNotNull(country.getId(), "Id should not be null");
        assertNotNull(country.getName(), "Name should not be null");
        assertNotNull(country.getCode(), "Code should not be null");

    }

    @Test
    void shouldCreateCountry(){
        Country country = new Country(123L, "Germany", "GER");

        Country createdCountry = countryService.createCountry(country);

        assertNotNull(createdCountry);

        assertNotNull(createdCountry.getId());
        Assertions.assertNotEquals(0L, createdCountry.getId());

        assertNotNull(createdCountry.getName());
        assertNotNull(createdCountry.getCode());

        assertEquals(createdCountry.getName(), country.getName());
        assertEquals(createdCountry.getCode(), country.getCode());

        Country checkCountry = countryService.getCountryById(createdCountry.getId());

        assertNotNull(checkCountry);
        assertNotNull(checkCountry.getId());
        assertNotNull(checkCountry.getName());
        assertNotNull(checkCountry.getCode());

        assertEquals(checkCountry.getId(), createdCountry.getId());
        assertEquals(checkCountry.getName(), createdCountry.getName());
        assertEquals(checkCountry.getCode(), createdCountry.getCode());

    }

    @Test
    void shouldUpdateCountry(){
        Random random = new Random();
        int randomIndex = random.nextInt(countryService.getAllCountries().size());

        Long someCountryId = countryService.getAllCountries().get(randomIndex).getId();

        Country country = new Country(123L, "Germany2", "GER2");

        Country updatedCountry = countryService.updateCountry(someCountryId, country);

        assertNotNull(updatedCountry);

        assertNotNull(updatedCountry.getId());
        assertNotNull(updatedCountry.getName());
        assertNotNull(updatedCountry.getCode());

        assertEquals(updatedCountry.getName(), country.getName());
        assertEquals(updatedCountry.getCode(), country.getCode());

        Country checkCountry = countryService.getCountryById(updatedCountry.getId());

        assertNotNull(checkCountry);
        assertNotNull(checkCountry.getId());
        assertNotNull(checkCountry.getName());
        assertNotNull(checkCountry.getCode());

        assertEquals(checkCountry.getId(), updatedCountry.getId());
        assertEquals(checkCountry.getName(), updatedCountry.getName());
        assertEquals(checkCountry.getCode(), updatedCountry.getCode());

        assertThrows(EntityNotFoundException.class, () -> {
            Country mockCountry = countryService.updateCountry(-1L, country);
        });
    }

    @Test
    void shouldDeleteCountry(){
        Random random = new Random();
        int randomIndex = random.nextInt(countryService.getAllCountries().size());

        Long someCountryId = countryService.getAllCountries().get(randomIndex).getId();

        countryService.deleteCountry(someCountryId);

        assertThrows(EntityNotFoundException.class, () -> {
           Country country = countryService.getCountryById(someCountryId);
        });

        assertThrows(EntityNotFoundException.class, () -> {
           countryService.deleteCountry(someCountryId);
        });
    }
}
