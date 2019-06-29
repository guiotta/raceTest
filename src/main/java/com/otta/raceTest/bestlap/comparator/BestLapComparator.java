package com.otta.raceTest.bestlap.comparator;

import java.util.Comparator;

import com.otta.raceTest.bestlap.model.BestLap;

/**
 * Classe para realizar comparações na ordenação entre {@link BestLap}.
 * @author Guilherme
 *
 */
public class BestLapComparator implements Comparator<BestLap> {

	@Override
	public int compare(BestLap bestLap1, BestLap bestLap2) {
		return bestLap1.getBestLapTime().compareTo(bestLap2.getBestLapTime());
	}

}
