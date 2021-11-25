package CustomerGoods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GoodsDAO {
	public static List<GoodsDTO> selectGoodsList() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.35:1521/xe", "CAFE", "cafe");
		Statement statement = connection.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT GOODS_NAME,");
		builder.append("          GOODS_PRICE");
		builder.append(" FROM GOODS");
		List<GoodsDTO> list = new ArrayList<GoodsDTO>();
		ResultSet resultSet = statement.executeQuery(builder.toString());
		while (resultSet.next()) {
			
			String goodsname = resultSet.getString("GOODS_NAME");
			int goodsprice = resultSet.getInt("GOODS_PRICE");
			
			list.add(new GoodsDTO(goodsname, goodsprice));
		}
		resultSet.close();
		statement.close();
		connection.close();
		
		return list;
	}
}
