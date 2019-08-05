package com.ibaseit.JpaRepoTest.serviceimpl;

import static com.ibaseit.JpaRepoTest.constants.ValidationUtilConstants.ERROR_CODE;
import static com.ibaseit.JpaRepoTest.constants.ValidationUtilConstants.ERROR_MSG;
import static com.ibaseit.JpaRepoTest.constants.ValidationUtilConstants.FAILED;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibaseit.JpaRepoTest.dto.WebElementDTO;
import com.ibaseit.JpaRepoTest.exception.ServiceGenericException;
import com.ibaseit.JpaRepoTest.model.HtmlElement;
import com.ibaseit.JpaRepoTest.repository.PersonRepository;
import com.ibaseit.JpaRepoTest.service.PersonService;
import com.ibaseit.JpaRepoTest.util.InputValidationUtil;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonRepository personRepo;

	@Override
	public HtmlElement saveWebElement(Object jsonObj) throws ServiceGenericException, IOException {
		HtmlElement htmlElement = new HtmlElement();

		Map<String, Object> isValid = InputValidationUtil
				.validateJsonInputFields(jsonObj/* JsonConverter.convertObjToJson(webElementDTO) */, "htmlelement");
		if (isValid.containsKey(FAILED))
			throw new ServiceGenericException(isValid.get(ERROR_CODE).toString(), isValid.get(ERROR_MSG).toString());

		// BeanUtils.copyProperties(webElementDTO, htmlElement);
		return personRepo.save(htmlElement);
	}

	@Override
	public Object saveWebElementWithDTO(WebElementDTO dtoObj) throws IOException, ServiceGenericException {
		HtmlElement htmlElement = new HtmlElement();

		/*
		 * Map<String, Object> isValid = InputValidationUtil
		 * .validateJsonInputFields(JsonConverter.convertObjToJson(dtoObj),
		 * "htmlelement"); if (isValid.containsKey(FAILED)) throw new
		 * ServiceGenericException(isValid.get(ERROR_CODE).toString(),
		 * isValid.get(ERROR_MSG).toString());
		 */

		return personRepo.save(htmlElement);
	}

	@Override
	public Object saveWebElementWithObject(Object jsonObj) throws IOException, ServiceGenericException {
		HtmlElement htmlElement = new HtmlElement();

		/*
		 * Map<String, Object> isValid =
		 * InputValidationUtil.validateJsonInputFields(jsonObj, "htmlelement"); if
		 * (isValid.containsKey(FAILED)) throw new
		 * ServiceGenericException(isValid.get(ERROR_CODE).toString(),
		 * isValid.get(ERROR_MSG).toString());
		 */

		return personRepo.save(htmlElement);
	}

}
