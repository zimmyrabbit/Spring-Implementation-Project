package com.project.spring.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView {

	@Autowired
	ServletContext context;
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HashMap<String, Object> map = (HashMap) model.get("map");
		
		//contextPath
		String path = context.getRealPath("resources");
		
		//storedName
		String storedName = (String)map.get("storedName");
		
		//파일 객체
		File src = new File(path, storedName);
		
		//파일 입력 스트림
		FileInputStream fis = new FileInputStream(src);
		
		
		//파일을 전송하는 ContentType 설정 (이진데이터)
		response.setContentType("application/octet-stream");
		
		//응답 데이터 크기 설정
		response.setContentLength((int)src.length());
		
		//응답 데이터 인코딩 설정
		response.setCharacterEncoding("UTF-8");
		
		//클라이언트가 파일을 저장할 때 사용할 이름 인코딩 설정
		String outputName = URLEncoder.encode((String)map.get("originName"), "UTF-8");
		
		//브라우저가 다운로드에 사용할 이름 설정
		response.setHeader("Content-Disposition", "attachment; filename=\"" + outputName + "\"");
		
		//서버 응답 출력 스트림
		OutputStream out = response.getOutputStream();
		
		//서버 -> 클라이언트 파일복사
		FileCopyUtils.copy(fis,out);
		
	}
}
