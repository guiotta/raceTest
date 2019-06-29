package com.otta.raceTest.result.converter;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.*;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.otta.raceTest.result.model.RaceResult;
import com.otta.raceTest.upload.model.FileData;
import com.otta.raceTest.upload.model.Lap;
import com.otta.raceTest.upload.model.Pilot;

@RunWith(MockitoJUnitRunner.class)
public class RaceResultConverterTest {
	private static final String IDENTIFIER = "1";
	private static final String NAME = "NAME";
	private static final int LAP_NUMBER = 1;
	private static final Double SPEED = Double.valueOf(44.275d);
	private static final String DURATION_VALUE = "1:02.852";
	private static final String TIME_VALUE = "23:49:08.277";

	private RaceResultConverter converter;
	
	@Mock
	private FileData fileData;
	@Mock
	private Pilot pilot;
	@Mock
	private Lap lap;
	
	private Duration duration;
	private LocalTime localtime;
	
	@Before
	public void setUp() throws Exception {
		converter = new RaceResultConverter();
		
		localtime = LocalTime.parse(TIME_VALUE, DateTimeFormatter.ISO_LOCAL_TIME);
		duration = Duration.ZERO;
		
		given(fileData.getPilot()).willReturn(pilot);
		given(fileData.getLaps()).willReturn(Lists.list(lap));
		given(pilot.getName()).willReturn(NAME);
		given(pilot.getIdentifier()).willReturn(IDENTIFIER);
		given(lap.getNumber()).willReturn(LAP_NUMBER);
		given(lap.getDuration()).willReturn(duration);
		given(lap.getTime()).willReturn(localtime);
		given(lap.getVelocity()).willReturn(SPEED);
	}
	
	@Test
	public void shouldCorrectlyConvert() {
		//given
		//when
		RaceResult actualValue = converter.convert(fileData);
		//then
		assertEquals(IDENTIFIER, actualValue.getPilotIdentifier());
		assertEquals(NAME, actualValue.getPilotName());
		assertEquals(LAP_NUMBER, actualValue.getCompleteLaps());
		assertEquals(SPEED, Double.valueOf(actualValue.getAverageSpeed()));
		assertEquals(duration, actualValue.getRaceDuration());
		assertEquals(localtime, actualValue.getEndRace());
	}

}
