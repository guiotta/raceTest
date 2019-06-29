package com.otta.raceTest.timedelay.converter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.*;

import java.time.LocalTime;
import java.util.Collection;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.otta.raceTest.timedelay.builder.EndRaceDelayBuilder;
import com.otta.raceTest.timedelay.builder.EndRaceTimeBuilder;
import com.otta.raceTest.timedelay.model.EndRaceDelay;
import com.otta.raceTest.timedelay.model.EndRaceTime;
import com.otta.raceTest.upload.model.FileData;

@RunWith(MockitoJUnitRunner.class)
public class EndRaceConverterTest {
	@Mock
	private EndRaceDelayBuilder endRaceDelayBuilder;

	@Mock
	private EndRaceTimeBuilder endRaceTimeBuilder;
	@InjectMocks
	private EndRaceConverter endRaceConverter;
	
	@Mock
	private FileData fileData1;
	@Mock
	private FileData fileData2;
	@Mock
	private EndRaceTime endRaceTime1;
	@Mock
	private EndRaceTime endRaceTime2;
	private LocalTime time1;
	private LocalTime time2;
	@Mock
	private EndRaceDelay endRaceDelay1;
	@Mock
	private EndRaceDelay endRaceDelay2;

	@Before
	public void setUp() throws Exception {
		time1 = LocalTime.MIDNIGHT;
		time2 = LocalTime.NOON;
		
		given(endRaceTimeBuilder.build(fileData1)).willReturn(endRaceTime1);
		given(endRaceTimeBuilder.build(fileData2)).willReturn(endRaceTime2);
		given(endRaceTime1.getTime()).willReturn(time1);
		given(endRaceTime2.getTime()).willReturn(time2);
		given(endRaceDelayBuilder.build(fileData1, time1)).willReturn(endRaceDelay1);
		given(endRaceDelayBuilder.build(fileData2, time1)).willReturn(endRaceDelay2);
	}

	@Test
	public void shouldConvertValuesCorrectly() throws Exception {
		//given
		//when
		Collection<EndRaceDelay> actualValues = endRaceConverter.convert(Lists.list(fileData1, fileData2));
		//then
		assertThat(actualValues, is(Lists.list(endRaceDelay1, endRaceDelay2)));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionIfCouldNotBuildEndRaceTime() throws Exception {
		//given
		//when
		Collection<EndRaceDelay> actualValues = endRaceConverter.convert(Lists.list());
		//then
	}
}
