package Inventory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO {
	
	public List<InventoryDTO> selectInventoryList() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.35:1521/xe", "CAFE", "cafe");
		Statement statement = connection.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT INVENTORY_ID,");
		builder.append("          INVENTORY_QUANTITY,");
		builder.append("          WAREHOUSING_DATE,");
		builder.append("          INVENTORY_PRICE,");
		builder.append("          INVENTORY_NAME,");
		builder.append("          GOODS_ID");
		builder.append(" FROM INVENTORY");
		List<InventoryDTO> list = new ArrayList<InventoryDTO>();
		ResultSet resultSet = statement.executeQuery(builder.toString());
		while (resultSet.next()) {
			String invenId = resultSet.getString("INVENTORY_ID");
			int invenName = resultSet.getInt("INVENTORY_QUANTITY");
			int invenPrice = resultSet.getInt("WAREHOUSING_DATE");
			int invenQuantity = resultSet.getInt("INVENTORY_PRICE");
			String invenDate = resultSet.getString("INVENTORY_NAME");
			String goodsName = resultSet.getString("GOODS_ID");
			
			list.add(new InventoryDTO(invenId, invenName,  invenPrice, invenQuantity, invenDate, goodsName));
		}
		resultSet.close();
		statement.close();
		connection.close();
		
		return list;	
	}
	
	public void insertInventory(InventoryDTO dto) throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.35:1521:xe", "CAFE", "cafe");
		Statement statement = connection.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO INVENTORY ");
		builder.append(" (INVENTORY_ID, INVENTORY_QUANTITY, WAREHOUSING_DATE, INVENTORY_PRICE, INVENTORY_NAME, GOODS_ID ) ");
		builder.append( "VALUES ");
		builder.append(" ('" + dto.getId() + "', '" + dto.getQuantity() + "', '" + dto.getDate() + "', '" + dto.getPrice()+ "', '"+ dto.getName() + "', '" + dto.getGoodsName() + "')");
		int result = statement.executeUpdate(builder.toString()); 
		if (result>0) {
			System.out.println("등록 완료");			
		}else {
			System.out.println("등록 실패");
		}
		statement.close();
		connection.close();
	}
	
	public void deleteEmployee(String id) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.35:1521:xe", "CAFE", "cafe");
		String sql = "DELETE INVENTORY WHERE INVENTORY_ID = ?";	
		PreparedStatement statement = connection.prepareStatement(sql);	
		statement.setString(1, id);
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
