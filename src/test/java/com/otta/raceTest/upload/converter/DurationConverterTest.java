package com.otta.raceTest.upload.converter;

import static org.junit.Assert.assertEquals;

import java.time.Duration;

import org.junit.Before;
import org.junit.Test;

public class DurationConverterTest {
	private static final String VALID_DURATION = "00:00:000";
	private static final String INVALID_DURATION = "0210123";

	private DurationConverter durationConverter;

	@Before
	public void setUp() throws Exception {
		durationConverter = new DurationConverter();
	}

	@Test
	public void shouldCorrectlyConvertDurationValue() {
		// given
		// when
		Duration actualValue = durationConverter.convert(VALID_DURATION);
		// then
		assertEquals(Duration.ZERO, actualValue);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionIfDurationIsInvalid() {
		// given
		// when
		durationConverter.convert(INVALID_DURATION);
		// then
	}

}
