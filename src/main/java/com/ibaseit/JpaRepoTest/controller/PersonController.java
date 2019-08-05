package com.ibaseit.JpaRepoTest.controller;

import static com.ibaseit.JpaRepoTest.constants.ValidationUtilConstants.ERROR_CODE;
import static com.ibaseit.JpaRepoTest.constants.ValidationUtilConstants.ERROR_MSG;
import static com.ibaseit.JpaRepoTest.constants.ValidationUtilConstants.FAILED;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibaseit.JpaRepoTest.converter.JsonConverter;
import com.ibaseit.JpaRepoTest.dto.WebElementDTO;
import com.ibaseit.JpaRepoTest.exception.ServiceGenericException;
import com.ibaseit.JpaRepoTest.service.PersonService;
import com.ibaseit.JpaRepoTest.util.InputValidationUtil;

@RestController
@RequestMapping("/person")
public class PersonController {

	static final Logger log = Logger.getLogger(PersonController.class);

	@Autowired
	PersonService service;

	@RequestMapping(value = "/saveWebElementWithDTO", method = RequestMethod.POST, produces = "application/json")
	public Object saveWebElementWithDTO(@RequestBody WebElementDTO dtoObj) throws ServiceGenericException, IOException {
		log.info("Start Time :::::::::::::::++++++++++::::::::::   " + System.currentTimeMillis());

		Map<String, Object> isValid = InputValidationUtil
				.validateJsonInputFields(JsonConverter.convertObjToJson(dtoObj), "htmlelement");
		if (isValid.containsKey(FAILED))
			return InputValidationUtil.errorResponse(isValid.get(ERROR_CODE).toString(),
					isValid.get(ERROR_MSG).toString());

		Object ele = service.saveWebElementWithDTO(dtoObj);
		log.info("End Time :::::::::::::::++++++++++::::::::::   " + System.currentTimeMillis());
		return ele;
	}

	@RequestMapping(value = "/saveWebElementWithObject", method = RequestMethod.POST, produces = "application/json")
	public Object saveWebElementWithObject(@RequestBody Object jsonObj) throws ServiceGenericException, IOException {
		log.info("Start Time :::::::::::::::++++++++++::::::::::   " + System.currentTimeMillis());

		Map<String, Object> isValid = InputValidationUtil.validateJsonInputFields(jsonObj, "htmlelement");
		if (isValid.containsKey(FAILED))
			return InputValidationUtil.errorResponse(isValid.get(ERROR_CODE).toString(),
					isValid.get(ERROR_MSG).toString());

		Object ele = service.saveWebElementWithObject(jsonObj);
		log.info("End Time :::::::::::::::++++++++++::::::::::   " + System.currentTimeMillis());
		return ele;
	}

	@RequestMapping(value = "/pollwhatsappMessages", method = RequestMethod.POST, produces = "application/json")
	public String pollwhatsappMessages() throws IOException {

		log.info("polling wtsapp messages  -->");

		return "yes Got It";

	}

}
