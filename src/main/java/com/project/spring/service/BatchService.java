package com.project.spring.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.spring.dao.BatchMapper;

@Service("BatchTest")
public class BatchService {
	
	@Autowired BatchMapper batchMapper;
	
	public void batchTest() {
		
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String batchDate = simpleDateFormat.format(date);
		
		System.out.println(batchDate);
		
		batchMapper.insertBatch(batchDate);
	}

}
