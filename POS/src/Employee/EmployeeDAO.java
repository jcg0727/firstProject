package Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
		public List<EmployeeDTO> selectEmployeeList() throws Exception {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.35:1521/xe", "CAFE", "cafe");
			Statement statement = connection.createStatement();
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT EMPLOYEE_NAME,");
			builder.append("          HP_NUMBER,");
			builder.append("          EMPLOYEE_ID");
			builder.append(" FROM EMPLOYEE");
			List<EmployeeDTO> list = new ArrayList<EmployeeDTO>();
			ResultSet resultSet = statement.executeQuery(builder.toString());
			while (resultSet.next()) {
				String empName = resultSet.getString("EMPLOYEE_NAME");
				String empHpNumber = resultSet.getString("HP_NUMBER");
				int empId = resultSet.getInt("EMPLOYEE_ID");
				
				list.add(new EmployeeDTO(empName, empHpNumber, empId));
			}
			resultSet.close();
			statement.close();
			connection.close();
			
			return list;
		}
		
		public void insertEmployee(EmployeeDTO dto) throws Exception{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.35:1521:xe", "CAFE", "cafe");
			Statement statement = connection.createStatement();
			StringBuilder builder = new StringBuilder();
			builder.append("INSERT INTO EMPLOYEE ");
			builder.append(" (EMPLOYEE_NAME, HP_NUMBER, EMPLOYEE_ID) ");
			builder.append( "VALUES ");
			builder.append(" ('" + dto.getName() + "', '" + dto.getHpNumber() + "', '" + dto.getEmployeeId() + "')");
			int result = statement.executeUpdate(builder.toString()); 
			if (result>0) {
				System.out.println("등록 완료");			
			}else {
				System.out.println("등록 실패");
			}
			statement.close();
			connection.close();
		}
		
		public void deleteEmployee(String name) throws Exception {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.35:1521:xe", "CAFE", "cafe");
			String sql = "DELETE EMPLOYEE WHERE EMPLOYEE_NAME = ?";	
			PreparedStatement statement = connection.prepareStatement(sql);	
			statement.setString(1, name);
			//statement 실행
			int result = statement.executeUpdate();
			if (result > 0) {
				System.out.println("삭제 성공");
			}else {
				System.out.println("삭제 실패 원인 : 삭제할 아이디가 없습니다.");
			}
			statement.close();
			connection.close();

		}
	}
