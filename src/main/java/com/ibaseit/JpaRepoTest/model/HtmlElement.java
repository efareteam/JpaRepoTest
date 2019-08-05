package com.ibaseit.JpaRepoTest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tf_html_element")
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class HtmlElement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "html_element_id")
	private Integer htmlElementId;

	@Column(name = "product_id")
	private Integer productId;

	@Column(name = "html_element_name")
	private String htmlElementName;
	
	@Column(name = "element_type")
	private String elementType;
	
	@Column(name = "element_class")
	private String elementClass;

	@Column(name = "module_name")
	private String moduleName;

	@Column(name = "page_name")
	private String pageName;
	
	@Column(name = "element_id")
	private String elementId;
	
	@Column(name = "element_css")
	private String elementCss;
	
	@Column(name = "element_xpath")
	private String elementXpath;
	
	@Column(name = "recommened_path")
	private String recommenedPath;
	
	@Column(name = "clients")
	private String clients;
	
	@Column(name = "wait_time")
	private Integer waitTime;

}
