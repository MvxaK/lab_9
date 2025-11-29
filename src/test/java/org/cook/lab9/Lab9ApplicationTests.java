package org.cook.lab9;

import org.cook.lab9.model.Country;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Lab9ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testCountryCreation(){
		Country country = new Country(123L, "Germany", "GR");


	}
}
