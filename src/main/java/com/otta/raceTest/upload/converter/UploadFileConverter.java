package com.otta.raceTest.upload.converter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.otta.raceTest.upload.model.FileData;
import com.otta.raceTest.upload.model.Header;
import com.otta.raceTest.upload.model.Lap;
import com.otta.raceTest.upload.model.Pilot;

@Component
public class UploadFileConverter {
	private static final String TEMP_FILE_PREFIX = "C://";
	private static final String TEMP_FILE_POSTFIX = "test";
	
	private final DurationConverter durationConverter;

	@Autowired
	public UploadFileConverter(DurationConverter durationConverter) {
		this.durationConverter = durationConverter;
	}



	public void convert(MultipartFile multipartFile) throws IOException {
		Header header = null;
		File tempFile = File.createTempFile(TEMP_FILE_PREFIX, TEMP_FILE_POSTFIX);
		tempFile.deleteOnExit();
		multipartFile.transferTo(tempFile);
		Collection<FileData> dataCollection = new ArrayList<FileData>();

		BufferedReader in = new BufferedReader(new FileReader(tempFile));
		try {
			String line;
			if ((line = in.readLine()) != null) {
				Collection<String> readValues = new ArrayList<String>();
				StringTokenizer stknz = new StringTokenizer(line);
				while (stknz.hasMoreElements()) {
					readValues.add(stknz.nextToken());
				}
				header = new Header(readValues);
				System.out.println(header);
			}
			while ((line = in.readLine()) != null) {
				StringTokenizer stknz = new StringTokenizer(line);

				String time = stknz.nextToken();
				String identifier = stknz.nextToken();
				String separator = stknz.nextToken();
				String name = stknz.nextToken();
				int lapNumber = Integer.parseInt(stknz.nextToken());
				String duration = stknz.nextToken();
				String velocity = stknz.nextToken();
				
				Pilot pilot = new Pilot(identifier, name);
				LocalTime timeConverted = LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME);
				Duration durationConverted = durationConverter.convert(duration);
				Lap lap = new Lap(timeConverted, lapNumber, durationConverted,
						new Double(NumberFormat.getNumberInstance(Locale.getDefault()).parse(velocity).doubleValue()));
				
				boolean isPilotPresent = dataCollection.stream().anyMatch((data) -> data.getPilot().getNumber().equals(pilot.getNumber()));
				if (isPilotPresent) {
					dataCollection.stream().filter((data) -> data.getPilot().getNumber().equals(pilot.getNumber())).findFirst().get().getLaps().add(lap);
				} else {
					Collection<Lap> laps = new ArrayList<Lap>();
					laps.add(lap);
					dataCollection.add(new FileData(pilot, laps));
				}				
			}
			System.out.println(dataCollection);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Valor inválido para velocidade.");
		} finally {
			tempFile.delete();
			in.close();
		}
	}
}
