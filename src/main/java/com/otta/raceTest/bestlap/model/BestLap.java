package com.otta.raceTest.bestlap.model;

import java.time.Duration;
import java.util.Objects;

import org.apache.commons.lang3.time.DurationFormatUtils;

/**
 * Modelo contendo as informações necessárias sobre a melhor volta de um pilot.
 * @author Guilherme
 *
 */
public class BestLap {
	private static final String TIME_PATTERN = "mm:ss.S";
	
	private final String pilotIdentifier;
	private final String pilotName;
	private final int bestLapNumber;
	private final Duration bestLapTime;

	public BestLap(String pilotIdentifier, String pilotName, int bestLapNumber, Duration bestLapTime) {
		this.pilotIdentifier = pilotIdentifier;
		this.pilotName = pilotName;
		this.bestLapNumber = bestLapNumber;
		this.bestLapTime = bestLapTime;
	}

	public String getPilotIdentifier() {
		return pilotIdentifier;
	}

	public String getPilotName() {
		return pilotName;
	}

	public int getBestLapNumber() {
		return bestLapNumber;
	}

	public Duration getBestLapTime() {
		return bestLapTime;
	}
	
	public String getBestLapTimeReadable() {
		return DurationFormatUtils.formatDuration(bestLapTime.toMillis(), TIME_PATTERN, true);
	}

	@Override
	public int hashCode() {
		return Objects.hash(bestLapNumber, bestLapTime, pilotIdentifier, pilotName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BestLap other = (BestLap) obj;
		return bestLapNumber == other.bestLapNumber && Objects.equals(bestLapTime, other.bestLapTime)
				&& Objects.equals(pilotIdentifier, other.pilotIdentifier) && Objects.equals(pilotName, other.pilotName);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BestLap [pilotIdentifier=");
		builder.append(pilotIdentifier);
		builder.append(", pilotName=");
		builder.append(pilotName);
		builder.append(", bestLapNumber=");
		builder.append(bestLapNumber);
		builder.append(", bestLapTime=");
		builder.append(bestLapTime);
		builder.append("]");
		return builder.toString();
	}
}
