package com.otta.raceTest.timedelay.model;

import java.time.Duration;
import java.util.Objects;

import org.apache.commons.lang3.time.DurationFormatUtils;

public class EndRaceDelay {
	private static final String TIME_PATTERN = "mm:ss.S";
	
	private final String pilotIdentifier;
	private final String pilotName;
	private final Duration delay;

	public EndRaceDelay(String pilotIdentifier, String pilotName, Duration delay) {
		this.pilotIdentifier = pilotIdentifier;
		this.pilotName = pilotName;
		this.delay = delay;
	}

	public String getPilotIdentifier() {
		return pilotIdentifier;
	}

	public String getPilotName() {
		return pilotName;
	}

	public Duration getDelay() {
		return delay;
	}
	
	public String getDelayReadable() {
		return DurationFormatUtils.formatDuration(delay.toMillis(), TIME_PATTERN, true);
	}

	@Override
	public int hashCode() {
		return Objects.hash(delay, pilotIdentifier, pilotName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EndRaceDelay other = (EndRaceDelay) obj;
		return Objects.equals(delay, other.delay) && Objects.equals(pilotIdentifier, other.pilotIdentifier)
				&& Objects.equals(pilotName, other.pilotName);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EndRaceDelay [pilotIdentifier=");
		builder.append(pilotIdentifier);
		builder.append(", pilotName=");
		builder.append(pilotName);
		builder.append(", delay=");
		builder.append(delay);
		builder.append("]");
		return builder.toString();
	}
}
