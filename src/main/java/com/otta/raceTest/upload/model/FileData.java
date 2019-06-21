package com.otta.raceTest.upload.model;

import java.util.Collection;
import java.util.Objects;

public class FileData {
	private final Pilot pilot;
	private final Collection<Lap> laps;
	
	public FileData(Pilot pilot, Collection<Lap> laps) {
		this.pilot = pilot;
		this.laps = laps;
	}

	public Pilot getPilot() {
		return pilot;
	}

	public Collection<Lap> getLaps() {
		return laps;
	}

	@Override
	public int hashCode() {
		return Objects.hash(laps, pilot);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileData other = (FileData) obj;
		return Objects.equals(laps, other.laps) && Objects.equals(pilot, other.pilot);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FileData [pilot=");
		builder.append(pilot);
		builder.append(", laps=");
		builder.append(laps);
		builder.append("]");
		return builder.toString();
	}
}
