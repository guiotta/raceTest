package com.otta.raceTest.timedelay.extractor;

import static org.junit.Assert.assertEquals;

import static org.mockito.BDDMockito.given;

import java.time.LocalTime;
import java.util.NoSuchElementException;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.otta.raceTest.upload.model.FileData;
import com.otta.raceTest.upload.model.Lap;

@RunWith(MockitoJUnitRunner.class)
public class EndRaceLocalTimeExtractorTest {
	private final int NUMBER_OF_LAPS1 = 1;
	private final int NUMBER_OF_LAPS2 = 2;

	@Mock
	private FileData fileData;
	@Mock
	private Lap lap1;
	@Mock
	private Lap lap2;
	private LocalTime time1;
	private LocalTime time2;
	
	private EndRaceLocalTimeExtractor extractor;

	@Before
	public void setUp() {
		extractor = new EndRaceLocalTimeExtractor();
		time1 = LocalTime.now();
		time2 = LocalTime.now();
		
		given(lap1.getNumber()).willReturn(Integer.valueOf(NUMBER_OF_LAPS1));
		given(lap2.getNumber()).willReturn(Integer.valueOf(NUMBER_OF_LAPS2));
		given(lap2.getTime()).willReturn(time1);
		given(fileData.getLaps()).willReturn(Lists.list(lap1, lap2));
	}

	@Test
	public void shouldExtractGreaterNumberOfLaps() {
		// given
		// when
		LocalTime actualValue = extractor.extract(fileData);
		// then
		assertEquals(time1, actualValue);
	}

	@Test(expected = NoSuchElementException.class)
	public void shouldThrowExceptionIfListOfLapsIsEmpty() {
		// given
		given(fileData.getLaps()).willReturn(Lists.list());
		// when
		LocalTime actualValue = extractor.extract(fileData);
		// then
	}
}
