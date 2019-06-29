package com.otta.raceTest.timedelay.comparator;

import java.util.Comparator;

import com.otta.raceTest.timedelay.model.EndRaceDelay;

/**
 * Classe para realizar comparações na ordenação entre {@link EndRaceDelay}.
 * @author Guilherme
 *
 */
public class EndRaceDelayComparator implements Comparator<EndRaceDelay> {

	@Override
	public int compare(EndRaceDelay endRace1, EndRaceDelay endRace2) {
		return endRace1.getDelay().compareTo(endRace2.getDelay());
	}

}
