package com.project.spring.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.view.document.AbstractXlsxStreamingView;
import net.sf.jxls.transformer.XLSTransformer;

public class ExcelView extends AbstractXlsxStreamingView {

	private static final String template_excel = "/template/stat/template_excel.xlsx";
	
	@SuppressWarnings("resource")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		OutputStream os = null;
		InputStream is = null;
		try {
			String fileName = "게시판리스트";
			is = new ClassPathResource(template_excel).getInputStream();
			response.setHeader("Content-Type", "application/octet-stream");
			response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s.xlsx\"", URLEncoder.encode(fileName, "UTF-8")));
	
			os = response.getOutputStream();
			XLSTransformer transformer = new XLSTransformer();
	
			Workbook excel = transformer.transformXLS(is, model);
			
			excel.write(os);
			os.flush();
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
