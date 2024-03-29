package com.otta.raceTest.result.model;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;

import org.apache.commons.lang3.time.DurationFormatUtils;

/**
 * Model contendo as informações sobre o final da corrida para um determinado piloto.
 * @author Guilherme
 *
 */
public class RaceResult {
	private static final String TIME_PATTERN = "mm:ss.S";
	private static final String AVERAGE_SPEED_PATTERN = "#.###";
	
	private final String pilotIdentifier;
	private final String pilotName;
	private final LocalTime endRace;
	private final int completeLaps;
	private final Duration raceDuration;
	private final double averageSpeed;

	public RaceResult(String pilotIdentifier, String pilotName, LocalTime endRace, int completeLaps,
			Duration raceDuration, double averageSpeed) {
		this.pilotIdentifier = pilotIdentifier;
		this.pilotName = pilotName;
		this.endRace = endRace;
		this.completeLaps = completeLaps;
		this.raceDuration = raceDuration;
		this.averageSpeed = averageSpeed;
	}

	public String getPilotIdentifier() {
		return pilotIdentifier;
	}

	public String getPilotName() {
		return pilotName;
	}

	public LocalTime getEndRace() {
		return endRace;
	}

	public int getCompleteLaps() {
		return completeLaps;
	}

	public Duration getRaceDuration() {
		return raceDuration;
	}
	
	public String getRaceDurationReadable() {
		return DurationFormatUtils.formatDuration(raceDuration.toMillis(), TIME_PATTERN, true);
	}

	public double getAverageSpeed() {
		return averageSpeed;
	}
	
	public String getAverageSpeedReadable() {
		return new DecimalFormat(AVERAGE_SPEED_PATTERN).format(averageSpeed);
	}

	@Override
	public int hashCode() {
		return Objects.hash(averageSpeed, completeLaps, endRace, pilotIdentifier, pilotName, raceDuration);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RaceResult other = (RaceResult) obj;
		return Double.doubleToLongBits(averageSpeed) == Double.doubleToLongBits(other.averageSpeed)
				&& completeLaps == other.completeLaps && Objects.equals(endRace, other.endRace)
				&& Objects.equals(pilotIdentifier, other.pilotIdentifier) && Objects.equals(pilotName, other.pilotName)
				&& Objects.equals(raceDuration, other.raceDuration);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RaceResult [pilotIdentifier=");
		builder.append(pilotIdentifier);
		builder.append(", pilotName=");
		builder.append(pilotName);
		builder.append(", endRace=");
		builder.append(endRace);
		builder.append(", completeLaps=");
		builder.append(completeLaps);
		builder.append(", raceDuration=");
		builder.append(raceDuration);
		builder.append(", averageSpeed=");
		builder.append(averageSpeed);
		builder.append("]");
		return builder.toString();
	}
}
