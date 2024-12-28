package kr.co.lee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service	//해당 interface를 로드하면 해당 class를 실행시키는 어노테이션 입니다.
public class shopping_serviceImpl implements shopping_service {
	
	//mapper.xml에 있는 DDL 명령어 실행함
	@Autowired
	private shopping_mapper sm;
	
	@Override
	public List<member_DTO> login_id(String mid) {
		List<member_DTO> mdto = sm.login_id(mid);
		return mdto;
	}
	
	@Override
	public String search_id(String mid) {	//아이디 중복 체크 메소드
		String result = sm.search_id(mid);
		return result;
	}

	@Override
	public int member_join(member_DTO mdto) {
		int result = sm.member_join(mdto);
		return result;
	}
}
