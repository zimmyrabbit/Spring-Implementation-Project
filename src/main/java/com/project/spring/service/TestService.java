package com.project.spring.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.spring.dao.TestMapper;


@Service("TestService")
public class TestService {

	@Autowired
	private TestMapper test;
	
	public List<Map<String, String>> getList() throws Exception {
		return test.getList();
	} 
	
}
