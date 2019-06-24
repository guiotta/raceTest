package com.otta.raceTest.bestlap.service;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.otta.raceTest.bestlap.comparator.BestLapComparator;
import com.otta.raceTest.bestlap.converter.BestLapConverter;
import com.otta.raceTest.bestlap.model.BestLap;
import com.otta.raceTest.upload.converter.UploadFileConverter;
import com.otta.raceTest.upload.model.FileData;

@Service
public class BestLapService {
	private final UploadFileConverter fileConverter;
	private final BestLapConverter bestLapConverter;

	@Autowired
	public BestLapService(UploadFileConverter fileConverter, BestLapConverter bestLapConverter) {
		this.fileConverter = fileConverter;
		this.bestLapConverter = bestLapConverter;
	}

	public Collection<BestLap> convertFileDataToBestLap(MultipartFile file) {
		Collection<FileData> fileDataCollection = convertFileData(file);
		List<BestLap> bestLapCollection = fileDataCollection.stream()
				.map((data) -> bestLapConverter.convert(data))
				.collect(Collectors.toList());
		Collections.sort(bestLapCollection, new BestLapComparator());
		
		return bestLapCollection;
	}
	
	protected Collection<FileData> convertFileData(MultipartFile file) {
		try {
			return fileConverter.convert(file);
		} catch (IOException e) {
			throw new IllegalArgumentException("Could not open file with race logs.", e);
		}
	}
}
