package com.otta.raceTest.timedelay.model;

import java.time.LocalTime;
import java.util.Objects;

public class EndRaceTime {
	private final String pilotIdentifier;
	private final String pilotName;
	private final LocalTime time;

	public EndRaceTime(String pilotIdentifier, String pilotName, LocalTime time) {
		this.pilotIdentifier = pilotIdentifier;
		this.pilotName = pilotName;
		this.time = time;
	}

	public String getPilotIdentifier() {
		return pilotIdentifier;
	}

	public String getPilotName() {
		return pilotName;
	}

	public LocalTime getTime() {
		return time;
	}

	@Override
	public int hashCode() {
		return Objects.hash(pilotIdentifier, pilotName, time);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EndRaceTime other = (EndRaceTime) obj;
		return Objects.equals(pilotIdentifier, other.pilotIdentifier) && Objects.equals(pilotName, other.pilotName)
				&& Objects.equals(time, other.time);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EndRaceTime [pilotIdentifier=");
		builder.append(pilotIdentifier);
		builder.append(", pilotName=");
		builder.append(pilotName);
		builder.append(", time=");
		builder.append(time);
		builder.append("]");
		return builder.toString();
	}
}
