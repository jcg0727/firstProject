package Goods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class GoodsDAOA {
	public List<GoodsDTOA> selectGoodsList() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.35:1521/xe", "CAFE", "cafe");
		Statement statement = connection.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT GOODS_ID,");
		builder.append("          GOODS_NAME,");
		builder.append("          GOODS_PRICE");
		builder.append(" FROM GOODS");
		List<GoodsDTOA> list = new ArrayList<GoodsDTOA>();
		ResultSet resultSet = statement.executeQuery(builder.toString());
		while (resultSet.next()) {
			String goodsId = resultSet.getString("GOODS_ID");
			String goodsName = resultSet.getString("GOODS_NAME");
			int goodsprice = resultSet.getInt("GOODS_PRICE");
			
			list.add(new GoodsDTOA(goodsId, goodsName, goodsprice));
		}
		resultSet.close();
		statement.close();
		connection.close();
		
		return list;
	
	}
		public void insertGoods(GoodsDTOA dtoa) throws Exception{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.35:1521:xe", "CAFE", "cafe");
			Statement statement = connection.createStatement();
			StringBuilder builder = new StringBuilder();
			builder.append("INSERT INTO GOODS ");
			builder.append(" (GOODS_ID, GOODS_NAME, GOODS_PRICE) ");
			builder.append( "VALUES ");
			builder.append(" ('" + dtoa.getId() + "', '" + dtoa.getName() + "', '" + dtoa.getPrice() + "')");
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
			String sql = "DELETE GOODS WHERE GOODS_ID = ?";	
			PreparedStatement statement = connection.prepareStatement(sql);	
			statement.setString(1, id);
			int result = statement.executeUpdate();
			if (result > 0) {
				System.out.println("삭제 성공");
			}else {
				System.out.println("삭제 실패 원인 : 삭제할 아이디가 없습니다.");
			}
			statement.close();
			connection.close();
		}
		
		public void updateGoods(GoodsDTOA dtoa) throws Exception {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.35:1521:xe", "CAFE", "cafe");
			String sql = "UPDATE GOODS SET GOODS_NAME = ?, GOODS_PRICE = ? WHERE GOODS_ID = ?";	
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, dtoa.getName()); 
			statement.setInt(2, dtoa.getPrice());
			statement.setString(3, dtoa.getId()); 
			statement.executeUpdate();
			int result = statement.executeUpdate();
			if (result > 0) {
				System.out.println("변경 성공");
			}else {
				System.out.println("변경 실패");
			}
			statement.close();
			connection.close();
		}
		
}