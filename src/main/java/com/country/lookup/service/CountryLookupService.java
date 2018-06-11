package com.country.lookup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxmind.geoip.Country;
import com.maxmind.geoip.LookupService;

/**
 * A class responsible for determining the country by IP with the help of GeoIP API.
 * 
 * @author Samuel Radingwane
 *
 */

@Service
public class CountryLookupService {
	
	@Autowired
	private LookupService lookupService;

	/**
	 * query GeoIP for country by IP Address
	 * 
	 * @param ipAddress
	 * @return country name formatted -- South Africa [ZA]
	 * @throws Exception if country is not available or general failure
	 */
	public String getCountryByIP (String ipAddress) throws Exception {
		
		try {
			// fetch country by IP Address from GeoIP API
			Country country = lookupService.getCountry(ipAddress);
			
			if (country.getName().equalsIgnoreCase("N/A")) {
				return "Not Found";
			} 
			
			return country.getName() + "["+country.getCode()+"]";
			
		} catch (Exception e) {
			// For NullPointer customize the message
			if (e instanceof NullPointerException) {
				throw new NullPointerException("Not Found");
			}
			// re throw exception
			throw e;
		}
	}
	
}
