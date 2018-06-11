package com.country.lookup.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages = {"com.country.lookup"})
public class CountryLookupAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(CountryLookupAssessmentApplication.class, args);
	}
}
