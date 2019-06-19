package com.otta.raceTest.upload.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {

	public boolean validate(MultipartFile file) {
		return true;
	}
}
