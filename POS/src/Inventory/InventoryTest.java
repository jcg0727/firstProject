package Inventory;

import java.util.List;
import java.util.Scanner;

public class InventoryTest {

	public static void main(String[] args) throws Exception {
		System.out.println("재고관리");
		System.out.println("1.검색 2.등록 3.삭제");
		System.out.print("선택해주세요: ");
		Scanner scanner = new Scanner(System.in);
		InventoryDAO inventoryDAO = new InventoryDAO();
		int menu = scanner.nextInt();
		
		switch(menu) {
		
			case 1:
				List<InventoryDTO> list = inventoryDAO.selectInventoryList();
				System.out.println("재고번호 재고수량 재고입고일 재고가격 재고이름 관련상품이름");
				for (InventoryDTO inventoryDTO : list) {
					System.out.println(inventoryDTO.getId() + "\t" + inventoryDTO.getQuantity() +"\t" + inventoryDTO.getDate() +"\t" + inventoryDTO.getPrice() +"\t" + inventoryDTO.getName() + "\t" + inventoryDTO.getGoodsName() );
				}
				break;
				
			case 2:
				System.out.println("등록하기");
				System.out.print("재고번호: ");
				String id = scanner.next();
				System.out.print("재고수량: ");
				int number = scanner.nextInt();
				System.out.print("입고일자: ");
				int date = scanner.nextInt();
				System.out.print("재고단가: ");
				int price = scanner.nextInt();
				System.out.print("재고이름: ");
				String name = scanner.next();
				System.out.print("제품이름: ");
				String goodsName = scanner.next();
				inventoryDAO.insertInventory(new InventoryDTO(id, number, date, price, name, goodsName));
				break;
		
			case 3:
				System.out.println("삭제하기");
				System.out.print("삭제할 재고번호: ");
				String InventoryId = scanner.next();
				inventoryDAO.deleteEmployee(InventoryId);
				break;		
		}
	}
}
