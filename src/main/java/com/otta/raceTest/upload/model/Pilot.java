package com.otta.raceTest.upload.model;

import java.util.Objects;

public class Pilot {
	private final String identifier;
	private final String name;

	public Pilot(String identifier, String name) {
		this.identifier = identifier;
		this.name = name;
	}

	public String getNumber() {
		return identifier;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, identifier);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pilot other = (Pilot) obj;
		return Objects.equals(name, other.name) && Objects.equals(identifier, other.identifier);
	}

	/**
	 * Equals levando em considera��o o log da corrida com o nome do piloto escrito de forma errada.
	 * Necessário pois o log postado no github apresenta um erro de grafia no nome do Felipe Massa. 
	 */
	public boolean equalsWorkaround(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pilot other = (Pilot) obj;
		return Objects.equals(identifier, other.identifier);
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pilot [number=");
		builder.append(identifier);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");

		return builder.toString();
	}

}
