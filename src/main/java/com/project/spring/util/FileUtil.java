package com.project.spring.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class FileUtil
{
	private static long MAX_FILE_SIZE = 100485760;

	public static List<Map> multiUploadFile(HttpServletRequest request, String fileuploadPath, String subUploadPath) throws Exception
	{
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;

		Map files = multiRequest.getFileMap();

		String uploadPath = fileuploadPath + File.separatorChar + subUploadPath;
		System.out.println("uploadPath ==> "+uploadPath);
		File saveFolder = new File(uploadPath);
		String fileName = null;
		List result = new ArrayList();

		boolean isDir = false;

		if ((!saveFolder.exists()) || (saveFolder.isFile())) {
			boolean bool1 = saveFolder.mkdirs();
		}
		
		//max file size check
		Iterator itr = files.entrySet().iterator();

		while (itr.hasNext()) {
			Map.Entry entry = (Map.Entry)itr.next();
			MultipartFile file = (MultipartFile)entry.getValue();
			System.out.println("File Size ===>>>  "+file.getSize());
			if(MAX_FILE_SIZE < file.getSize()) {
				throw new Exception("============ DownLoad File Size Over MaxSize =====================");
			}
		}
		
		Iterator fitr = files.entrySet().iterator();
		Map fileInfo = new HashMap();
		while (fitr.hasNext()) {
			Map.Entry entry = (Map.Entry)fitr.next();
			MultipartFile file = (MultipartFile)entry.getValue();
			fileName = file.getOriginalFilename();
			
			if (!"".equals(fileName)) {
				fileInfo = new HashMap();
				String nFileName = UUID.randomUUID().toString();

				String ext = fileName.substring(fileName.lastIndexOf("."));

				fileInfo.put("originName", fileName);
				fileInfo.put("saveFilePath", uploadPath);
				fileInfo.put("storedName", nFileName + ext);
				fileInfo.put("fileSize", String.valueOf(file.getSize()));
				fileInfo.put("contentType", ext);

				String filePath = uploadPath + File.separatorChar + nFileName + ext;
				file.transferTo(new File(filePath));
				
				result.add(fileInfo);				
			}
		}

		return result;
	}
}
