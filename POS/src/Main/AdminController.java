package Main;

import Customer.CustomerService;
import Employee.EmployeeService;
import Goods.GoodsService;
import Inventory.InventoryService;
import Main.AdminService;
import Sales.SalesService;
import Util.View;

public class AdminController {

	public static void main(String[] args) throws Exception {
		new AdminController().service();
	}
	
	private static LoginService loginService = LoginService.getInstance();
	private static AdminService adminservice = AdminService.getInstance();
	private EmployeeService employeeservice = EmployeeService.getInstance();
	private CustomerService customerservice = CustomerService.getInstance();
	private InventoryService inventoryservice = InventoryService.getInstance();
	private static SalesService salesservice = SalesService.getInstance();
		public static int service() throws Exception {
			int view = View.ADMINMENU;
			adminLoop:
			while(true) {
				switch(view){
					case View.ADMINMENU:
						view = adminservice.serviceList(); 
					break;
					
					case View.GOODS:
						view = GoodsService.goods();
					break;
					
					case View.INVENTORY:
						view = InventoryService.inventory();
					break;
					
					case View.SALES:
						view = salesservice.sales();
					break;
						
					case View.CUSTOMER:
						view = CustomerService.customer();
					break;
					
					case View.EMPLOYEE:
						view = EmployeeService.emp();
					break;
					
					case View.HOME:
						view = loginService.home();
						break adminLoop;
				}
			}
			return view;
		}
	}