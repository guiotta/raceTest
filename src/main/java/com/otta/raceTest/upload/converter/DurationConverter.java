package com.otta.raceTest.upload.converter;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class DurationConverter {
	private static final String REGEX_TO_DURATION = "((\\d{1,2}):(\\d{2}).(\\d{3}))";

	public Duration convert(String duration) {
		Pattern pattern = Pattern.compile(REGEX_TO_DURATION);
		Matcher matcher = pattern.matcher(duration);

		if (matcher.matches()) {
			int minutes = Integer.parseInt(matcher.group(2));
			int seconds = Integer.parseInt(matcher.group(3));
			int milliseconds = Integer.parseInt(matcher.group(4));

			int valueToConvert = (((minutes * 60) + seconds) * 1000) + milliseconds;
			return Duration.ofMillis(valueToConvert);
		}

		throw new IllegalArgumentException(
				String.format("Duração com valor inválido para ser convertido: %s.", duration));
	}
}
