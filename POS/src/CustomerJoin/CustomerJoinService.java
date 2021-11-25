package CustomerJoin;

import CustomerJoin.CustomerJoinDAO;
import CustomerJoin.CustomerJoinDTO;
import Main.CustomerController;
import Util.ScanUtil;
import Util.View;



public class CustomerJoinService {
	private CustomerJoinService() {}
	private static CustomerJoinService instance = new CustomerJoinService();
	public static CustomerJoinService getInstance() {
		return instance;
	}
	public int service() throws Exception {
		System.out.print("이름: ");
		String name = ScanUtil.nextLine();
		System.out.print("연락처: ");
		String hpNumber = ScanUtil.nextLine();
		CustomerJoinDAO.insertCustomer(new CustomerJoinDTO(name, hpNumber));
		return View.CUSTOMERMENU;
	}
}
