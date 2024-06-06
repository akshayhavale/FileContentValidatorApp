package com.app.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.service.FileContentValidatorService;

@RestController
@RequestMapping("/api")
public class FileContentValidatorApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileContentValidatorApi.class);

	private static final String SVG_FILE_TYPE = "image/svg+xml";

	@Autowired
	private FileContentValidatorService fileContentValidatorService;

	@PostMapping(path = "/validate", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.ALL_VALUE)
	public @ResponseBody ResponseEntity<String> validateFileType(
			@RequestParam(name = "file", required = true) MultipartFile file) {
		LOGGER.debug("Executing validateFileType");
		boolean doValideFileType = fileContentValidatorService.isValidFileByFileType(file, SVG_FILE_TYPE);
		if (doValideFileType) {
			return ResponseEntity.ok("Valid file type");
		}
		return ResponseEntity.badRequest().body("Invalid file type");
	}

}
