package com.ibaseit.JpaRepoTest.service;

import java.io.IOException;

import com.ibaseit.JpaRepoTest.dto.WebElementDTO;
import com.ibaseit.JpaRepoTest.exception.ServiceGenericException;

public interface PersonService {

	Object saveWebElement(Object jsonObj) throws ServiceGenericException, IOException;

	Object saveWebElementWithDTO(WebElementDTO dtoObj) throws IOException, ServiceGenericException;

	Object saveWebElementWithObject(Object jsonObj) throws IOException, ServiceGenericException;

}
