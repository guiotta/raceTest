package com.otta.raceTest.upload.converter;

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

import com.otta.raceTest.upload.model.FileData;
import com.otta.raceTest.upload.model.Lap;
import com.otta.raceTest.upload.model.Pilot;

/**
 * Componente para converter uma linha de texto do arquivo inserido em um {@link FileData}.
 * @author Guilherme
 *
 */
@Component
public class FileDataConverter {
	private final DurationConverter durationConverter;

	@Autowired
	public FileDataConverter(DurationConverter durationConverter) {
		this.durationConverter = durationConverter;
	}

	public FileData convert(String line) throws ParseException {
		StringTokenizer stknz = new StringTokenizer(line);

		String time = stknz.nextToken();
		String identifier = stknz.nextToken();
		// Busca character usado na separa��o entre as informa��es do piloto
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
		Collection<Lap> laps = new ArrayList<Lap>();
		laps.add(lap);
		
		return new FileData(pilot, laps);
	}
}
