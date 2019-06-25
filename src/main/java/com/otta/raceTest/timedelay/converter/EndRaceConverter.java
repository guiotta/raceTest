package com.otta.raceTest.timedelay.converter;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.otta.raceTest.timedelay.builder.EndRaceDelayBuilder;
import com.otta.raceTest.timedelay.builder.EndRaceTimeBuilder;
import com.otta.raceTest.timedelay.model.EndRaceDelay;
import com.otta.raceTest.timedelay.model.EndRaceTime;
import com.otta.raceTest.upload.model.FileData;

@Component
public class EndRaceConverter {
	private EndRaceTimeBuilder endRaceTimeBuilder;
	private EndRaceDelayBuilder endRaceDelayBuilder;
	
	@Autowired
	public EndRaceConverter(EndRaceTimeBuilder endRaceTimeBuilder, EndRaceDelayBuilder endRaceDelayBuilder) {
		this.endRaceTimeBuilder = endRaceTimeBuilder;
		this.endRaceDelayBuilder = endRaceDelayBuilder;
	}

	public List<EndRaceDelay> convert(Collection<FileData> fileDataCollection) {
		Collection<EndRaceTime> endRaceTimeCollection = fileDataCollection.stream()
				.map((fileData) -> endRaceTimeBuilder.build(fileData))
				.collect(Collectors.toList());
		
		EndRaceTime bestTime = endRaceTimeCollection.stream()
				.min(Comparator.comparing(EndRaceTime::getTime))
				.orElseThrow(IllegalArgumentException::new);
		
		return fileDataCollection.stream()
				.map((fileData) -> endRaceDelayBuilder.build(fileData, bestTime.getTime()))
				.collect(Collectors.toList());
	}
}
