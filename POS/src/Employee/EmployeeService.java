package Employee;

import java.util.List;
import java.util.Scanner;

import Util.ScanUtil;
import Util.View;

public class EmployeeService {
	private EmployeeService() {}
	private static EmployeeService instance = new EmployeeService();
	public static EmployeeService getInstance() {
		return instance;
	}

	public static int emp() throws Exception {
		try {
			EmployeeDAO employeeDAO = new EmployeeDAO();
			System.out.println("----------------------------------------------");
			System.out.println("직원관리");
			System.out.println("1.검색\t\t2.등록\t\t3.삭제\t\t4.뒤로가기");
			System.out.println("----------------------------------------------");
			System.out.print("선택해주세요: ");
			int menu = ScanUtil.nextInt();

			switch(menu) {
				case 1:
					List<EmployeeDTO> selectEmployeeList = employeeDAO.selectEmployeeList();
					System.out.println("이름\t\t 연락처 \t\t 직원번호");
					for(EmployeeDTO emp : selectEmployeeList) {
						System.out.println(emp.getName() +" \t "+ emp.getHpNumber() + "\t\t" + emp.getEmployeeId());	
					}	
					System.out.println();
					return View.ADMINMENU;
			
				case 2:
					System.out.println("등록하기");
					System.out.print("이름: ");
					String name = ScanUtil.nextLine();
					System.out.print("연락처: ");
					String number = ScanUtil.nextLine();
					System.out.print("사원번호: ");
					int empId = ScanUtil.nextInt();
					employeeDAO.insertEmployee(new EmployeeDTO(name, number, empId));
					return View.ADMINMENU;
			
				case 3:
					System.out.println("삭제하기");
					System.out.print("삭제할 이름: ");
					String name1 = ScanUtil.nextLine();
					employeeDAO.deleteEmployee(name1);
					return View.ADMINMENU;
			
				case 4:
					return View.ADMINMENU;
					
				default:
					System.err.println("잘못 입력하셨습니다.");
					return View.EMPLOYEE;
			}
		} catch (Exception e) {
			System.err.println("잘못 입력하셨습니다");
			return View.EMPLOYEE;
		}
	}
}
