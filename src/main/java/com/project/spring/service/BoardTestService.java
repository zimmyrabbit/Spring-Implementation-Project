package com.project.spring.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.spring.dao.BoardTestMapper;

@Service
public class BoardTestService {

	@Autowired BoardTestMapper boardTestMapper;
	@Autowired ServletContext context;
	
	public ArrayList<Map<String, String>> getBoradTestList() {

		ArrayList<Map<String,String>> list = boardTestMapper.selectList();
		
		return list;
	}

	public void writeBoard(HashMap<String, String> map, MultipartFile file) {
		
		boardTestMapper.insertBoardTest(map);
		
		System.out.println(String.valueOf(map.get("no")));
		
		if(!file.isEmpty()) {
			String storedPath = context.getRealPath("resources");
			
			System.out.println(storedPath);
			
			File stored = new File(storedPath);
			if(!stored.exists()) {
				stored.mkdir();
			}
			
			String originName = file.getOriginalFilename();
			String storedName = originName + UUID.randomUUID().toString().split("-")[4];
			
			int fileSize = (int)file.getSize();
			
			int dot = file.getOriginalFilename().lastIndexOf(".");
			String contentType = file.getOriginalFilename().substring(dot + 1);
			
			File dest = new File(stored, storedName);
			
			try {
				file.transferTo(dest);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			HashMap<String, Object> fileMap = new HashMap<>();
			fileMap.put("no", String.valueOf(map.get("no")));
			fileMap.put("originName", originName);
			fileMap.put("storedName", storedName);
			fileMap.put("fileSize", fileSize);
			fileMap.put("contentType", contentType);
			
			boardTestMapper.insertBoardTestFile(fileMap);
		}
	}

	public HashMap<String, Object> detailBoard(int no) {

		HashMap<String, Object> map = boardTestMapper.getBoardDetail(no);
		
		return map;
	}

}
