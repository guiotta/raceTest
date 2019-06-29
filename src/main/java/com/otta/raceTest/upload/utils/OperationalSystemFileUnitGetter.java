package com.otta.raceTest.upload.utils;

import org.springframework.stereotype.Component;

/**
 * Classe para retornar o prefixo para criação de um arquivo.
 * Suporta prefixos de Windows e Linux.
 * @author Guilherme
 *
 */
@Component
public class OperationalSystemFileUnitGetter {

	public String get(boolean isWindows) {
		return isWindows ? "C://" : "//home//";
	}
}
