package com.otta.raceTest.upload.converter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.lang3.SystemUtils;
import org.assertj.core.util.VisibleForTesting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.otta.raceTest.upload.model.FileData;
import com.otta.raceTest.upload.model.Header;
import com.otta.raceTest.upload.utils.OperationalSystemFileUnitGetter;
import com.otta.raceTest.upload.validator.HeaderValidator;

@Component
public class UploadFileConverter {
	private static final String TEMP_FILE_POSTFIX = "test";

	private final FileDataConverter fileDataConverter;
	private final OperationalSystemFileUnitGetter operationalSystemFileUnitGetter;
	private final FileHeaderConverter fileHeaderConverter;
	private final HeaderValidator headerValidator;

	@Autowired
	public UploadFileConverter(FileDataConverter fileDataConverter,
			OperationalSystemFileUnitGetter operationalSystemFileUnitGetter, FileHeaderConverter fileHeaderConverter,
			HeaderValidator headerValidator) {
		this.fileDataConverter = fileDataConverter;
		this.operationalSystemFileUnitGetter = operationalSystemFileUnitGetter;
		this.fileHeaderConverter = fileHeaderConverter;
		this.headerValidator = headerValidator;
	}

	public Collection<FileData> convert(MultipartFile multipartFile) throws IOException {
		Header header = null;
		Collection<FileData> dataCollection = new ArrayList<FileData>();
		String line = "";

		File tempFile = createTemporaryFile();
		BufferedReader in = createBufferedReader(multipartFile, tempFile);
		try {
			Collection<String> readValues = fileHeaderConverter.convert(in.readLine());
			header = new Header(readValues);
			if (!headerValidator.validate(header)) {
				String errorMessage = "Unknown values are found in Table Headers: %s";
				throw new IllegalArgumentException(String.format(errorMessage, header));
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
			throw new IllegalArgumentException(String.format("Invalid value found in %s.", line), e);
		} finally {
			tempFile.delete();
			in.close();
		}
		return dataCollection;
	}
	
	@VisibleForTesting
	protected File createTemporaryFile() throws IOException {
		String tempFilePrefix = operationalSystemFileUnitGetter.get(SystemUtils.IS_OS_WINDOWS);
		return File.createTempFile(tempFilePrefix, TEMP_FILE_POSTFIX);
	}
	
	@VisibleForTesting
	protected BufferedReader createBufferedReader(MultipartFile multipartFile, File tempFile)
			throws IllegalStateException, IOException {
		tempFile.deleteOnExit();
		multipartFile.transferTo(tempFile);
		
		return new BufferedReader(new FileReader(tempFile));
	}
}
