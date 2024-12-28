package kr.co.lee;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Data
@Repository("notice_DTO")
public class notice_DTO {
	int nidx;
	String nsubject, nwriter, npass, ntext, ndate;
	
}
