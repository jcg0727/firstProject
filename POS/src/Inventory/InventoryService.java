package Inventory;

import java.util.List;

import Util.ScanUtil;
import Util.View;

public class InventoryService {
	private InventoryService() {}
	private static InventoryService instance = new InventoryService();
	public static InventoryService getInstance() {
		return instance;
	}

	public static int inventory() throws Exception {
		try {
			InventoryDAO inventoryDAO = new InventoryDAO();
			System.out.println("------------------------------------------------" );
			System.out.println("1.검색\t\t2.등록\t\t3.삭제\t\t4.뒤로가기");
			System.out.println("------------------------------------------------" );
			System.out.print("선택해주세요: ");
			int menu = ScanUtil.nextInt();
			switch(menu) {
				case 1:
					List<InventoryDTO> list = inventoryDAO.selectInventoryList();
					System.out.println("재고번호\t재고수량\t재고입고일\t재고가격\t재고이름\t관련상품번호");
					for (InventoryDTO inventoryDTO : list) {
						System.out.println(inventoryDTO.getId() + "\t" + inventoryDTO.getQuantity() +"\t\t" + inventoryDTO.getDate() +"\t" + inventoryDTO.getPrice() +"\t\t" + inventoryDTO.getName() + "\t\t" + inventoryDTO.getGoodsName() );
					}
					return View.ADMINMENU;
			
				case 2:
					System.out.println("등록하기");
					System.out.print("재고번호: ");
					String id = ScanUtil.nextLine();
					System.out.print("재고수량: ");
					int number = ScanUtil.nextInt();
					System.out.print("입고일자: ");
					int date = ScanUtil.nextInt();
					System.out.print("재고단가: ");
					int price = ScanUtil.nextInt();
					System.out.print("재고이름: ");
					String name = ScanUtil.nextLine();
					System.out.print("제품이름: ");
					String goodsName = ScanUtil.nextLine();
					inventoryDAO.insertInventory(new InventoryDTO(id, number, date, price, name, goodsName));
					return View.ADMINMENU;
					
				 case 3:
					System.out.println("삭제하기");
					System.out.print("삭제할 재고번호: ");
					String InventoryId = ScanUtil.nextLine();
					inventoryDAO.deleteEmployee(InventoryId);
					return View.ADMINMENU;
					
				 case 4:
					 return View.ADMINMENU;
					 
				default :
					System.err.println("잘못 입력하셨습니다.");
					return View.INVENTORY;
				}
		} catch (Exception e) {
			System.err.println("잘못 입력하셨습니다");
			return View.INVENTORY;
		}
	}
}
