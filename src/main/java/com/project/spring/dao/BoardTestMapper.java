package com.project.spring.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BoardTestMapper {

	public int getCount();
	
	public ArrayList<Map<String, Object>> getList(int displayPost, int postNum);

	public ArrayList<Map<String, Object>> selectList();

	public int insertBoardTest(HashMap<String, String> map);

	public int insertBoardTestFile(HashMap<String, String> fileMap);

	public HashMap<String, Object> getBoardDetail(int no);

	public HashMap<String, Object> selectFile(int fileno);

	public void insertMapInfo(HashMap<String, String> map);

	public List<Map<String, Object>> selectMapList();

	

}
