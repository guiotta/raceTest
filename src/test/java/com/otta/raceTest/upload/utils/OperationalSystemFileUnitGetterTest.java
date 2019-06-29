package com.otta.raceTest.upload.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class OperationalSystemFileUnitGetterTest {
	private static final String EXPECTED_VALUE_WINDOWS = "C://";
	private static final String EXPECTED_VALUE_LINUX = "//home//";
	
	private OperationalSystemFileUnitGetter getter;

	@Before
	public void setUp() throws Exception {
		getter = new OperationalSystemFileUnitGetter();
	}

	@Test
	public void shouldGetCorrectValueForWindows() throws Exception {
		//given
		//when
		String actualValue = getter.get(true);
		//then
		assertEquals(EXPECTED_VALUE_WINDOWS, actualValue);
	}
	
	@Test
	public void shouldGetCorrectValueForLinux() throws Exception {
		//given
		//when
		String actualValue = getter.get(false);
		//then
		assertEquals(EXPECTED_VALUE_LINUX, actualValue);
	}

}
