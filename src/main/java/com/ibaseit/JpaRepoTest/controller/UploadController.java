package com.ibaseit.JpaRepoTest.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import com.ibaseit.JpaRepoTest.util.MimeTypeValidateUtil;

import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;

@RestController
@RequestMapping("/upload")
public class UploadController {
	static final Logger log = Logger.getLogger(UploadController.class);

	@Autowired
	MimeTypeValidateUtil mimeUtil;

	@GetMapping(value = "/msg", produces = MediaType.APPLICATION_JSON)
	public Object getMessage() {
		log.info("Start getMessage() in UnsecureController");
		return "Hello Unsecured Controller";
	}

	@PostMapping(value = "uploadFile", produces = MediaType.APPLICATION_JSON, consumes = MediaType.MULTIPART_FORM_DATA)
	public Object uploadFile(@RequestBody MultipartFile file, HttpServletRequest req)
			throws IOException, MagicParseException, MagicMatchNotFoundException, MagicException, SAXException, TikaException {

		log.info("Started uploadFile()");

		return mimeUtil.validateMimeType(file, req);
	}

}
