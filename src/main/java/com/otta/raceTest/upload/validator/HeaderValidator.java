package com.otta.raceTest.upload.validator;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.otta.raceTest.upload.model.Header;

@Component
public class HeaderValidator {
	private static final String[] VALID_HEADERS = {
			"Hora",
			"Piloto",
			"Nº",
			"Volta",
			"Tempo",
			"Volta",
			"Velocidade",
			"média",
			"da",
			"volta"
			};
	
	public boolean validate(Header header) {
		Collection<String> validValues = Arrays.stream(VALID_HEADERS).collect(Collectors.toList());
		
		return (header.getTitles().size() == VALID_HEADERS.length)
				&& header.getTitles().containsAll(validValues);
	}
}
