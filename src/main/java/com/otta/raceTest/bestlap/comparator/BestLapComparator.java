package com.otta.raceTest.bestlap.comparator;

import java.util.Comparator;

import com.otta.raceTest.bestlap.model.BestLap;

public class BestLapComparator implements Comparator<BestLap> {

	@Override
	public int compare(BestLap bestLap1, BestLap bestLap2) {
		return bestLap1.getBestLapTime().compareTo(bestLap2.getBestLapTime());
	}

}
