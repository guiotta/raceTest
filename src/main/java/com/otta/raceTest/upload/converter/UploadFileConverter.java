package com.otta.raceTest.upload.converter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.otta.raceTest.upload.model.FileData;
import com.otta.raceTest.upload.model.Header;

@Component
public class UploadFileConverter {
	private static final String TEMP_FILE_PREFIX = "C://";
	private static final String TEMP_FILE_POSTFIX = "test";

	private final FileDataConverter fileDataConverter;

	@Autowired
	public UploadFileConverter(FileDataConverter fileDataConverter) {
		this.fileDataConverter = fileDataConverter;
	}

	public Collection<FileData> convert(MultipartFile multipartFile) throws IOException {
		Header header = null;
		File tempFile = File.createTempFile(TEMP_FILE_PREFIX, TEMP_FILE_POSTFIX);
		tempFile.deleteOnExit();
		multipartFile.transferTo(tempFile);
		Collection<FileData> dataCollection = new ArrayList<FileData>();
		String line = "";

		BufferedReader in = new BufferedReader(new FileReader(tempFile));
		try {
			if ((line = in.readLine()) != null) {
				Collection<String> readValues = new ArrayList<String>();
				StringTokenizer stknz = new StringTokenizer(line);
				while (stknz.hasMoreElements()) {
					readValues.add(stknz.nextToken());
				}
				header = new Header(readValues);
				System.out.println(header);
			}
			while ((line = in.readLine()) != null) {
				FileData fileData = fileDataConverter.convert(line);

				boolean isPilotPresent = dataCollection.stream()
						.anyMatch((data) -> data.getPilot().equalsWorkaround(fileData.getPilot()));

				if (isPilotPresent) {
					dataCollection.stream().filter((data) -> data.getPilot().equalsWorkaround(fileData.getPilot()))
							.findFirst().get().getLaps().addAll(fileData.getLaps());
				} else {
					dataCollection.add(fileData);
				}
			}
			System.out.println(dataCollection);
		} catch (ParseException e) {
			throw new IllegalArgumentException(String.format("Valor numérico inválido foi encontrado em %s.", line));
		} finally {
			tempFile.delete();
			in.close();
		}
		return dataCollection;
	}
}
