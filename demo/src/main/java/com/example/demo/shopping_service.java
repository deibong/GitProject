package kr.co.lee;

import java.util.List;

public interface shopping_service {
	public String search_id(String mid);	//Controller에서 Mapper전달
	public int member_join(member_DTO dto);
	public List<member_DTO> login_id(String mid);
}
