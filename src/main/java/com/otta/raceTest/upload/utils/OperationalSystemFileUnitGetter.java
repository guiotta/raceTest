package com.otta.raceTest.upload.utils;

import org.springframework.stereotype.Component;

@Component
public class OperationalSystemFileUnitGetter {

	public String get(boolean isWindows) {
		return isWindows ? "C://" : "//home//";
	}
}
