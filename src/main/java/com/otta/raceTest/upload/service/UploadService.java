package com.otta.raceTest.upload.service;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.otta.raceTest.upload.converter.UploadFileConverter;
import com.otta.raceTest.upload.model.FileData;

@Service
public class UploadService {
	private UploadFileConverter fileConverter;

	@Autowired
	public UploadService(UploadFileConverter lapConverter) {
		this.fileConverter = lapConverter;
	}
	
	public Collection<FileData> convertFileData(MultipartFile file) {
		try {
			return fileConverter.convert(file);
		} catch (IOException e) {
			throw new IllegalArgumentException("Could not open file with race logs.", e);
		}
	}

	public boolean validate(MultipartFile file) {
		return true;
	}
}
