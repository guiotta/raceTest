package com.otta.raceTest.timedelay.comparator;

import java.util.Comparator;

import com.otta.raceTest.timedelay.model.EndRaceDelay;

public class EndRaceDelayComparator implements Comparator<EndRaceDelay> {

	@Override
	public int compare(EndRaceDelay endRace1, EndRaceDelay endRace2) {
		return endRace1.getDelay().compareTo(endRace2.getDelay());
	}

}
