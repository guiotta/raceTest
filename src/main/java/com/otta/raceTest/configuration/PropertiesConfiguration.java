package com.otta.raceTest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Classe de configuração de properties do projeto.
 * @author Guilherme
 *
 */
@Configuration
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
public class PropertiesConfiguration {
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
	  return new PropertySourcesPlaceholderConfigurer();
	}
}
