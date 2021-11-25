package Main;

import Util.ScanUtil;
import Util.View;
import CustomerJoin.CustomerJoinDAO;
import CustomerJoin.CustomerJoinDTO;
import Main.LoginService;
import Main.MainController;

public class CustomerMenuService {
	private CustomerMenuService() {}
	private static CustomerMenuService instance = new CustomerMenuService();
	public static CustomerMenuService getInstance() {
		return instance;
	}
	
	
	public static int service() throws Exception {
		  try {
			System.out.println("******************Mega Star Place*******************");
			  System.out.println("1.메뉴보기\t2.회원가입\t3.회원정보\t0.메인메뉴");
			  System.out.println("*****************************************************");
			  System.out.print("선택해 주세요: ");
			  int input = ScanUtil.nextInt();
			  
			  switch(input) {
			  	case 1: 
				  return View.CUSTOMERGOODS;
			  	case 2:
				  return View.JOIN;
			  	case 3:
				  return View.CUSTOMERINFO;
			  	case 0:
				  return  View.HOME;
			  	default:
			  		System.err.println("잘못 입력하셨습니다.");
			  		return  View.CUSTOMERMENU;
			  }
		} catch (Exception e) {
			System.err.println("잘못 입력하셨습니다.");
			return View.CUSTOMERMENU;
		}
	}
}
