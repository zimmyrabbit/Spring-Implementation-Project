package com.project.spring.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;

public class FileUtil
{
	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
	public static List<Map> ffmpegUploadFile(HttpServletRequest request, String fileuploadPath, String subUploadPath) throws IOException, Exception
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
			if(Constants.MAX_VIDEO_FILESIZE < file.getSize()) {
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
				String realFileName = UUID.randomUUID().toString();

				String ext = fileName.substring(fileName.lastIndexOf("."));
				String filePath = uploadPath + File.separatorChar + nFileName + ext;  // 업로드 영상
				String realPath = uploadPath + File.separatorChar + realFileName + ext; // 실제 저장 경로
				file.transferTo(new File(filePath)); //업로드한 동영상 저장
				
				FFmpeg ffmpeg = new FFmpeg(Constants.FFMPEG_PATH); // ffmpeg 경로 
				FFprobe ffprobe = new FFprobe(Constants.FFPROBE_PATH);  // ffprobe 경로
				FFmpegProbeResult info = ffprobe.probe(filePath); // 업로드한 파일의 정보 추출
				int bit_rate = (int)info.getFormat().bit_rate > 1048000 ? 1048000 : (int)info.getFormat().bit_rate; // 업로드한 동영상의 비트레이트 추출
				
				logger.debug("====== Video Conversion Start ======");
				/* 파일변환 실행 */
				FFmpegBuilder builder = new FFmpegBuilder().setInput(filePath)
					.addOutput(realPath)  // 저장할 경로
					.setVideoResolution(800, 600)  // 해상도 x, y
					.setVideoBitRate(bit_rate)  // 비트레이트 ( 용량과 제일 밀접함 --> 높을수록 화질좋고 용량커짐)
					.setVideoCodec("libx264")  // 코덱
					.setStrict(FFmpegBuilder.Strict.EXPERIMENTAL)
					.done();
				
				FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);	
				try {
					executor.createJob(builder, p -> {
						if(p.isEnd()) {
							logger.debug("Video conversion Successfully completed ===>> " + realPath);
							logger.debug("Video File Size(Byte) ===>> " + p.total_size);
						}
					}).run();
				}catch(Exception e ) {
					e.getMessage();
					logger.debug("Video conversion failed ===>>" + fileName);
				}
				/* 동영상 변환 완료 ===> 실제 저장 경로에 변환 동영상 저장 */

				File removeFile = new File(filePath);
				removeFile.delete();  /* 기존 업로드 저장파일은 삭제해준다 */
				
				FFmpegProbeResult realInfo = ffprobe.probe(realPath);  // 변환된 동영상의 정보 추출

				fileInfo.put("originName", fileName);
				fileInfo.put("saveFilePath", uploadPath);
				fileInfo.put("storedName", realFileName + ext);
				fileInfo.put("fileSize", String.valueOf(realInfo.getFormat().size));
				fileInfo.put("contentType", ext);
				
				result.add(fileInfo);				
			}
		}

		return result;
	}
	
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
			if(Constants.MAX_FILESIZE < file.getSize()) {
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
