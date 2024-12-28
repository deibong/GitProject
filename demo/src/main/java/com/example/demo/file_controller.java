package kr.co.lee;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class file_controller {
	
	PrintWriter pw = null;
	
	//파일전송 응용편
	@PostMapping("/fileupok3.do")
	public String fileupok3(ServletRequest req, ServletResponse res,
			@RequestParam("mfile") List<MultipartFile> files) {
	//xls파일만 저장 (application/vnd.openxmlformats-officedocument.spreadsheetml.sheet)
		res.setContentType("text/html;charset=utf-8");
		try {
			int fileno = files.size();
			String url = req.getServletContext().getRealPath("/payfile/");
			int w = 0;
			while(w < fileno) {
				
				String oriname = files.get(w).getOriginalFilename();
				String types = oriname.substring(oriname.lastIndexOf("."));
				
				String filetype = files.get(w).getContentType();
				System.out.println(filetype);
				//UUID - 암호화 알고리즘 클래스(Spring 전용)
				/*
				   UUID : version1 (PC - Macaddress 형태로 암호화)
				   UUID : version3 (MD5, hash)
				   UUID : version4 (Random 함수를 이용해서)
				   UUID : version5 (SHA-1 함수를 이용해서)
				 */
				String uuid = UUID.randomUUID().toString();
				String newname = url + uuid + types;
				if(files.get(w).getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
					FileCopyUtils.copy(files.get(w).getBytes(), new File(newname));
				}
				w++;
			}
			this.pw = res.getWriter();
			this.pw.print("<script>"
					+ "alert('등록하신 첨부파일이 정상적으로 모두 저장 되었습니다.');"
					+ "history.go(-1);"
					+ "</script>");
			this.pw.close();
		}catch (Exception e) {
			this.pw.print("<script>"
					+ "alert('첨부파일이 올바르지 않거나, 해당 용량이 맞지 않아서 저장 되지 않습니다.');"
					+ "history.go(-1);"
					+ "</script>");
		}
		
		return null;
	}
	
	
	//복합 파일 전송
	@PostMapping("/fileupok2.do")
	public String fileupok2(HttpServletRequest req, HttpServletResponse res,
			@RequestParam("mfile") MultipartFile files[]) throws IOException {
		res.setContentType("text/html;charset=utf-8");
		
		//파일 저장 경로
		String url = req.getServletContext().getRealPath("/upload/");
		int w = 0;
		
		//이름 변경하여 저장
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHms");
		String ymd = df.format(today);
		
		while(w < files.length) {
			
			if(!files[w].getOriginalFilename().equals("")) {
				int no = (int)Math.ceil(Math.random()*1000);
				String nm = files[w].getOriginalFilename().substring(files[w].getOriginalFilename().lastIndexOf("."));
				String rename = ymd + no + nm;
				FileCopyUtils.copy(files[w].getBytes(), new File(url + rename));
			}
			
			w++;
		}
		this.pw = res.getWriter();
		this.pw.print("<script>"
				+ "alert('등록하신 첨부파일이 정상적으로 모두 저장 되었습니다.');"
				+ "history.go(-1);"
				+ "</script>");
		
		return null;
	}
	
	//단일 파일 전송
	@PostMapping("/fileupok1.do")
	public String fileupok1(@RequestParam("mfile") MultipartFile files,
			HttpServletResponse res, HttpServletRequest req) throws Exception {
		
		res.setContentType("text/html;charset=utf-8");
		
		String filenm = files.getOriginalFilename();    //파일명
		long filesize = files.getSize();    //파일 용량 크기
		String filetype = files.getContentType();		//파일 속성
		System.out.println(filenm);
		System.out.println(filesize);
		System.out.println(filetype);
		
		this.pw = res.getWriter();
		//2MB 이하의 용량만 처리하겠다는 조건문 입니다.
		if(filesize > 2097152) {
			this.pw.print("<script>"
					+ "alert('첨부파일 용량은 최대 2MB까지만 등록 가능합니다.');"
					+ "history.go(-1);"
					+ "</script>");
		}
		else {	//첨부파일 image 또는 압축파일, pdf, xls, doc 허용
			//.jsp .java .php .asp .ini .js => 해킹의 위험으로 인하여 절대 첨부파일에 적용하지 않음
			if(filetype.equals("image/jpeg") || filetype.equals("image/png")
					|| filetype.equals("image/gif") || filetype.equals("image/bmp")
					|| filetype.equals("image/webp")) {
				
				String url = req.getServletContext().getRealPath("/upload/");
				File fe = new File(url+filenm); //경로+기존파일명
				byte bt[] = files.getBytes();
				FileCopyUtils.copy(bt, fe);
				
				this.pw.print("<script>"
						+ "alert('정상적으로 파일이 업로드 되었습니다.');"
						+ "history.go(-1);"
						+ "</script>");		
			}
			else {
				this.pw.print("<script>"
						+ "alert('첨부파일은 이미지만 업로드 가능합니다.');"
						+ "history.go(-1);"
						+ "</script>");
			}
			this.pw.close();
		}
		
		return null;
	}
}
