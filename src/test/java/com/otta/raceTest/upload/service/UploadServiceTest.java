package com.otta.raceTest.upload.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.*;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Collection;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

import com.otta.raceTest.result.converter.RaceResultConverter;
import com.otta.raceTest.result.model.RaceResult;
import com.otta.raceTest.upload.converter.UploadFileConverter;
import com.otta.raceTest.upload.model.FileData;

@RunWith(MockitoJUnitRunner.class)
public class UploadServiceTest {
	@Mock
	private UploadFileConverter fileConverter;
	@Mock
	private RaceResultConverter raceResultConverter;
	@InjectMocks
	private UploadService uploadService;

	@Mock
	private MultipartFile file;
	@Mock
	private FileData fileData1;
	@Mock
	private FileData fileData2;
	@Mock
	private RaceResult raceResult1;
	@Mock
	private RaceResult raceResult2;

	private LocalTime localTime1;
	private LocalTime localTime2;

	@Before
	public void setUp() throws Exception {
		localTime1 = LocalTime.NOON;
		localTime2 = LocalTime.NOON;

		given(fileConverter.convert(file)).willReturn(Lists.list(fileData1, fileData2));
		given(raceResultConverter.convert(fileData1)).willReturn(raceResult1);
		given(raceResultConverter.convert(fileData2)).willReturn(raceResult2);

		given(raceResult1.getEndRace()).willReturn(localTime1);
		given(raceResult2.getEndRace()).willReturn(localTime2);
	}

	@Test
	public void shouldConvertConvertFileToRaceResultCollection() {
		// given
		// when
		Collection<RaceResult> actualValues = uploadService.convertFileToRaceResult(file);
		// then
		assertThat(actualValues, is(Lists.list(raceResult1, raceResult2)));
	}
	
	@Test
	public void shouldReturnEmptyListIfFileHasNoData() throws IOException {
		// given
		given(fileConverter.convert(file)).willReturn(Lists.list());
		// when
		Collection<RaceResult> actualValues = uploadService.convertFileToRaceResult(file);
		// then
		assertThat(actualValues, is(Lists.list()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionIfCouldNotOpenFile() throws IOException {
		// given
		given(fileConverter.convert(file)).willThrow(IOException.class);
		// when
		uploadService.convertFileToRaceResult(file);
		// then
	}
}
