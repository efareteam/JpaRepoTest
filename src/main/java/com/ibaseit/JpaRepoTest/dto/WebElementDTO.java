package com.ibaseit.JpaRepoTest.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebElementDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -560170698514393580L;

	private Integer htmlElementId;

	private Integer productId;

	private String htmlElementName;

	private String elementType;

	private String elementClass;

	private String moduleName;

	private String pageName;

	private String elementId;

	private String elementCss;

	private String elementXpath;

	private String recommenedPath;

	private String clients;

	private Integer waitTime;

}
