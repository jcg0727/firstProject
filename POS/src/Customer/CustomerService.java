package Customer;

import java.util.List;

import Util.ScanUtil;
import Util.View;

public class CustomerService {
	private CustomerService() {}
	private static CustomerService instance = new CustomerService();
	public static CustomerService getInstance() {
		return instance;
	}
	
	
	public static int customer() throws Exception {
		try {
			CustomerDAO customerDAO = new CustomerDAO();
			System.out.println("------------------------------------");
			System.out.println("고객관리");
			System.out.println("1.검색\t\t2.삭제\t\t3.뒤로가기");
			System.out.println("-------------------------------------");
			System.out.print("선택해주세요: ");
			int menu = ScanUtil.nextInt();
			
			switch(menu) {
				case 1:
				List<CustomerDTO> selectCusotmerList = customerDAO.selectCustomerList();
				System.out.println("번호 \t 이름 \t\t 연락처 \t\t 마일리지");
				for(CustomerDTO mem : selectCusotmerList) {
					System.out.println(mem.getId() +" \t "+ mem.getName() + "\t" + mem.getHpNumber() + "\t" + mem.getMileage());	
				}	
				return View.ADMINMENU;
				
				case 2:
				System.out.println("삭제하기");
				System.out.print("삭제할 이름: ");
				String name = ScanUtil.nextLine();
				customerDAO.deleteCustomer(name);
				return View.ADMINMENU;
				
				case 3:
				return View.ADMINMENU;
			}
			return menu;
		} catch (Exception e) {
			System.err.println("잘못 입력하셨습니다");
			return View.CUSTOMER;
		}
	}
}
