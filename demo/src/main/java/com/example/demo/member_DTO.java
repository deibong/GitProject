package kr.co.lee;

import org.springframework.stereotype.Repository;

import lombok.Data;

//spring boot에서 사용하는 Data 어노테이션
@Data   //Getter, Setter, Hashcode, 즉시 실행, toString()
@Repository("memberdto")   //@Autowired, @Resource 에서 응용이 가능한 어노테이션
public class member_DTO {
   int midx;
   String mid, mpass, mname, telcorp, mhp, memail, mpost, mroad, maddr, magree1, magree2, mjoin;
   
}