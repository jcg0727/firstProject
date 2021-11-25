package Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Scanner;

public class EmployeeTest {
	public static void main(String[] args) throws Exception {
		System.out.println("직원관리");
		System.out.println("1.검색 2.등록 3.삭제");
		System.out.print("선택해주세요: ");
		Scanner scanner = new Scanner(System.in);
		EmployeeDAO employeeDAO = new EmployeeDAO();
		int menu = scanner.nextInt();
	
		switch(menu) {
		
		case 1:
		List<EmployeeDTO> selectEmployeeList = employeeDAO.selectEmployeeList();
		System.out.println("이름\t\t 연락처 \t\t 회원번호");
		for(EmployeeDTO emp : selectEmployeeList) {
			System.out.println(emp.getName() +" \t "+ emp.getHpNumber() + "\t\t" + emp.getEmployeeId());	
		}	
			break;
		
		case 2:
		System.out.println("등록하기");
		System.out.print("이름: ");
		String name = scanner.next();
		System.out.print("연락처: ");
		String number = scanner.next();
		System.out.print("사원번호: ");
		int empId = scanner.nextInt();
		employeeDAO.insertEmployee(new EmployeeDTO(name, number, empId));
		break;
		
		case 3:
		System.out.println("삭제하기");
		System.out.print("삭제할 이름: ");
		String name1 = scanner.next();
		employeeDAO.deleteEmployee(name1);
		break;
		}
			
	}	
}
