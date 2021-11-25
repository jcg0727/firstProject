package CustomerJoin;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerJoinDAO {
	
	   public static void insertCustomer(CustomerJoinDTO dto) throws Exception {
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.35:1521:xe", "CAFE", "cafe");
	      Statement statement = (Statement) connection.createStatement();
	      StringBuilder builder = new StringBuilder();
	      builder.append("INSERT INTO CUSTOMER ");
	      builder.append(" (MEMBER_ID,MEMBER_NAME,HP_NUMBER,MILIEAGE) ");
	      builder.append(" VALUES ");
	      builder.append(" ((SELECT NVL(MAX(TO_NUMBER(MEMBER_ID)),0)+1 FROM CUSTOMER),'" + dto.getName() + "','"
	              + dto.getHpNumber() + "','" + dto.getMilieage() + "')");
	      int result = statement.executeUpdate(builder.toString());
	      if (result > 0) {
	         System.out.println("등록 완료");
	      } else {
	         System.out.println("등록 실패");
	      }
	      statement.close();
	      connection.close();
	   }
	   
	   public static void updateCustomer(CustomerJoinDTO dto) throws Exception {
		      Class.forName("oracle.jdbc.driver.OracleDriver");
		      Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.35:1521:xe", "CAFE", "cafe");
		      String sql = "update customer set member_name = ?, hp_number = ? where member_name = ? and hp_number = ?";
		      PreparedStatement statement = connection.prepareStatement(sql);
		      statement.setString(1, dto.getChangeName());
		      statement.setString(2, dto.getChangeHpNumber());
		      statement.setString(3, dto.getName());
		      statement.setString(4, dto.getHpNumber());
		      int result = statement.executeUpdate();
		      if (result > 0) {
		         System.out.println("변경 성공!");
		      } else {
		         System.out.println("변경 실패!");
		      }
		      statement.close();
		      connection.close();
		   }
	   
	   public static CustomerJoinDTO selectMilieage(String customerHpNumber) throws Exception {
		      Class.forName("oracle.jdbc.driver.OracleDriver");
		      Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.35:1521:xe", "CAFE", "cafe");
		      StringBuilder builder = new StringBuilder();
		      builder.append("SELECT member_id,member_name, hp_number,milieage from customer where hp_number = ?");
		      PreparedStatement statement = connection.prepareStatement(builder.toString());
		      statement.setString(1, customerHpNumber);
		      ResultSet resultSet = statement.executeQuery();
		      CustomerJoinDTO vo = null;
		      if (resultSet.next()) {
		         String id = resultSet.getString("member_id");
		         String name = resultSet.getString("member_name");
		         String hpNumber = resultSet.getString("hp_number");
		         int milieage = resultSet.getInt("milieage");
		         vo = new CustomerJoinDTO(name, hpNumber, milieage);
		      }
		      resultSet.close();
		      statement.close();
		      connection.close();

		      return vo;

		   }
	   
	   
	   public static void deleteCustomer(String id) throws Exception {
		      Class.forName("oracle.jdbc.driver.OracleDriver");
		      Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.35:1521:xe", "CAFE", "cafe");
		      String sql = "delete customer where member_name = ?";
		      PreparedStatement statement = connection.prepareStatement(sql);
		      statement.setString(1, id);
		      int result = statement.executeUpdate();
		      if (result > 0) {
		         System.out.println("삭제 성공!");
		      } else {
		         System.out.println("삭제 실패 원인: 삭제할 아이디가 없습니다.");
		      }
		      statement.close();
		      connection.close();

		   }
	   
	   
	   
	   
	   
	   
	   
}
