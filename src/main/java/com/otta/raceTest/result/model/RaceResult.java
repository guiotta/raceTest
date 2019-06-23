package com.otta.raceTest.result.model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;

import org.apache.commons.lang3.time.DurationFormatUtils;

public class RaceResult {
	private final String pilotIdentifier;
	private final String pilotName;
	private final LocalTime endRace;
	private final int completeLaps;
	private final Duration raceDuration;

	public RaceResult(String pilotIdentifier, String pilotName, LocalTime endRace, int completeLaps,
			Duration raceDuration) {
		this.pilotIdentifier = pilotIdentifier;
		this.pilotName = pilotName;
		this.endRace = endRace;
		this.completeLaps = completeLaps;
		this.raceDuration = raceDuration;
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
		return DurationFormatUtils.formatDuration(raceDuration.toMillis(), "mm:s.S", true);
	}

	@Override
	public int hashCode() {
		return Objects.hash(completeLaps, endRace, pilotIdentifier, pilotName, raceDuration);
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
		return completeLaps == other.completeLaps && Objects.equals(endRace, other.endRace)
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
		builder.append("]");
		return builder.toString();
	}
}
