package Goods;

import java.util.List;
import java.util.Scanner;

public class GoodsTest {
	
	public static void main(String[] args) throws Exception {
		
		
		System.out.println("상품관리");
		System.out.println("1.메뉴보기 2.등록 3.삭제 4.변경");
		System.out.print("선택해주세요 : ");
		Scanner scanner = new Scanner(System.in);
		GoodsDAOA goodsDAOA = new GoodsDAOA();
		int menu = scanner.nextInt();
		
		switch(menu) {
			case 1:
				List<GoodsDTOA> selectGoodsList = goodsDAOA.selectGoodsList();
				System.out.println("상품번호 상품이름 가격");
				for (GoodsDTOA goodsDTOA : selectGoodsList) {
					System.out.println(goodsDTOA.getId() + "\t" + goodsDTOA.getName() + "\t" + goodsDTOA.getPrice());
				}
				break;
		
			case 2:
		
				System.out.println("등록하기");
				System.out.print("상품번호: ");
				String id = scanner.next();
				System.out.print("상품이름: ");
				String name = scanner.next();
				System.out.print("가격: ");
				int price = scanner.nextInt();
				goodsDAOA.insertGoods(new GoodsDTOA(id, name, price));
				break;
		
			case 3:
				System.out.println("삭제하기");
				System.out.print("삭제할 상품번호: ");
				String code = scanner.next();
				goodsDAOA.deleteEmployee(code);
				break;
				
			case 4:
				System.out.println("수정하기");
				System.out.print("조회할 상품번호: ");
			    String goodsId = scanner.next();
		    	System.out.print("수정할 이름: ");
				String goodsName = scanner.next();
				System.out.print("수정할 가격: ");
		    	int goodsPrice = scanner.nextInt();
				goodsDAOA.updateGoods(new GoodsDTOA(goodsId, goodsName, goodsPrice));
				scanner.close();
		}
	}
}
