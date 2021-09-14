package com.project.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.project.spring.service.BoardTestService;

@Controller
public class BoardTestController {

	private static final Logger logger = LoggerFactory.getLogger(BoardTestController.class);
	
	@Autowired
	BoardTestService boardTestService;
	
	//리스트
	@RequestMapping(value="/board/list", method=RequestMethod.GET)
	public String boardTestList(Model model) {
		
		ArrayList<Map<String, String>> list = boardTestService.getBoradTestList();
		
		model.addAttribute("list", list);
		
		return "board/list";
	}
	
	//글쓰기
	@RequestMapping(value="/board/write", method=RequestMethod.GET)
	public void write() {}
	
	@RequestMapping(value="/board/write", method=RequestMethod.POST)
	public String writeProc(String title, String content, MultipartFile file) {
		
		HashMap<String,String> map = new HashMap<>();
		map.put("title", title);
		map.put("content", content);

		boardTestService.writeBoard(map,file);
		
		return "redirect: /board/list";
	}
	
	//상세보기
	@RequestMapping(value="/board/detail", method=RequestMethod.GET)
	public String boardTestDetail(Model model, int no) {
		
		HashMap<String, Object> map = boardTestService.detailBoard(no);
		
		model.addAttribute("map", map);
		
		return "board/detail";
	}

}
