package com.otta.raceTest.upload.model;

import java.util.ArrayList;
import java.util.Collection;

public class Header {
	private final Collection<String> titles;
	
	public Header() {
		this.titles = new ArrayList<String>();
	}
	
	public Header(Collection<String> titles) {
		this.titles = titles;
	}
	
	public Collection<String> getTitles() {
		return this.titles;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Header [titles=");
		builder.append(titles);
		builder.append("]");

		return builder.toString();
	}
}
