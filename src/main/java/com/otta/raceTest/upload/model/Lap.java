package com.otta.raceTest.upload.model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;

public class Lap {
	private final LocalTime time;
	private final int number;
	private final Duration duration;
	private final Double velocity;

	public Lap(LocalTime time, int number, Duration duration, Double velocity) {
		this.time = time;
		this.number = number;
		this.duration = duration;
		this.velocity = velocity;
	}

	public LocalTime getTime() {
		return time;
	}

	public int getNumber() {
		return number;
	}

	public Duration getDuration() {
		return duration;
	}

	public Double getVelocity() {
		return velocity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(duration, number, time, velocity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lap other = (Lap) obj;
		return Objects.equals(duration, other.duration) && number == other.number && Objects.equals(time, other.time)
				&& Objects.equals(velocity, other.velocity);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Lap [time=");
		builder.append(time);
		builder.append(", number=");
		builder.append(number);
		builder.append(", duration=");
		builder.append(duration);
		builder.append(", velocity=");
		builder.append(velocity);
		builder.append("]");
		return builder.toString();
	}

}
