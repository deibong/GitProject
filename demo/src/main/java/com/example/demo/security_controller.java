package kr.co.lee;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller		//security interface를 로드함
public class security_controller implements security {
	
	@GetMapping("/userpass.do")
	public String userpass() {
		String pw = "1004apink";
		try {
			//secode : default 메소드를 핸들링한 형태
			StringBuilder result = secode(pw);
			System.out.println(result);
		}catch (Exception e) {
			System.out.println("패스워드 변경 오류!!");
		}
		
		return null;
	}
	
	
	//base64 암호화 기술
	@GetMapping("/se1.do")
	public String se1() {
		String pw = "a123456";
		byte pw2[] = pw.getBytes(); //문자를 byte로 변환
		Encoder code = Base64.getEncoder(); //해당 암호화(base64)
		byte se[] = code.encode(pw2); //해당 내용을 byte 암호화 시킴
		
		String se_pw = new String(se);
		
		System.out.println(new String(se)); //byte 배열을 문자열로 변경시킴
		
		return null;
	}
	
	//base64 복호화 기술
	@GetMapping("/se2.do")
	public String se2() {
		String pw = "YTEyMzQ1Ng==";
		Decoder dc = Base64.getDecoder();
		byte repw[] = dc.decode(pw);
		System.out.println(new String(repw));
		
		return null;
	}
	
	@GetMapping("/se3.do")
	public String se3() {
		String pw = "a123456";
		try {
			//md5 : 암호화 기술
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(pw.getBytes());
			byte[] repw = md5.digest();
			StringBuilder sb = new StringBuilder();
			for(byte w : repw) {
				//문자포맷 : %x(소문자+숫자), %X(대문자+숫자)
				//%02x : 2진수 형태의 소문자+숫자 암호화
				//%05x : 5진수 형태의 소문자+숫자 암호화
				String word = String.format("%05x", w);
				sb.append(word);
			}
			System.out.println(sb);
			
			
		}catch(Exception e) {
			System.out.println("해당 문자는 암호화가 불가능 합니다.");
		}
		
		return null;
	}
	
	@GetMapping("/se4.do")
	public String se4() {
		try {
			String pass = "a123456";
			//MD5 < SHA1 < SHA-224 < SHA-256 < SHA-512
			MessageDigest sha = MessageDigest.getInstance("SHA-224");
			sha.update(pass.getBytes());
			byte[] m = sha.digest();
			StringBuilder sb = new StringBuilder();
			for(byte w : m) {
				String code = String.format("%x", w);
				sb.append(code);
			}
			System.out.println(sb);
			
		}catch(Exception e) {
			System.out.println("해당 문자는 암호화가 불가능 합니다.");
		}
		return null;
	}
}
