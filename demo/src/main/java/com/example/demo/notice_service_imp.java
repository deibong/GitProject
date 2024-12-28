package kr.co.lee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class notice_service_imp implements notice_service {
	
	@Autowired
	protected notice_mapper nm;
	
	@Override
	public int notice_insert(notice_DTO nd) {
		int result = nm.notice_insert(nd);
		return result;
	}
}
