package kr.co.lee;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class jstl_controller {
	
	@GetMapping("/jstl6.do")
	public String jstl6(Model m) {		//Model (jstl)
		String menu = "대메뉴 출력 파트!!";
		m.addAttribute("menu2",menu);
		return null;
	}
	/*
	   응용문제
	   다음 배열 데이터의 값을 이용하여 웹 페이지에 출력되도록 코드를 작성하시오.
	   {"홍길동","강감찬","이순신","유관순","김유신"}
	   {"25","30","27","44","37"}
	   
	   해당 데이터를 다음과 같이 웹에 출력하시오.
	   홍길동 : 25
	   강감찬 : 30
	   이순신 : 27
	   유관순 : 44
	   김유신 : 37
	   
	   *긴급수정 : 사용자 리스트 중에서 30살 이상 되는 고객만 리스트 출력
	 */
	@GetMapping("/jstl7.do")
	public ModelAndView jstl7() {
		ModelAndView mv = new ModelAndView();
		String product = "LG 냉장고";
		ArrayList<String> al = new ArrayList<>();
		al.add("홍길동");
		al.add("강감찬");
		al.add("이순신");
		al.add("유관순");
		al.add("김유신");
		
		ArrayList<String> al2 = new ArrayList<>();
		al2.add("25");
		al2.add("30");
		al2.add("27");
		al2.add("44");
		al2.add("37");
		
		ArrayList<ArrayList<String>> all = new ArrayList<ArrayList<String>>();
		
		int w = 0;
		while(w < al.size()) {
			ArrayList<String> al3 = new ArrayList<>();
			al3.add(al.get(w));
			al3.add(al2.get(w));
			all.add(al3);
			w++;
		}
		
		mv.addObject("all",all);
		mv.addObject("pdname",product); //키이름으로 데이터값을 이관하여 => .jsp에 출력시키기 위함
		//mv.setViewName("/jstl8");  //jsp view 파일명을 지정
		return mv; //mv => ModelAndView => jstl7.jsp를 찾아서 return 출력
	}
}
