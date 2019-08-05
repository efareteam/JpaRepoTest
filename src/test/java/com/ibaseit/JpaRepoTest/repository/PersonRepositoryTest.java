package com.ibaseit.JpaRepoTest.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.ibaseit.JpaRepoTest.model.HtmlElement;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private PersonRepository personRepo;

	private HtmlElement element;
	
	@Before
	public void init() {
		element = new HtmlElement();
		element.setHtmlElementName("Text Box");
		element.setModuleName("Module1");
		element.setElementId("ele_id1");

	}

	@Test
	public void savePersonTest() {
		element = entityManager.persist(element);
		HtmlElement getFromDb = personRepo.findOne(element.getHtmlElementId());

		Assert.assertEquals(getFromDb, element);
	}
	
	

}
