package Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

	public List<CustomerDTO> selectCustomerList() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.35:1521/xe", "CAFE", "cafe");
		Statement statement = connection.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT MEMBER_ID,");
		builder.append("          MEMBER_NAME,");
		builder.append("          HP_NUMBER,");
		builder.append("          MILIEAGE");
		builder.append(" FROM CUSTOMER");
		List<CustomerDTO> list = new ArrayList<CustomerDTO>();
		ResultSet resultSet = statement.executeQuery(builder.toString());
		while (resultSet.next()) {
			int memId = resultSet.getInt("MEMBER_ID");
			String memName = resultSet.getString("MEMBER_NAME");
			String memHpNumber = resultSet.getString("HP_NUMBER");
			int mileage = resultSet.getInt("MILIEAGE");
			
			list.add(new CustomerDTO(memId, memName, memHpNumber, mileage));
		}
		resultSet.close();
		statement.close();
		connection.close();
		
		return list;
	}
	
	public void deleteCustomer(String name) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.35:1521:xe", "CAFE", "cafe");
		String sql = "DELETE CUSTOMER WHERE MEMBER_NAME = ?";	
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
