package com.ibaseit.JpaRepoTest.serviceimpl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ibaseit.JpaRepoTest.service.AppService;

@Service
public class AppServiceImpl implements AppService {

	static final Logger log = Logger.getLogger(AppServiceImpl.class);
	
	@Override
	public void getMessage() {
		log.info("Start getMessage() in AppServiceImpl");
		log.info("End getMessage() in AppServiceImpl");
	}

}
