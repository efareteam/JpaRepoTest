package com.ibaseit.JpaRepoTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibaseit.JpaRepoTest.model.HtmlElement;

@Repository
public interface PersonRepository extends JpaRepository<HtmlElement, Integer> {

}
