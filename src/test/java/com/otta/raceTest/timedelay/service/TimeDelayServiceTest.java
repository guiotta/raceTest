package com.otta.raceTest.timedelay.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.*;

import java.time.Duration;
import java.util.Collection;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

import com.otta.raceTest.timedelay.converter.EndRaceConverter;
import com.otta.raceTest.timedelay.model.EndRaceDelay;
import com.otta.raceTest.upload.converter.UploadFileConverter;
import com.otta.raceTest.upload.model.FileData;

@RunWith(MockitoJUnitRunner.class)
public class TimeDelayServiceTest {
	@Mock
	private EndRaceConverter endRaceConverter;

	@Mock
	private UploadFileConverter fileConverter;
	@InjectMocks
	private TimeDelayService timeDelayService;
	
	@Mock
	private MultipartFile file;
	@Mock
	private FileData fileData1;
	@Mock
	private FileData fileData2;
	@Mock
	private EndRaceDelay endRaceDelay1;
	@Mock
	private EndRaceDelay endRaceDelay2;
	private Duration duration1;
	private Duration duration2;

	@Before
	public void setUp() throws Exception {
		duration1 = Duration.ZERO;
		duration2 = Duration.ZERO;
		
		given(fileConverter.convert(file)).willReturn(Lists.list(fileData1, fileData2));
		given(endRaceConverter.convert(Lists.list(fileData1, fileData2))).willReturn(Lists.list(endRaceDelay1, endRaceDelay2));
		
		given(endRaceDelay1.getDelay()).willReturn(duration1);
		given(endRaceDelay2.getDelay()).willReturn(duration2);
	}

	@Test
	public void shouldConvertValuesCorrectly() throws Exception {
		//given
		//when
		Collection<EndRaceDelay> actualValues = timeDelayService.convertToEndRaceDelay(file);
		//then
		assertThat(actualValues, is(Lists.list(endRaceDelay1, endRaceDelay2)));
	}

}
