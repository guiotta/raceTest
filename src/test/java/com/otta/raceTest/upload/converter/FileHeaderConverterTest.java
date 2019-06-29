package com.otta.raceTest.upload.converter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

public class FileHeaderConverterTest {
	private static final String VALID_STRING = "a b";
	private static final String A_VALUE = "a";
	private static final String B_VALUE = "b";

	private FileHeaderConverter converter;

	@Before
	public void setUp() {
		converter = new FileHeaderConverter();
	}

	@Test
	public void shouldCorrectlyConvertValues() {
		// given
		// when
		Collection<String> actualValues = converter.convert(VALID_STRING);
		// then
		assertThat(actualValues, is(Lists.list(A_VALUE, B_VALUE)));
	}

	@Test
	public void shouldReturnEMptyListIfStringIsEmpty() {
		// given
		// when
		Collection<String> actualValues = converter.convert(StringUtils.EMPTY);
		// then
		assertThat(actualValues, is(Lists.list()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionIfStringIsNull() {
		// given
		// when
		converter.convert(null);
		// then
	}
}
