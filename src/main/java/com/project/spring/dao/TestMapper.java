package com.project.spring.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository("TestMapper")
public interface TestMapper {
		
	List<Map<String, String>> getList() throws Exception;
	
}
