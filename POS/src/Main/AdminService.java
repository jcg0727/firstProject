package Main;

import Util.ScanUtil;
import Util.View;

public class AdminService {
	private AdminService() {}
	private static AdminService instance = new AdminService();
	public static AdminService getInstance() {
		return instance;
	}

	public int serviceList() {
		try {
			System.out.println("************************************************************************************");
			System.out.print("1.상품관리\t2.재고관리\t3.매출관리\t4.고객관리\t5.직원관리\t0.메인메뉴\n");
			System.out.println("************************************************************************************");
			System.out.print("선택해 주세요 : ");
			int input = ScanUtil.nextInt();
			switch(input){
				case 1: 
					return View.GOODS;
					
				case 2:
					return View.INVENTORY;
					
				case 3:
					return View.SALES;
				
				case 4:
					return View.CUSTOMER;
					
				case 5:
					return View.EMPLOYEE;
					
				case 0:
					return View.HOME;
				
				default:
					System.err.println("잘못 입력하셨습니다");
					return View.ADMINMENU;
			}
		} catch (Exception e) {
			System.err.println("잘못 입력하셨습니다");
			return View.ADMINMENU;
		}
	}
}
