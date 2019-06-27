package com.otta.raceTest.timedelay.builder;

import static org.junit.Assert.assertEquals;

import static org.mockito.BDDMockito.given;

import java.time.Duration;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.otta.raceTest.timedelay.model.EndRaceDelay;
import com.otta.raceTest.timedelay.model.EndRaceTime;
import com.otta.raceTest.upload.model.FileData;
import com.otta.raceTest.upload.model.Pilot;

@RunWith(MockitoJUnitRunner.class)
public class EndRaceDelayBuilderTest {
	private static final String IDENTIFIER = "1";
	private static final String NAME = "Name";
	
	@Mock
	private EndRaceTimeBuilder endRaceTimeBuilder;
	@InjectMocks
	private EndRaceDelayBuilder endRaceDelayBuilder;
	
	@Mock
	private FileData fileData;
	private LocalTime raceTime;
	private LocalTime endPilotTime;
	@Mock
	private Pilot pilot;
	@Mock
	private EndRaceTime endRaceTime;
	
	

	@Before
	public void setUp() throws Exception {
		raceTime = LocalTime.now();
		endPilotTime = LocalTime.now();
		
		given(endRaceTimeBuilder.build(fileData)).willReturn(endRaceTime);
		given(endRaceTime.getTime()).willReturn(endPilotTime);
		given(fileData.getPilot()).willReturn(pilot);
		given(pilot.getNumber()).willReturn(IDENTIFIER);
		given(pilot.getName()).willReturn(NAME);
	}

	@Test
	public void shouldBuildCorrectly() throws Exception {
		//given
		//when
		EndRaceDelay actualValue = endRaceDelayBuilder.build(fileData, raceTime);
		//then
		assertEquals(actualValue.getPilotIdentifier(), IDENTIFIER);
		assertEquals(actualValue.getPilotName(), NAME);
		assertEquals(actualValue.getDelay(), Duration.between(raceTime, endPilotTime));
	}

}
