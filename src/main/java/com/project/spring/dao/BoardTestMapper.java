package com.project.spring.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface BoardTestMapper {

	public ArrayList<Map<String, String>> selectList();

	public int insertBoardTest(HashMap<String, String> map);

	public int insertBoardTestFile(HashMap<String, String> fileMap);

	public HashMap<String, Object> getBoardDetail(int no);

	public HashMap<String, Object> selectFile(int fileno);

	

}
