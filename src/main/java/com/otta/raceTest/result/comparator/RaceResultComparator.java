package com.otta.raceTest.result.comparator;

import java.util.Comparator;

import com.otta.raceTest.result.model.RaceResult;

/**
 * Classe para realizar comparações na ordenação entre {@link RaceResult}.
 * @author Guilherme
 *
 */
public class RaceResultComparator implements Comparator<RaceResult> {

	@Override
	public int compare(RaceResult result1, RaceResult result2) {
		return result1.getEndRace().compareTo(result2.getEndRace());
	}

}
