package CustomerGoods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class OrderDAO {
	   // 현금결제 + 노적립
	   public void insertOrder(receiptDTO dto) throws Exception {
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.35:1521:xe", "CAFE", "cafe");
	      Statement statement = connection.createStatement();
	      StringBuilder builder = new StringBuilder();
	      builder.append("INSERT INTO RECEIPT ");
	      builder.append(" (RECEIPT_ID,SALEDATE,GOODS_PRICE,GOODS_NAME,QUANTITY,PAYMENT) ");
	      builder.append(" VALUES ");
	      builder.append(" ((SELECT NVL(MAX(TO_NUMBER(RECEIPT_ID)),0)+1 FROM RECEIPT) , SYSDATE ,'" + dto.getPrice() + "','"
	            + dto.getName() + "','" + dto.getQuantity() + "','" + "현금" + "')");
	      int result = statement.executeUpdate(builder.toString());
	      if (result > 0) {
	         System.out.println("결제 완료");
	      } else {
	         System.out.println("등록 실패");
	      }
	      statement.close();
	      connection.close();

	   }

	   // 카드 결제 +노적립
	   public void insertOrderCard(receiptDTO dto) throws Exception {
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.35:1521:xe", "CAFE", "cafe");
	      Statement statement = connection.createStatement();
	      StringBuilder builder = new StringBuilder();
	      builder.append("INSERT INTO RECEIPT ");
	      builder.append(" (RECEIPT_ID,SALEDATE,GOODS_PRICE,GOODS_NAME,QUANTITY,PAYMENT) ");
	      builder.append(" VALUES ");
	      builder.append(" ((SELECT NVL(MAX(TO_NUMBER(RECEIPT_ID)),0)+1 FROM RECEIPT), SYSDATE ,'" + dto.getPrice() + "','"
	            + dto.getName() + "','" + dto.getQuantity() + "','" + "카드" + "')");
	      int result = statement.executeUpdate(builder.toString());
	      if (result > 0) {
	         System.out.println("결제 완료");
	      } else {
	         System.out.println("등록 실패");
	      }
	      statement.close();
	      connection.close();

	   }

	   // 현금결제 +마일리지 적립
	   public void insertOrderMilieage(receiptDTO dto) throws Exception {
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.35:1521:xe", "CAFE", "cafe");
	      Statement statement = connection.createStatement();
	      StringBuilder builder = new StringBuilder();
	      builder.append("INSERT INTO RECEIPT ");
	      builder.append(" (RECEIPT_ID,SALEDATE,GOODS_PRICE,GOODS_NAME,QUANTITY,HP_NUMBER,PAYMENT) ");
	      builder.append(" VALUES ");
	      builder.append(" ((SELECT NVL(MAX(TO_NUMBER(RECEIPT_ID)),0)+1 FROM RECEIPT), SYSDATE ,'" + dto.getPrice() + "','"
	            + dto.getName() + "','" + dto.getQuantity() + "','" + dto.getHpNumber() + "','" + "현금" + "')");
	      int result = statement.executeUpdate(builder.toString());
	      if (result > 0) {
	         System.out.println("결제 완료");
	      } else {
	         System.out.println("등록 실패");
	      }
	      statement.close();
	      connection.close();

	   }

	   // 카드결제 + 마일리지 적립
	   public void insertOrderCardMilieage(receiptDTO dto) throws Exception {
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.35:1521:xe", "CAFE", "cafe");
	      Statement statement = connection.createStatement();
	      StringBuilder builder = new StringBuilder();
	      builder.append("INSERT INTO RECEIPT ");
	      builder.append(" (RECEIPT_ID,SALEDATE,GOODS_PRICE,GOODS_NAME,QUANTITY,HP_NUMBER,PAYMENT) ");
	      builder.append(" VALUES ");
	      builder.append(" ((SELECT NVL(MAX(TO_NUMBER(RECEIPT_ID)),0)+1 FROM RECEIPT), SYSDATE ,'" + dto.getPrice() + "','"
	            + dto.getName() + "','" + dto.getQuantity() + "','" + dto.getHpNumber() + "','" + "카드" + "')");
	      int result = statement.executeUpdate(builder.toString());
	      if (result > 0) {
	         System.out.println("결제 완료");
	      } else {
	         System.out.println("등록 실패");
	      }
	      statement.close();
	      connection.close();

	   }
	         //마일리지 적립용
	   public void updateMilieage(receiptDTO dto) throws Exception {
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.35:1521:xe", "CAFE", "cafe");
	      Statement statement = connection.createStatement();
	      StringBuilder builder = new StringBuilder();
	      builder.append("UPDATE CUSTOMER ");
	      builder.append(" SET MILIEAGE =(SELECT milieage+(" + dto.getPrice() * 0.05 + ")"
	            + "from customer where hp_number = " + dto.getHpNumber() + ")");
	      builder.append(" WHERE HP_NUMBER =" + dto.getHpNumber());
	      int result = statement.executeUpdate(builder.toString());

	      if (result > 0) {
	         System.out.println("적립 완료");
	      } else {
	         System.out.println("적립 실패");
	      }
	      statement.close();
	      connection.close();

	   }

	   // 영수증
	   public List<receiptDTO> selectReciept() throws Exception {
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.35:1521/xe", "CAFE", "cafe");
	      Statement statement = connection.createStatement();
	      StringBuilder builder = new StringBuilder();
	      builder.append(
	            "SELECT RECEIPT_ID, SALEDATE, GOODS_PRICE, GOODS_NAME,QUANTITY,GOODS_PRICE * QUANTITY AS AMOUNT,HP_NUMBER,PAYMENT ");
	      builder.append("FROM (SELECT* FROM RECEIPT ORDER BY RECEIPT_ID DESC)");
	      builder.append("WHERE ROWNUM= 1");
	      List<receiptDTO> list = new ArrayList<receiptDTO>();
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
	         list.add(new receiptDTO(id, saledate, price, name, quantity, amount, hpNumber, payment));
	      
	      }
	      resultSet.close();
	      statement.close();
	      connection.close();

	      return list;
	   }
}
