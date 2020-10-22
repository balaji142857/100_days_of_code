package com.conditions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.MyService;

@RestController
@RequestMapping("/")
public class TestController {
	
	@Autowired
	private MyDao dao;
	
	@Autowired
	private MyService myService;
	
	@GetMapping("condition")
	public String getDaoType() {
		return dao.getClass().getName();
	} 
	
	@GetMapping("aop/{time}")
	public String testAop(@PathVariable Long time) {
		myService.someMethod(time);
		return "check logs - method call success";
	}

}
