package Sales;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import CustomerGoods.receiptDTO;

public class SalesDAO {
	
	// 전체 영수증 추력	 
	 public List<receiptDTO> selectAmount() throws Exception {
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.35:1521/xe", "CAFE", "cafe");
	      Statement statement = connection.createStatement();
	      StringBuilder builder = new StringBuilder();
	      builder.append(
	            "SELECT RECEIPT_ID, SALEDATE, GOODS_PRICE, GOODS_NAME,QUANTITY,GOODS_PRICE * QUANTITY AS AMOUNT,HP_NUMBER,PAYMENT ");
	      builder.append("FROM receipt ORDER BY 2");
	      List<receiptDTO> listAmount = new ArrayList<receiptDTO>();
	      ResultSet resultSet = statement.executeQuery(builder.toString());
	      while (resultSet.next()) {
	         int id = resultSet.getInt("RECEIPT_ID");
	         String saledate = resultSet.getString("SALEDATE");
	         int price = resultSet.getInt("GOODS_PRICE");
	         String name = resultSet.getString("GOODS_NAME");
	         int quantity = resultSet.getInt("QUANTITY");
	         String amount = resultSet.getString("AMOUNT");
	         String hpNumber = resultSet.getString("HP_NUMBER");
	         String payment = resultSet.getString("PAYMENT");
	         listAmount.add(new receiptDTO(id, saledate, price, name, quantity, amount, hpNumber, payment));  
	      }
	      resultSet.close();
	      statement.close();
	      connection.close();
	      return listAmount;
	   }
	
		 //총매출계산
	 public receipt1DTO selectTotalSale() throws Exception {
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.35:1521:xe", "CAFE", "cafe");
	      Statement statement = connection.createStatement();
	      StringBuilder builder = new StringBuilder();
	      builder.append(" SELECT SUM(GOODS_PRICE*QUANTITY) AS sales FROM RECEIPT");
	      ResultSet resultSet = statement.executeQuery(builder.toString());
	      receipt1DTO vo = null;
	      if (resultSet.next()) {
	         String sales = resultSet.getString("SALES");
	         vo = new receipt1DTO(sales);
	      }
	      resultSet.close();
	      statement.close();
	      connection.close();

	      return vo;
	   }
	 
	 	//메뉴 검색 합계금액
	 	public receipt1DTO selectMenu(String menuName) throws Exception {
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.35:1521:xe", "CAFE", "cafe");
	      StringBuilder builder = new StringBuilder();
	      builder.append("SELECT SUM(GOODS_PRICE * QUANTITY) AS SALES FROM RECEIPT WHERE GOODS_NAME like ?");
	      PreparedStatement statement = connection.prepareStatement(builder.toString());
	      statement.setString(1, menuName);
	      ResultSet resultSet = statement.executeQuery();
	      receipt1DTO vo = null;
	      if (resultSet.next()) {
	         String sales = resultSet.getString("SALES");
	         vo = new receipt1DTO(sales);
	      }
	      resultSet.close();
	      statement.close();
	      connection.close();

	      return vo;
	   }
	 	
	 	//메뉴 검색 영수증 출력
	 	 public List<receipt1DTO> selectMenuReciept(String menuName) throws Exception {
	 	      Class.forName("oracle.jdbc.OracleDriver"); // 드라이버이름
	 	      Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.35:1521/xe", "cafe", "cafe"); // 접속
	 	      String sql = "SELECT RECEIPT_ID, SALEDATE, GOODS_PRICE, GOODS_NAME,QUANTITY,GOODS_PRICE * QUANTITY AS AMOUNT,HP_NUMBER,PAYMENT FROM RECEIPT  WHERE GOODS_NAME LIKE ? ORDER BY 2"; // 쿼리문
	 	      List<receipt1DTO> list = new ArrayList<receipt1DTO>(); // Bookprod 테이블에 대한 리스트를 뽑겠다.
	 	      PreparedStatement statement = connection.prepareStatement(sql);
	 	      statement.setString(1, menuName); // 첫번째 ?에 들어갈것!
	 	      ResultSet resultSet = statement.executeQuery(); // 쿼리문을 실행결과를 resulset 저장
	 	      while (resultSet.next()) { // resultset 있는 경우 while문을 탄다
	 	         int id = resultSet.getInt("RECEIPT_ID");
	 	         String saledate = resultSet.getString("SALEDATE");
	 	         int price = resultSet.getInt("GOODS_PRICE");
	 	         String name = resultSet.getString("GOODS_NAME");
	 	         int quantity = resultSet.getInt("QUANTITY");
	 	         String amount = resultSet.getString("AMOUNT");
	 	         String hpNumber = resultSet.getString("HP_NUMBER");
	 	         String payment = resultSet.getString("PAYMENT");
	 	         list.add(new receipt1DTO(id, saledate, price, name, quantity, amount, hpNumber, payment));
	 	      }
	 	      resultSet.close(); // 쿼리 끝났으니 닫아라~
	 	      statement.close();
	 	      connection.close();

	 	      return list; // 기본은null되어있는데 null되어있으면 아무리검색해도 안나옴~
	 	   }

	 	 	//현금,카드별 합계금액
	 	   public receipt1DTO selectPayment(String payment) throws Exception {
	 	      Class.forName("oracle.jdbc.driver.OracleDriver");
	 	      Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.35:1521:xe", "cafe", "cafe");
	 	      StringBuilder builder = new StringBuilder();
	 	      builder.append("SELECT SUM(GOODS_PRICE * QUANTITY) AS SALES FROM RECEIPT WHERE PAYMENT like ?");
	 	      PreparedStatement statement = connection.prepareStatement(builder.toString());
	 	      statement.setString(1, payment);
	 	      ResultSet resultSet = statement.executeQuery();
	 	      receipt1DTO vo = null;
	 	      if (resultSet.next()) {
	 	         String sales = resultSet.getString("SALES");
	 	         vo = new receipt1DTO(sales);
	 	      }
	 	      resultSet.close();
	 	      statement.close();
	 	      connection.close();

	 	      return vo;
	 	   }
	 	   //현금,카드 별 영수증
	 	   public List<receipt1DTO> selectPaymentReciept(String payment1) throws Exception {
	 	      Class.forName("oracle.jdbc.OracleDriver"); // 드라이버이름
	 	      Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.35:1521/xe", "cafe", "cafe"); // 접속
	 	      String sql = "SELECT RECEIPT_ID, SALEDATE, GOODS_PRICE, GOODS_NAME,QUANTITY,GOODS_PRICE * QUANTITY AS AMOUNT,HP_NUMBER,PAYMENT FROM RECEIPT  WHERE PAYMENT LIKE ? ORDER BY 2"; // 쿼리문
	 	      List<receipt1DTO> list = new ArrayList<receipt1DTO>(); // Bookprod 테이블에 대한 리스트를 뽑겠다.
	 	      PreparedStatement statement = connection.prepareStatement(sql);
	 	      statement.setString(1, payment1); // 첫번째 ?에 들어갈것!
	 	      ResultSet resultSet = statement.executeQuery(); // 쿼리문을 실행결과를 resulset 저장
	 	      while (resultSet.next()) { // resultset 있는 경우 while문을 탄다
	 	         int id = resultSet.getInt("RECEIPT_ID");
	 	         String saledate = resultSet.getString("SALEDATE");
	 	         int price = resultSet.getInt("GOODS_PRICE");
	 	         String name = resultSet.getString("GOODS_NAME");
	 	         int quantity = resultSet.getInt("QUANTITY");
	 	         String amount = resultSet.getString("AMOUNT");
	 	         String hpNumber = resultSet.getString("HP_NUMBER");
	 	         String payment = resultSet.getString("PAYMENT");
	 	         list.add(new receipt1DTO(id, saledate, price, name, quantity, amount, hpNumber, payment));
	 	      }
	 	      resultSet.close(); // 쿼리 끝났으니 닫아라~
	 	      statement.close();
	 	      connection.close();

	 	      return list; // 기본은null되어있는데 null되어있으면 아무리검색해도 안나옴~
	 	   }
	 	 
	 	   //고객검색 합계금액
	 	   public receipt1DTO selectCustomer(String hpNumber1) throws Exception {
	 	      Class.forName("oracle.jdbc.driver.OracleDriver");
	 	      Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.35:1521:xe", "cafe", "cafe");
	 	      StringBuilder builder = new StringBuilder();
	 	      builder.append("SELECT SUM(GOODS_PRICE * QUANTITY) AS SALES FROM RECEIPT WHERE HP_NUMBER like ?");
	 	      PreparedStatement statement = connection.prepareStatement(builder.toString());
	 	      statement.setString(1, hpNumber1);
	 	      ResultSet resultSet = statement.executeQuery();
	 	      receipt1DTO vo = null;
	 	      if (resultSet.next()) {
	 	         String sales = resultSet.getString("SALES");
	 	         vo = new receipt1DTO(sales);
	 	      }
	 	      resultSet.close();
	 	      statement.close();
	 	      connection.close();

	 	      return vo;
	 	   }
	 	   //고객 검색 영수증
	 	   public List<receipt1DTO> selectHpNumberReciept(String hpNumber1) throws Exception {
	 	      Class.forName("oracle.jdbc.OracleDriver"); // 드라이버이름
	 	      Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.35:1521/xe", "cafe", "cafe"); // 접속
	 	      String sql = "SELECT RECEIPT_ID, SALEDATE, GOODS_PRICE, GOODS_NAME,QUANTITY,GOODS_PRICE * QUANTITY AS AMOUNT,HP_NUMBER,PAYMENT FROM RECEIPT  WHERE HP_NUMBER LIKE ? ORDER BY 2"; // 쿼리문
	 	      List<receipt1DTO> list = new ArrayList<receipt1DTO>(); // Bookprod 테이블에 대한 리스트를 뽑겠다.
	 	      PreparedStatement statement = connection.prepareStatement(sql);
	 	      statement.setString(1, hpNumber1); // 첫번째 ?에 들어갈것!
	 	      ResultSet resultSet = statement.executeQuery(); // 쿼리문을 실행결과를 resulset 저장
	 	      while (resultSet.next()) { // resultset 있는 경우 while문을 탄다
	 	         int id = resultSet.getInt("RECEIPT_ID");
	 	         String saledate = resultSet.getString("SALEDATE");
	 	         int price = resultSet.getInt("GOODS_PRICE");
	 	         String name = resultSet.getString("GOODS_NAME");
	 	         int quantity = resultSet.getInt("QUANTITY");
	 	         String amount = resultSet.getString("AMOUNT");
	 	         String hpNumber = resultSet.getString("HP_NUMBER");
	 	         String payment = resultSet.getString("PAYMENT");
	 	         list.add(new receipt1DTO(id, saledate, price, name, quantity, amount, hpNumber, payment));
	 	      }
	 	      resultSet.close(); // 쿼리 끝났으니 닫아라~
	 	      statement.close();
	 	      connection.close();

	 	      return list; // 기본은null되어있는데 null되어있으면 아무리검색해도 안나옴~
	 	   }
	 	   
	 	   public receipt1DTO selectDate(String firstDate,String secondDate) throws Exception {
	 	      Class.forName("oracle.jdbc.driver.OracleDriver");
	 	      Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.35:1521:xe", "cafe", "cafe");
	 	      StringBuilder builder = new StringBuilder();
	 	      builder.append(" SELECT SUM(GOODS_PRICE * QUANTITY) AS SALES FROM RECEIPT WHERE SALEDATE BETWEEN ? AND ?");
	 	      PreparedStatement statement = connection.prepareStatement(builder.toString());
	 	      statement.setString(1, firstDate);
	 	      statement.setString(2, secondDate);
	 	      ResultSet resultSet = statement.executeQuery();
	 	      receipt1DTO vo = null;
	 	      if (resultSet.next()) {
	 	         String sales = resultSet.getString("SALES");
	 	         vo = new receipt1DTO(sales);
	 	      }
	 	      resultSet.close();
	 	      statement.close();
	 	      connection.close();

	 	      return vo;
	 	   }
	 	   
	 	   public List<receipt1DTO> selectDateReciept(String firstDate,String secondDate) throws Exception {
	 	      Class.forName("oracle.jdbc.OracleDriver"); // 드라이버이름
	 	      Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.35:1521/xe", "cafe", "cafe"); // 접속
	 	      String sql = "SELECT RECEIPT_ID, SALEDATE, GOODS_PRICE, GOODS_NAME,QUANTITY,GOODS_PRICE * QUANTITY AS AMOUNT,HP_NUMBER,PAYMENT FROM RECEIPT  WHERE SALEDATE BETWEEN ? AND ? ORDER BY 2"; // 쿼리문
	 	      List<receipt1DTO> list = new ArrayList<receipt1DTO>(); // Bookprod 테이블에 대한 리스트를 뽑겠다.
	 	      PreparedStatement statement = connection.prepareStatement(sql);
	 	      statement.setString(1, firstDate); // 첫번째 ?에 들어갈것!
	 	      statement.setString(2, secondDate); // 첫번째 ?에 들어갈것!
	 	      ResultSet resultSet = statement.executeQuery(); // 쿼리문을 실행결과를 resulset 저장
	 	      while (resultSet.next()) { // resultset 있는 경우 while문을 탄다
	 	         int id = resultSet.getInt("RECEIPT_ID");
	 	         String saledate = resultSet.getString("SALEDATE");
	 	         int price = resultSet.getInt("GOODS_PRICE");
	 	         String name = resultSet.getString("GOODS_NAME");
	 	         int quantity = resultSet.getInt("QUANTITY");
	 	         String amount = resultSet.getString("AMOUNT");
	 	         String hpNumber = resultSet.getString("HP_NUMBER");
	 	         String payment = resultSet.getString("PAYMENT");
	 	         list.add(new receipt1DTO(id, saledate, price, name, quantity, amount, hpNumber, payment));
	 	      }
	 	      resultSet.close(); // 쿼리 끝났으니 닫아라~
	 	      statement.close();
	 	      connection.close();

	 	      return list; // 기본은null되어있는데 null되어있으면 아무리검색해도 안나옴~
	 	   }
}