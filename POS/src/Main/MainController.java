package Main;

import CustomerJoin.CustomerJoinService;
import Employee.EmployeeService;
import Main.AdminController;
import Util.View;
import Main.CustomerMenuService;


public class MainController<CustomerMenuService> {

	public static void main(String[] args) throws Exception {
		new MainController().start();
	}

	private LoginService loginService = LoginService.getInstance();
	private AdminService adminservice = AdminService.getInstance();
	private EmployeeService employeeservice = EmployeeService.getInstance();
	private CustomerJoinService customerservice = CustomerJoinService.getInstance();
	
	public int start() throws Exception {
		int view = View.HOME;
		
		while (true) {
			switch (view) {
				case View.HOME:
					view = loginService.home();
					break;

				case View.ADMINLOGIN:
					view = loginService.admin();
					break;

				case View.CUSTOMERMENU:
					view = CustomerController.service();
					break;

				case View.ADMINMENU:
					view = AdminController.service();
					break;
			}
		}
	}
}
