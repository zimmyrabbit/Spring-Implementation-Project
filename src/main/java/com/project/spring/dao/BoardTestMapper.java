package com.project.spring.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface BoardTestMapper {

	public ArrayList<Map<String, String>> selectList();

	public void insertBoardTest(HashMap<String, String> map);

	public void insertBoardTestFile(HashMap<String, Object> fileMap);

	public HashMap<String, Object> getBoardDetail(int no);

	

}
