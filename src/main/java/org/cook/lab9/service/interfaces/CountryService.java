package org.cook.lab9.service.interfaces;

import org.cook.lab9.model.Country;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CountryService {

    Country getCountryById(Long id);
    List<Country> getAllCountries();
    Country createCountry(Country model);
    Country updateCountry(Long id, Country model);
    void deleteCountry(Long id);

}
