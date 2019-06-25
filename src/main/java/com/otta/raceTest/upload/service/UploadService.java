package com.otta.raceTest.upload.service;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.otta.raceTest.result.comparator.RaceResultComparator;
import com.otta.raceTest.result.converter.RaceResultConverter;
import com.otta.raceTest.result.model.RaceResult;
import com.otta.raceTest.upload.converter.UploadFileConverter;
import com.otta.raceTest.upload.model.FileData;

@Service
public class UploadService {
	private final UploadFileConverter fileConverter;
	private final RaceResultConverter raceResultConverter;
	
	@Autowired
	public UploadService(UploadFileConverter fileConverter, RaceResultConverter raceResultConverter) {
		this.fileConverter = fileConverter;
		this.raceResultConverter = raceResultConverter;
	}
	
	public Collection<RaceResult> convertFileToRaceResult(MultipartFile file) {
		Collection<FileData> fileDataCollection = convertFileData(file);
		List<RaceResult> resultsCollection = convertToRaceResult(fileDataCollection).stream().collect(Collectors.toList());

		Collections.sort(resultsCollection, new RaceResultComparator());
		return resultsCollection;
	}
	
	protected Collection<FileData> convertFileData(MultipartFile file) {
		try {
			return fileConverter.convert(file);
		} catch (IOException e) {
			throw new IllegalArgumentException("Could not open file with race logs.", e);
		}
	}
	
	protected Collection<RaceResult> convertToRaceResult(Collection<FileData> fileData) {
		return fileData.stream().map((data) -> raceResultConverter.convert(data)).collect(Collectors.toList());
	}

	public boolean validate(MultipartFile file) {
		return true;
	}
}
