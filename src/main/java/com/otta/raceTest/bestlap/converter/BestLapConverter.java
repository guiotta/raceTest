package com.otta.raceTest.bestlap.converter;

import java.util.Comparator;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Component;

import com.otta.raceTest.bestlap.model.BestLap;
import com.otta.raceTest.upload.model.FileData;
import com.otta.raceTest.upload.model.Lap;

/**
 * Componente para realizar a conversão de um {@link FileData} para um {@link BestLap}.
 * @author Guilherme
 *
 */
@Component
public class BestLapConverter {

	public BestLap convert(FileData fileData) {
		String pilotIdentifier = fileData.getPilot().getIdentifier();
		String pilotName = fileData.getPilot().getName();
		Lap bestLap = fileData.getLaps().stream()
				.min(Comparator.comparing(Lap::getDuration))
				.orElseThrow(NoSuchElementException::new);
		
		return new BestLap(pilotIdentifier, pilotName, bestLap.getNumber(), bestLap.getDuration());
	}
}
