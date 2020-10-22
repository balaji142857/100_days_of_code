package com.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

@Service
public class MyServiceImpl implements MyService {

	@Override
	@LogTime
	public void someMethod(Long waitTime) {
		try {
			TimeUnit.MILLISECONDS.sleep(waitTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
