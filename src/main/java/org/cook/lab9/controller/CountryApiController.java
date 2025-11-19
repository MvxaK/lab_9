package org.cook.lab9.controller;

import lombok.RequiredArgsConstructor;
import org.cook.lab9.model.Country;
import org.cook.lab9.service.impl.CountryServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
public class CountryApiController {

    private final CountryServiceImpl countryService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getCountryById(@PathVariable Long id){
        Country country = countryService.getCountryById(id);

        return ResponseEntity.ok(country);
    }

    @GetMapping
    public ResponseEntity<?> getAllCountries(){
        List<Country> countries = countryService.getAllCountries();

        return ResponseEntity.ok(countries);
    }

    @PostMapping
    public ResponseEntity<?> createCountry(@RequestBody Country model){
        Country country = countryService.createCountry(model);

        return ResponseEntity.ok(country);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCountry(@PathVariable Long id, @RequestBody Country model){
        Country country = countryService.updateCountry(id, model);

        return ResponseEntity.ok(country);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCountry(@PathVariable Long id){
        countryService.deleteCountry(id);

        return ResponseEntity.noContent()
                .build();
    }

}
