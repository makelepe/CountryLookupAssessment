package com.country.lookup.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.country.lookup.service.CountryLookupService;

/**
 * A REST Service class to expose functionality to query country by IP Address.
 * 
 *  @Param ipAddress string value
 *  @Return country code (i.e ZA) 
 * 
 * @author Samuel Radingwane <mailto: makelepe1@gmail.com>
 *
 */

@CrossOrigin
@RestController
@RequestMapping (path = "/lookup", produces = MediaType.APPLICATION_JSON_VALUE)
public class CountryLookupController {
	private static final Logger log = LoggerFactory.getLogger(CountryLookupController.class);
	
	@Autowired
	private CountryLookupService countryLookupService;
	
	@GetMapping("/ping")
    public String welcomeUser(@RequestParam(name="name", required=false, defaultValue="Radingwane") String name) {
		log.info("ping controller : " + name);
        return String.format("Welcome Mr/Mrs. %s!", name);
    }
	
	@GetMapping("/country")
    public ResponseEntity<String> getCountryByIP(@RequestParam(name="ipAddress", required=false) String ipAddress) {
        try {
			return new ResponseEntity<String>(countryLookupService.getCountryByIP(ipAddress), new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), new HttpHeaders(), HttpStatus.NO_CONTENT);
		}
    }
	
}
