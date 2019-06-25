package com.otta.raceTest.timedelay.service;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.otta.raceTest.timedelay.comparator.EndRaceDelayComparator;
import com.otta.raceTest.timedelay.converter.EndRaceConverter;
import com.otta.raceTest.timedelay.model.EndRaceDelay;
import com.otta.raceTest.upload.converter.UploadFileConverter;
import com.otta.raceTest.upload.model.FileData;

@Service
public class TimeDelayService {
	private final UploadFileConverter fileConverter;
	private final EndRaceConverter endRaceConverter;

	@Autowired
	public TimeDelayService(UploadFileConverter fileConverter, EndRaceConverter endRaceConverter) {
		this.fileConverter = fileConverter;
		this.endRaceConverter = endRaceConverter;
	}

	public Collection<EndRaceDelay> convertToEndRaceDelay(MultipartFile file) {
		Collection<FileData> fileDataCollection = convertFileData(file);
		
		List<EndRaceDelay> endRaceDelayCollection = endRaceConverter.convert(fileDataCollection)
				.stream().collect(Collectors.toList());
		Collections.sort(endRaceDelayCollection, new EndRaceDelayComparator());
		
		return endRaceDelayCollection;
	}
	
	protected Collection<FileData> convertFileData(MultipartFile file) {
		try {
			return fileConverter.convert(file);
		} catch (IOException e) {
			throw new IllegalArgumentException("Could not open file with race logs.", e);
		}
	}
}
