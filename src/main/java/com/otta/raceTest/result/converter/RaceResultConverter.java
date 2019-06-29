package com.otta.raceTest.result.converter;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Component;

import com.otta.raceTest.result.model.RaceResult;
import com.otta.raceTest.upload.model.FileData;
import com.otta.raceTest.upload.model.Lap;
import com.otta.raceTest.upload.model.Pilot;

/**
 * Componente para converter um {@link FileData} em um {@link RaceResult}.
 * @author Guilherme
 *
 */
@Component
public class RaceResultConverter {

	public RaceResult convert(FileData data) {
		Pilot pilot = data.getPilot();
		Lap finalLap = data.getLaps().stream()
				.max(Comparator.comparing(Lap::getNumber))
				.orElseThrow(NoSuchElementException::new);
		
		String pilotIdentifier = pilot.getNumber();
		String pilotName = pilot.getName();
		int lapNumber = finalLap.getNumber();
		LocalTime endRace = finalLap.getTime().plus(finalLap.getDuration());
		Duration raceDuration = data.getLaps().stream()
				.map((lap) -> lap.getDuration())
				.reduce(Duration.ZERO, (a, b) -> a.plusMillis(b.toMillis()));
		double speedSum = data.getLaps().stream()
				.map((lap) -> lap.getVelocity())
				.reduce(0D, Double::sum);
		double averageSpeed = speedSum / data.getLaps().size();
		
		return new RaceResult(pilotIdentifier, pilotName, endRace, lapNumber, raceDuration, averageSpeed);
	}
}
