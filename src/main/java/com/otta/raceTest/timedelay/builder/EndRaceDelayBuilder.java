package com.otta.raceTest.timedelay.builder;

import java.time.Duration;
import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.otta.raceTest.timedelay.model.EndRaceDelay;
import com.otta.raceTest.upload.model.FileData;

/**
 * Componente para construir um {@link EndRaceDelay} partindo de um {@link FileData} e um {@link LocalTime}.
 * @author Guilherme
 *
 */
@Component
public class EndRaceDelayBuilder {
	private EndRaceTimeBuilder endRaceTimeBuilder;

	@Autowired
	public EndRaceDelayBuilder(EndRaceTimeBuilder endRaceTimeBuilder) {
		this.endRaceTimeBuilder = endRaceTimeBuilder;
	}

	public EndRaceDelay build(FileData fileData, LocalTime endRaceTime) {
		LocalTime pilotRaceEndTime = endRaceTimeBuilder.build(fileData).getTime();
		String pilotIdentifier = fileData.getPilot().getIdentifier();
		String pilotName = fileData.getPilot().getName();
		
		return new EndRaceDelay(pilotIdentifier, pilotName, Duration.between(endRaceTime, pilotRaceEndTime));
	}
}
