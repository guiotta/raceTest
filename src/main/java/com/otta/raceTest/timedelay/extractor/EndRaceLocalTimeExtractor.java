package com.otta.raceTest.timedelay.extractor;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Component;

import com.otta.raceTest.upload.model.FileData;
import com.otta.raceTest.upload.model.Lap;

@Component
public class EndRaceLocalTimeExtractor {

	public LocalTime extract(FileData data) {
		Lap lastLap = data.getLaps().stream()
				.max(Comparator.comparing(Lap::getNumber))
				.orElseThrow(NoSuchElementException::new);
		LocalTime endOfLap = lastLap.getTime();
		
		return endOfLap;
	}
}
