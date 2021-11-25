package Main;

import Util.View;
import Customer.CustomerService;
import CustomerGoods.CusotmerGoodsService;
import CustomerJoin.CustomerJoinService;
import Employee.EmployeeService;
import Inventory.InventoryService;
import Employee.EmployeeService;
import Goods.GoodsService;
import Inventory.InventoryService;
import Util.View;

public class CustomerController {
	public static void main(String[] args) throws Exception {
		new CustomerController().service();
	}

	private static CustomerMenuService customermenuservice = CustomerMenuService.getInstance();
	private static CustomerJoinService customerjoinservice = CustomerJoinService.getInstance();
	private static LoginService loginservice = LoginService.getInstance();
	private static AdminService adminservice = AdminService.getInstance();
	private EmployeeService employeeservice = EmployeeService.getInstance();
	private CustomerService customerservice = CustomerService.getInstance();
	private InventoryService inventoryservice = InventoryService.getInstance();
	private static CustomerInfoService customerinfoservice = CustomerInfoService.getInstance();
	private static CusotmerGoodsService cusomergoodsservice = CusotmerGoodsService.getInstance();
	static int service() throws Exception {
		int view = View.CUSTOMERMENU;
		
		adminLoop:
		while(true){
			switch(view) {
				case View.CUSTOMERMENU:
				view = customermenuservice.service();
				break;
				
				case View.CUSTOMERGOODS:
				view = cusomergoodsservice.menu();
				break;
				
				case View.JOIN:
				view = customerjoinservice.service();
				break;
				
				case View.CUSTOMERINFO:
				view = customerinfoservice.service();
				break;
				
				case View.HOME:
				view = loginservice.home();
				break adminLoop;
				}	
		}
		return view;
	}
}
