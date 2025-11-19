package org.cook.lab9.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.cook.lab9.entity.CountryEntity;
import org.cook.lab9.mapper.CountryMapper;
import org.cook.lab9.model.Country;
import org.cook.lab9.repository.CountryRepository;
import org.cook.lab9.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    public Country getCountryById(Long id) {
        return countryRepository.findById(id).map(countryMapper::toModel)
                .orElseThrow(() -> new EntityNotFoundException("There is no country with id -> " + id));
    }

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll().stream()
                .map(countryMapper::toModel)
                .toList();
    }

    @Override
    public Country createCountry(Country model) {
        CountryEntity country = countryMapper.toEntity(model);

        return countryMapper.toModel(countryRepository.save(country));
    }

    @Override
    public Country updateCountry(Long id, Country model) {
        CountryEntity country = countryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no country with id -> " + id));

        country.setName(model.getName());
        country.setCode(model.getCode());

        return countryMapper.toModel(countryRepository.save(country));
    }

    @Override
    public void deleteCountry(Long id) {
        if(!countryRepository.existsById(id)){
            throw new EntityNotFoundException("There is no country with id -> " + id);
        }

        countryRepository.deleteById(id);
    }
}
