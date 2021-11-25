package Goods;

import java.util.Scanner;

import Util.ScanUtil;
import Util.View;

import java.util.List;

public class GoodsService {

	private static GoodsService instance;
	public static GoodsService getInstance() {
		if(instance==null) {
			instance = new GoodsService();
		}
		return instance;
	}

	public static int goods() throws Exception {
		try {
			GoodsDAOA goodsDAOA = new GoodsDAOA();
			System.out.println("-----------------------------------------------------------------");
			System.out.println("1.메뉴보기 \t 2.등록 \t 3.삭제 \t 4.변경 \t 5.뒤로가기");
			System.out.println("-----------------------------------------------------------------");
			System.out.print("선택해주세요 : ");
			 int menu = ScanUtil.nextInt();
			switch(menu) {
				case 1:
					List<GoodsDTOA> selectGoodsList = goodsDAOA.selectGoodsList();
					System.out.println("상품번호 상품이름 가격");
					for (GoodsDTOA goodsDTOA : selectGoodsList) {
						System.out.println(goodsDTOA.getId() + "\t" + goodsDTOA.getName() + "\t" + goodsDTOA.getPrice());
					}
					return View.ADMINMENU;
			
				case 2:
					System.out.println("등록하기");
					System.out.print("상품번호: ");
					String id = ScanUtil.nextLine();
					System.out.print("상품이름: ");
					String name = ScanUtil.nextLine();
					System.out.print("가격: ");
					int price = ScanUtil.nextInt();
					goodsDAOA.insertGoods(new GoodsDTOA(id, name, price));
					return View.ADMINMENU;

				case 3:
					System.out.println("삭제하기");
					System.out.print("삭제할 상품번호: ");
					String code = ScanUtil.nextLine();
					goodsDAOA.deleteEmployee(code);
					return View.ADMINMENU;
				
				case 4:
					System.out.println("수정하기");
					System.out.print("조회할 상품번호: ");
					String goodsId = ScanUtil.nextLine();
					System.out.print("수정할 이름: ");
					String goodsName = ScanUtil.nextLine();
					System.out.print("수정할 가격: ");
					int goodsPrice = ScanUtil.nextInt();
					goodsDAOA.updateGoods(new GoodsDTOA(goodsId, goodsName, goodsPrice));
					
				case 5:
					return View.ADMINMENU;
					
				default:
					System.err.println("잘못 입력하셨습니다.");
					return View.GOODS;
			}
		} catch (Exception e) {
			System.err.println("잘못 입력하셨습니다");
			return View.GOODS;
		}
	}
}
