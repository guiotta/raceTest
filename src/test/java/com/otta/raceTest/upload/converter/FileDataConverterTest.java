package com.otta.raceTest.upload.converter;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.*;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.otta.raceTest.upload.model.FileData;
import com.otta.raceTest.upload.model.Lap;

@RunWith(MockitoJUnitRunner.class)
public class FileDataConverterTest {
	private static final String VALID_LINE = "23:49:08.277      038 - F.MASSA                           1		1:02.852                        44,275";
	private static final String INVALID_PARSE_LINE = "23:49:08.277      038 - F.MASSA                           1		1:02.852                        a";
	private static final String INVALID_LAP_LINE = "23:49:08.277      038 - F.MASSA                           a		1:02.852                        44,275";
	private static final String IDENTIFIER = "038";
	private static final String NAME = "F.MASSA";
	private static final int LAP_NUMBER = 1;
	private static final Double SPEED = Double.valueOf(44.275d);
	private static final String DURATION_VALUE = "1:02.852";
	private static final String TIME_VALUE = "23:49:08.277";
	
	@Mock
	private DurationConverter durationConverter;
	@InjectMocks
	private FileDataConverter fileDataConverter;
	
	private Duration duration;
	private LocalTime localtime;

	@Before
	public void setUp() throws Exception {
		localtime = LocalTime.parse(TIME_VALUE, DateTimeFormatter.ISO_LOCAL_TIME);
		given(durationConverter.convert(DURATION_VALUE)).willReturn(duration);
	}

	@Test
	public void shouldConvertLineToFileData() throws Exception {
		//given
		//when
		FileData fileData = fileDataConverter.convert(VALID_LINE);
		//then
		Lap actualLap = fileData.getLaps().stream().collect(Collectors.toList()).get(0);
		assertEquals(NAME, fileData.getPilot().getName());
		assertEquals(IDENTIFIER, fileData.getPilot().getIdentifier());
		assertEquals(1, fileData.getLaps().size());
		assertEquals(LAP_NUMBER, actualLap.getNumber());
		assertEquals(SPEED, actualLap.getVelocity());
		assertEquals(duration, actualLap.getDuration());
		assertEquals(localtime, actualLap.getTime());
	}
	
	@Test(expected = ParseException.class)
	public void shouldThrowParseExceptionIfSpeedFormatIsInvalid() throws ParseException {
		//given
		//when
		fileDataConverter.convert(INVALID_PARSE_LINE);
		//then
	}
	
	@Test(expected = NumberFormatException.class)
	public void shouldThrowParseExceptionIfLapFormatIsInvalid() throws ParseException {
		//given
		//when
		fileDataConverter.convert(INVALID_LAP_LINE);
		//then
	}
}
