package com.app.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileContentValidatorService {

	boolean isValidFileByFileType(MultipartFile file, String fileType);
}
