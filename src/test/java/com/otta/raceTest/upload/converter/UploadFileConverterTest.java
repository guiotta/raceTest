package com.otta.raceTest.upload.converter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

import com.otta.raceTest.upload.model.FileData;
import com.otta.raceTest.upload.model.Lap;
import com.otta.raceTest.upload.model.Pilot;
import com.otta.raceTest.upload.utils.OperationalSystemFileUnitGetter;
import com.otta.raceTest.upload.validator.HeaderValidator;

@RunWith(MockitoJUnitRunner.class)
public class UploadFileConverterTest {
	private static final String LINE_ONE = "line one";
	private static final String LINE_TWO = "line two";
	private static final String LINE_THREE = "line three";
	
	@Mock
	private FileDataConverter fileDataConverter;

	@Mock
	private FileHeaderConverter fileHeaderConverter;

	@Mock
	private HeaderValidator headerValidator;

	@Mock
	private OperationalSystemFileUnitGetter operationalSystemFileUnitGetter;
	
	private UploadFileConverter uploadFileConverter;
	
	@Mock
	private File file;
	@Mock
	private MultipartFile multipartFile;
	@Mock
	private BufferedReader bufferedReader;
	@Mock
	private FileData fileData1;
	@Mock
	private Pilot pilot1;
	@Mock
	private FileData fileData2;
	@Mock
	private Pilot pilot2;
	@Mock
	private Lap lap1;
	@Mock
	private Lap lap2;

	@Before
	public void setUp() throws Exception {
		uploadFileConverter = spy(new UploadFileConverter(fileDataConverter, operationalSystemFileUnitGetter,
				fileHeaderConverter, headerValidator));
		
		doReturn(LINE_ONE, LINE_TWO, LINE_THREE, null).when(bufferedReader).readLine();
		doReturn(Lists.list(LINE_ONE)).when(fileHeaderConverter).convert(LINE_ONE);
		doReturn(Boolean.TRUE).when(headerValidator).validate(any());
		doReturn(fileData1).when(fileDataConverter).convert(LINE_TWO);
		doReturn(fileData2).when(fileDataConverter).convert(LINE_THREE);
		doReturn(pilot1).when(fileData1).getPilot();
		doReturn(pilot2).when(fileData2).getPilot();
		doReturn(Lists.list(lap1)).when(fileData1).getLaps();
		doReturn(Lists.list(lap2)).when(fileData2).getLaps();
		
		doReturn(file).when(uploadFileConverter).createTemporaryFile();
		doReturn(bufferedReader).when(uploadFileConverter).createBufferedReader(multipartFile, file);
		
	}

	@Test
	public void shouldCorrectlyConvertFileDataFromMultipartFile() throws IOException {
		//given
		//when
		Collection<FileData> actualValue = uploadFileConverter.convert(multipartFile);
		//then
		assertThat(actualValue, is(Lists.list(fileData1, fileData2)));
	}
	
	@Test
	public void shouldConvertDataToSameFileData() throws IOException {
		//given
		doReturn(Boolean.TRUE).when(pilot1).equalsWorkaround(pilot2);
		//when
		Collection<FileData> actualValue = uploadFileConverter.convert(multipartFile);
		//then
		assertThat(actualValue, is(Lists.list(fileData1)));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionIfHeaderIsInvalid() throws IOException {
		//given
		doReturn(Boolean.FALSE).when(headerValidator).validate(any());
		//when
		uploadFileConverter.convert(multipartFile);
		//then
	}

}
