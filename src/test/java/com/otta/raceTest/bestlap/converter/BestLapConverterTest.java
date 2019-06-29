package com.otta.raceTest.bestlap.converter;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.*;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.otta.raceTest.bestlap.model.BestLap;
import com.otta.raceTest.upload.model.FileData;
import com.otta.raceTest.upload.model.Lap;
import com.otta.raceTest.upload.model.Pilot;

@RunWith(MockitoJUnitRunner.class)
public class BestLapConverterTest {
	private static final String IDENTIFIER = "1";
	private static final String NAME = "Name";
	private static final int LAP_NUMBER1 = 1;
	
	private BestLapConverter converter;
	
	@Mock
	private FileData fileData;
	@Mock
	private Pilot pilot;
	@Mock
	private Lap lap1;
	@Mock
	private Lap lap2;
	private Duration duration1;
	private Duration duration2;

	@Before
	public void setUp() throws Exception {
		duration1 = Duration.ZERO;
		duration2 = Duration.ZERO;
		converter = new BestLapConverter();
		
		given(fileData.getPilot()).willReturn(pilot);
		given(fileData.getLaps()).willReturn(Lists.list(lap1, lap2));
		given(pilot.getIdentifier()).willReturn(IDENTIFIER);
		given(pilot.getName()).willReturn(NAME);
		given(lap1.getNumber()).willReturn(LAP_NUMBER1);
		given(lap1.getDuration()).willReturn(duration1);
		given(lap2.getDuration()).willReturn(duration2);
	}

	@Test
	public void shouldConvertValuesCorrectly() {
		//given
		//when
		BestLap actualValue = converter.convert(fileData);
		//then
		assertEquals(IDENTIFIER, actualValue.getPilotIdentifier());
		assertEquals(NAME, actualValue.getPilotName());
		assertEquals(LAP_NUMBER1, actualValue.getBestLapNumber());
		assertEquals(duration1, actualValue.getBestLapTime());
	}

	@Test(expected = NoSuchElementException.class)
	public void shouldThrowExceptionIfLapsCollectionIsEmpty() {
		//given
		given(fileData.getLaps()).willReturn(Lists.list());
		//when
		converter.convert(fileData);
		//then
	}
}
