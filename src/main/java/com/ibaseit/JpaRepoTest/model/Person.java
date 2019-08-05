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
@Table(name = "test_person_tbl")
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "person_id")
	private Integer personId;

	@Column(name = "person_name")
	private String personName;

	@Column(name = "person_type")
	private String personType;
}