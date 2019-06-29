package com.otta.raceTest.bestlap.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.*;

import java.io.IOException;
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

import com.otta.raceTest.bestlap.converter.BestLapConverter;
import com.otta.raceTest.bestlap.model.BestLap;
import com.otta.raceTest.upload.converter.UploadFileConverter;
import com.otta.raceTest.upload.model.FileData;

@RunWith(MockitoJUnitRunner.class)
public class BestLapServiceTest {
	@Mock
	private BestLapConverter bestLapConverter;

	@Mock
	private UploadFileConverter fileConverter;
	@InjectMocks
	private BestLapService bestLapService;

	@Mock
	private MultipartFile file;
	@Mock
	private FileData fileData1;
	@Mock
	private FileData fileData2;
	@Mock
	private BestLap bestLap1;
	@Mock
	private BestLap bestLap2;

	private Duration bestTime1;
	private Duration bestTime2;

	@Before
	public void setUp() throws Exception {
		bestTime1 = Duration.ZERO;
		bestTime2 = Duration.ZERO;

		given(fileConverter.convert(file)).willReturn(Lists.list(fileData1, fileData2));
		given(bestLapConverter.convert(fileData1)).willReturn(bestLap1);
		given(bestLapConverter.convert(fileData2)).willReturn(bestLap2);
		given(bestLap1.getBestLapTime()).willReturn(bestTime1);
		given(bestLap2.getBestLapTime()).willReturn(bestTime2);
	}

	@Test
	public void shouldCorrectlyConvertFile() {
		// given
		// when
		Collection<BestLap> actualValues = bestLapService.convertFileDataToBestLap(file);
		// then
		assertThat(actualValues, is(Lists.list(bestLap1, bestLap2)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionIfCouldNotOpenFile() throws IOException {
		// given
		given(fileConverter.convert(file)).willThrow(IOException.class);
		// when
		bestLapService.convertFileDataToBestLap(file);
		// then
	}

}
