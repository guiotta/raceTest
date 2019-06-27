package com.otta.raceTest.timedelay.builder;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.*;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.otta.raceTest.timedelay.extractor.EndRaceLocalTimeExtractor;
import com.otta.raceTest.timedelay.model.EndRaceTime;
import com.otta.raceTest.upload.model.FileData;
import com.otta.raceTest.upload.model.Pilot;

@RunWith(MockitoJUnitRunner.class)
public class EndRaceTimeBuilderTest {
	private static final String IDENTIFIER = "1";
	private static final String NAME = "Name";
	
	@Mock
	private EndRaceLocalTimeExtractor endRaceLocalTimeExtractor;
	@InjectMocks
	private EndRaceTimeBuilder endRaceTimeBuilder;
	
	@Mock
	private FileData fileData;
	@Mock
	private Pilot pilot;
	private LocalTime time;

	@Before
	public void setUp() throws Exception {
		time = LocalTime.now();
		
		given(endRaceLocalTimeExtractor.extract(fileData)).willReturn(time);
		given(fileData.getPilot()).willReturn(pilot);
		given(pilot.getNumber()).willReturn(IDENTIFIER);
		given(pilot.getName()).willReturn(NAME);
	}
	
	@Test
	public void shouldBuildCorrectly() {
		//given
		//when
		EndRaceTime actuasValue = endRaceTimeBuilder.build(fileData);
		//then
		assertEquals(actuasValue.getTime(), time);
		assertEquals(actuasValue.getPilotIdentifier(), IDENTIFIER);
		assertEquals(actuasValue.getPilotName(), NAME);
	}

}
