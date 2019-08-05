package com.ibaseit.JpaRepoTest.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.microsoft.ooxml.OOXMLParser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import eu.medsea.mimeutil.MimeUtil;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;

@Component
@PropertySource("classpath:fileinfo/mimeinfo.properties")
public class MimeTypeValidateUtil {
	static final Logger log = Logger.getLogger(MimeTypeValidateUtil.class);

	@Autowired
	private Environment env;

	private final String EXTENSION = "extension";

	public Object validateMimeType(MultipartFile file, HttpServletRequest req) throws IOException, MagicParseException,
			MagicMatchNotFoundException, MagicException, SAXException, TikaException {
		Map<String, Object> respMap = new HashMap<>();

		if (!checkMimeType(file, req, respMap))
			return InputValidationUtil.errorResponse("415", "Unsupported MIME Type");

		return InputValidationUtil.successResponse(respMap);
	}

	private boolean checkMimeType(MultipartFile file, HttpServletRequest req, Map<String, Object> respMap)
			throws IOException, MagicParseException, MagicMatchNotFoundException, MagicException, TikaException {

		InputStream is = new BufferedInputStream(new ByteArrayInputStream(file.getBytes()));
		Path path = new File(file.getOriginalFilename()).toPath();
		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		ServletContext servletContext = req.getServletContext();
		Tika tika = new Tika();
		MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.MagicMimeMimeDetector");

		String fileName = file.getOriginalFilename();
		String extension = FilenameUtils.getExtension(fileName);
		String mimeTypeFromProp = env.getProperty(EXTENSION + "." + extension);

		if (mimeTypeFromProp.equals(getMimeTypeViaMethod(file, is, tika, extension))) {
			log.info("file is valid...");
			respMap.put("extension", extension);
			respMap.put("mimeType1_via_guessContentType(inputstream)", URLConnection.guessContentTypeFromStream(is));
			respMap.put("mimeType2_via_probeContentType(filename)", Files.probeContentType(path));
			respMap.put("mimeType3_via_getMagicMatch()", Magic.getMagicMatch(file.getBytes(), false).getMimeType());
			respMap.put("mimeType4_via_getContentType(filename)", fileTypeMap.getContentType(fileName));
			respMap.put("mimeType5_via_getMimeType(filename)", servletContext.getMimeType(fileName));
			respMap.put("mimeType6_via_getcontentType()", file.getContentType());
			respMap.put("mimeType7_via_tika_detect(inputStream)", tika.detect(is));
			respMap.put("mimeType8_via_guessContentType(filename)",
					URLConnection.guessContentTypeFromName(file.getOriginalFilename()));
			respMap.put("mimeType9_via_tika_detect(filename)", tika.detect(fileName));
			respMap.put("mimeType10_via_getContentTypeFor(filepath)",
					URLConnection.getFileNameMap().getContentTypeFor("file://" + path));
			respMap.put("mimeType11_via_MimeUtil", MimeUtil.getMimeTypes(is).toString());

			return true;
		}

		return false;

	}

	private String getMimeTypeViaMethod(MultipartFile file, InputStream is, Tika tika, String extension)
			throws MagicParseException, MagicMatchNotFoundException, MagicException, IOException, TikaException {
		String mimeType = null;

		if ("csv".equals(extension) || "xls".equals(extension)) {

			mimeType = MimeUtil.getMimeTypes(is).toString();

		} else if ("pdf".equals(extension) || "html".equals(extension) || "txt".equals(extension)
				|| "jpeg".equals(extension) || "jpg".equals(extension) || "png".equals(extension)) {

			mimeType = Magic.getMagicMatch(file.getBytes(), false).getMimeType();

		} else if ("xml".equals(extension) || "bmp".equals(extension)) {

			mimeType = tika.detect(is);

		} else if ("xlsx".equals(extension) || "docx".equals(extension) || "doc".equals(extension)) {

			Metadata metadata = new Metadata();

			// OOXml parser
			OOXMLParser msofficeparser = new OOXMLParser();
			try {
				msofficeparser.parse(file.getInputStream(), new BodyContentHandler(new StringWriter()/*-1*/), metadata, new ParseContext());
				log.info("Content-Type : " + metadata.get("Content-Type"));
				mimeType = metadata.get("Content-Type");
			} catch (Exception ex) {
				log.error(ex);
				mimeType = null;
			}
		}

		return mimeType;
	}

}
