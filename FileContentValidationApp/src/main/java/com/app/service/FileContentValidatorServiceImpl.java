package com.app.service;

import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileContentValidatorServiceImpl implements FileContentValidatorService {

	private final static Logger LOGGER = LoggerFactory.getLogger(FileContentValidatorServiceImpl.class);

	private final Tika tika;

	public FileContentValidatorServiceImpl() {
		this.tika = new Tika();
	}

	@Override
	public boolean isValidFileByFileType(MultipartFile file, String fileType) {
		LOGGER.debug("Executing isValidFileByFileType for fileType : {}", fileType);
		try {
			String detectedType = tika.detect(file.getInputStream());
			if (detectedType != null && detectedType.equals(fileType)) {
				return true;
			}

		} catch (Exception e) {
			LOGGER.error("Exception while isValidFileByFileType due to : ", e);
		}
		return false;
	}

}
