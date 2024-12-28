package kr.co.lee;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface shopping_mapper {
	String search_id(String mid);		//사용자 ID 체크
	int member_join(member_DTO mdto);	//신규가입 mapper
	List<member_DTO> login_id(String mid);		//로그인 사용자 정보 체크
}
