package CustomerGoods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Goods.GoodsDTOA;

import Util.ScanUtil;
import Util.View;


public class CusotmerGoodsService {
	private CusotmerGoodsService() {}
	private static CusotmerGoodsService instance = new CusotmerGoodsService();
	public static CusotmerGoodsService getInstance() {
		return instance;
	}
	OrderDAO order = new OrderDAO();

   
	public int menu() throws Exception {
		try {
			Map<String, Integer> caffe = new HashMap<String, Integer>();
			caffe.put("에스프레소", 3500);
			caffe.put("아메리카노", 4100);
			caffe.put("카페라떼", 4600);
			caffe.put("카페모카", 4600);
			caffe.put("바닐라라떼", 4600);
			caffe.put("자몽에이드", 5000);
			caffe.put("청포도에이드", 5000);
			caffe.put("블루베리에이드", 5000);
			caffe.put("아이스티", 3000);
			caffe.put("얼그레이", 4000);
			caffe.put("페퍼민트", 4000);
			caffe.put("유자차", 4000);
			caffe.put("자몽차", 4000);
			caffe.put("딸기스무디", 5000);
			caffe.put("플레인스무디", 5000);
			caffe.put("청포도스무디", 5000);
			caffe.put("마카롱", 2500);
			caffe.put("브레드", 4000);
			caffe.put("베이글", 3000);
			caffe.put("조각케이크", 5000);
			caffe.put("쿠키", 2000);
			caffe.put("휘핑크림", 500);
			caffe.put("샷", 500);
			caffe.put("시럽", 500);
			
			List<GoodsDTO> selectGoodsList = GoodsDAO.selectGoodsList();
			System.out.println("이름\t\t\t\t가격");
			for (GoodsDTO goodsDTOA : selectGoodsList) {
				System.out.println(goodsDTOA.getName() + "\t\t" + goodsDTOA.getPrice());
			}
			System.out.println("주문하시겠습니까?");
			System.out.println("YES=1 , NO= 2 ");
			int input = ScanUtil.nextInt();
			 
			 switch(input) {
			 	case 1:
			 		
			 		System.out.println("주문하실 메뉴를 골라주세요. ");
			 		String menuChoice = ScanUtil.nextLine();
			 		System.out.println(menuChoice);
			 		System.out.println("수량을 선택해주세요.");
			 		int choice = ScanUtil.nextInt();
			 		try {
					System.out.println(menuChoice + '\t' + choice + "개" + '\t' + (caffe.get(menuChoice) * choice) + "원");
				} catch (Exception e) {
					System.err.println("메뉴 및 수량을 잘못입력하셨습니다. 다시 선택해 주세요.");
					return View.CUSTOMERGOODS;
				}
				int payment = caffe.get(menuChoice) * choice;
			 		System.out.println("추가주문 하시겠습니까?");
			 		System.out.println("예 : 1번 , 아니오 : 2번");
			 		int select = ScanUtil.nextInt();
			 		switch(select) {
			 		case 1:
			 		return View.CUSTOMERGOODS;
			 		default:
			 			break;
			 		}
			 		
			 		
			 		System.out.println("1.현금 결제 2. 카드 결제 3. 취소");
			 		int cash = ScanUtil.nextInt();	     		
			 		switch(cash) {
			 			case 1:
			 				System.out.print("현금을 넣어주세요: ");
			 				cash = ScanUtil.nextInt();
			 				if (cash - payment >= 0) {
			 					System.out.println(
			 					"현금: " + cash + "원"+'\t' + "결제금액: " + payment + "원"+'\t' + "거스름돈 : " + (cash - payment) + "원");
			 					System.out.println("적립하시겠습니까? 1.Y 2.N");
			 					int customer = ScanUtil.nextInt();
			 					if (customer == 1) {
			 						System.out.println("적립할 번호를 입력해주세요.");
			 						String accumulate = ScanUtil.nextLine();
			 						order.insertOrderMilieage(
			 						new receiptDTO(caffe.get(menuChoice), menuChoice, choice, accumulate));
			 						order.updateMilieage(new receiptDTO(payment, accumulate));
			 						order.selectReciept();
			 						List<receiptDTO> selectReciept = order.selectReciept();
			 	                    for (receiptDTO receiptDTO : selectReciept) {
			 	                    System.out.println("영수증번호" + '\t' + "날짜" + '\t' + '\t' + '\t'+ '\t'+ "가격" + '\t' + "메뉴"
			     	                             +'\t' + '\t' + "수량" + '\t' + "결제금액" + '\t' + "구매자" +'\t' + "결제방식");
			 	                       System.out.println("----------------------------------------------------------------------------------------------------------------");
			 	                       System.out.println(receiptDTO.getId() + "" + '\t' + '\t' + receiptDTO.getSaledate()
			 	                             + '\t' + receiptDTO.getPrice() + '\t' + receiptDTO.getName() + '\t'
			 	                             + receiptDTO.getQuantity() + '\t' + receiptDTO.getAmount() + '\t' + '\t'
			 	                             + receiptDTO.getHpNumber() + '\t' + receiptDTO.getPayment());
			 	                      System.out.println("----------------------------------------------------------------------------------------------------------------");
			 	                    }
			 						return View.CUSTOMERMENU;
			 					}else {
			 						System.out.println("적립하지 않습니다.");
			 						order.insertOrder(new receiptDTO(caffe.get(menuChoice), menuChoice, choice));
			 						order.selectReciept();
			 						List<receiptDTO> selectReciept = order.selectReciept();
			 	                    for (receiptDTO receiptDTO : selectReciept) {
			 	                       System.out.println("영수증번호" + '\t' + "날짜" + '\t'+ '\t'+ '\t' + '\t' + "가격" + '\t' + "메뉴"
			 	                             +'\t' +'\t' + "수량" + '\t' + "결제금액" + '\t' + "구매자" +'\t' + "결제방식");
			 	                       System.out.println("----------------------------------------------------------------------------------------------------------------");
			 	                       System.out.println(receiptDTO.getId() + "" + '\t' + '\t' + receiptDTO.getSaledate()
			 	                             + '\t' + receiptDTO.getPrice() + '\t' + receiptDTO.getName() + '\t'
			 	                             + receiptDTO.getQuantity() + '\t' + receiptDTO.getAmount() + '\t' + '\t'
			 	                             + receiptDTO.getHpNumber() + '\t' + receiptDTO.getPayment());
			 	                      System.out.println("----------------------------------------------------------------------------------------------------------------");
			 	                    }
			 					}
			 					   return View.CUSTOMERMENU;
			 				}else{
			 					System.out.println("현금이 부족합니다");
			 				}
			 				  return View.CUSTOMERMENU;
			 
			 			case 2:
			 				System.out.println("카드 결제를 진행합니다.");
			 				System.out.println("적립하시겠습니까? 1.Y 2.N");
			 				int customer = ScanUtil.nextInt();
			 				if (customer == 1) {
			 					System.out.println("적립할 번호를 입력해주세요.");
			 					String accumulate = ScanUtil.nextLine();
			 					order.insertOrderCardMilieage(
			 					new receiptDTO(caffe.get(menuChoice), menuChoice, choice, accumulate));
			 					order.updateMilieage(new receiptDTO(payment, accumulate));
			 					order.selectReciept();
			 					List<receiptDTO> selectReciept = order.selectReciept();
			 					 for (receiptDTO receiptDTO : selectReciept) {
			 	                       System.out.println("영수증번호" + '\t' + "날짜" + '\t'+ '\t'+ '\t' + '\t' + "가격" + '\t' + "메뉴"
			 	                             +'\t' +'\t' + "수량" + '\t' + "결제금액" + '\t' + "구매자" +'\t' + "결제방식");
			 	                       System.out.println("----------------------------------------------------------------------------------------------------------------");
			 	                       System.out.println(receiptDTO.getId() + "" + '\t' + '\t' + receiptDTO.getSaledate()
			 	                             + '\t' + receiptDTO.getPrice() + '\t' + receiptDTO.getName() + '\t'
			 	                             + receiptDTO.getQuantity() + '\t' + receiptDTO.getAmount() + '\t' + '\t'
			 	                             + receiptDTO.getHpNumber() + '\t' + receiptDTO.getPayment());
			 	                      System.out.println("----------------------------------------------------------------------------------------------------------------");
			 	                    }
			 					return View.CUSTOMERMENU;
			 				}else {
			 					System.out.println("적립하지 않습니다.");
			 					order.insertOrderCard(new receiptDTO(caffe.get(menuChoice), menuChoice, choice));
			 					order.selectReciept();
			 					List<receiptDTO> selectReciept = order.selectReciept();
			 					 for (receiptDTO receiptDTO : selectReciept) {
			 	                       System.out.println("영수증번호" + '\t' + "날짜" + '\t'+ '\t'+ '\t' + '\t' + "가격" + '\t' + "메뉴"
			 	                             +'\t' +'\t' + "수량" + '\t' + "결제금액" + '\t' + "구매자" +'\t' + "결제방식");
			 	                       System.out.println("----------------------------------------------------------------------------------------------------------------");
			 	                       System.out.println(receiptDTO.getId() + "" + '\t' + '\t' + receiptDTO.getSaledate()
			 	                             + '\t' + receiptDTO.getPrice() + '\t' + receiptDTO.getName() + '\t'
			 	                             + receiptDTO.getQuantity() + '\t' + receiptDTO.getAmount() + '\t' + '\t'
			 	                             + receiptDTO.getHpNumber() + '\t' + receiptDTO.getPayment());
			 	                      System.out.println("----------------------------------------------------------------------------------------------------------------");
			 	                    }
			 				}
			 				   return View.CUSTOMERMENU;
			         
			 			case 3:
			 				System.out.println("결제를 취소합니다.");
			 				   return View.CUSTOMERMENU;
			 		}

			 	case 2:
			 		System.out.println("감사합니다.");
			 		return View.CUSTOMERMENU;
			 }
			 return input;
		} catch (Exception e) {
			System.err.println("잘못 입력하셨습니다");
			return View.CUSTOMERGOODS;
		}
	}
}
		

