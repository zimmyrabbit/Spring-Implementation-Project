package com.project.spring.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.spring.dao.BoardTestMapper;

@Service
public class BoardTestService {

	@Autowired BoardTestMapper boardTestMapper;
	@Autowired ServletContext context;
	
	public ArrayList<Map<String, String>> getBoradTestList() {

		ArrayList<Map<String,String>> list = boardTestMapper.selectList();
		
		return list;
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public int writeBoard(HashMap<String, String> map, List<Map> file ) {
		
		
		int cnt = 0;
		cnt = boardTestMapper.insertBoardTest(map);
		HashMap<String, String> fileMap = new HashMap<String, String>();
		
		if(file != null && file.size() > 0) {
			fileMap.put("boardno",String.valueOf(map.get("no")));
			
			for(Map<String, String> data : file) {	
				fileMap.put("originName", data.get("originName"));
				fileMap.put("saveFilePath", data.get("saveFilePath"));
				fileMap.put("storedName", data.get("storedName"));
				fileMap.put("fileSize", data.get("fileSize"));
				fileMap.put("contentType", data.get("contentType"));
			}
			cnt = boardTestMapper.insertBoardTestFile(fileMap);
		}
		return cnt;
	}

	public HashMap<String, Object> detailBoard(int no) {

		HashMap<String, Object> map = boardTestMapper.getBoardDetail(no);
		
		return map;
	}

	public HashMap<String, Object> getFile(int fileno) {

		HashMap<String, Object> map = boardTestMapper.selectFile(fileno);
		
		return map;
	}

}
