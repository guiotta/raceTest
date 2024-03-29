package com.otta.raceTest.upload.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

import org.springframework.stereotype.Component;

/**
 * Componente para converter a primeira linha do arquivo inserido pelo usuário em uma coleção de Strings.
 * @author Guilherme
 *
 */
@Component
public class FileHeaderConverter {

	public Collection<String> convert(String line) {
		Collection<String> readValues = new ArrayList<String>();

		if (line == null) {
			throw new IllegalArgumentException("Could not read Table Headers in log file.");
		} else {
			StringTokenizer stknz = new StringTokenizer(line);
			while (stknz.hasMoreElements()) {
				readValues.add(stknz.nextToken());
			}
		}

		return readValues;
	}
}
