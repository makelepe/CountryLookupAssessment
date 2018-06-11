package com.country.lookup.app;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.country.lookup.service.CountryLookupService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryLookupServiceTest {

	@Autowired
	private CountryLookupService countryLookupService;

	@Test
	public void testCountryByIP() {
		
		try {
			String country = countryLookupService.getCountryByIP("000.00.00.00");
			assertTrue ("Invalid IP address returned valid country", country.equalsIgnoreCase("Not Found"));

			country = countryLookupService.getCountryByIP("000.00.00.00");
			assertTrue ("Valid IP address returned Not Found", country.equalsIgnoreCase("Not Found"));
		} catch (Exception e) {
			
			if (e instanceof NullPointerException) {
				assertTrue ("Message Not null", e.getMessage() == null);
			}
			
			assertTrue("Unknown Exception caught", false);
		}

	}
	
}
