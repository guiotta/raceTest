package com.otta.raceTest.timedelay.builder;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.otta.raceTest.timedelay.extractor.EndRaceLocalTimeExtractor;
import com.otta.raceTest.timedelay.model.EndRaceTime;
import com.otta.raceTest.upload.model.FileData;

@Component
public class EndRaceTimeBuilder {
	private final EndRaceLocalTimeExtractor endRaceLocalTimeExtractor;

	@Autowired
	public EndRaceTimeBuilder(EndRaceLocalTimeExtractor endRaceLocalTimeExtractor) {
		this.endRaceLocalTimeExtractor = endRaceLocalTimeExtractor;
	}

	public EndRaceTime build(FileData fileData) {
		LocalTime endTime = endRaceLocalTimeExtractor.extract(fileData);
		String identifier = fileData.getPilot().getNumber();
		String name = fileData.getPilot().getName();

		return new EndRaceTime(identifier, name, endTime);
	}
}
