package com.otta.raceTest.result.comparator;

import java.util.Comparator;

import com.otta.raceTest.result.model.RaceResult;

public class RaceResultComparator implements Comparator<RaceResult> {

	@Override
	public int compare(RaceResult result1, RaceResult result2) {
		return result1.getEndRace().compareTo(result2.getEndRace());
	}

}
