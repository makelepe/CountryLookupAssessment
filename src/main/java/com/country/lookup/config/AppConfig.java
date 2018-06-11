package com.country.lookup.config;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

import com.maxmind.geoip.LookupService;

@Configuration
@ComponentScan("com.country.lookup")
public class AppConfig {
	private static final Logger log = LoggerFactory.getLogger(AppConfig.class);

	/**
	 * This will load LookupService, which in turn loads DATA, only once and store in spring context for server lifetime.  
	 */
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_APPLICATION)
    public LookupService pref() {
        try {
        	ClassLoader classLoader = getClass().getClassLoader();
        	File file = new File(classLoader.getResource("geoip/GeoIPLite.dat").getFile());
        	
        	// instantiate Lookup Service and load data from the data file
			return new LookupService(file);
		} catch (IOException e) {
			log.error("Error configuring GEOIP API : " + e.getMessage(), e);
        	// shutdown the server: this abnormally terminates the JVM since this service cannot operate without data source.
        	System.exit(1);
        }
		return null;
    }

}
