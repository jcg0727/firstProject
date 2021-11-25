package Sales;

import java.util.List;
import java.util.Scanner;

import CustomerGoods.receiptDTO;
import CustomerJoin.CustomerJoinDAO;
import CustomerJoin.CustomerJoinDTO;
import Employee.EmployeeDTO;
import Util.ScanUtil;
import Util.View;

public class SalesService {
	private SalesService() {}
	private static SalesService instance = new SalesService();
	public static SalesService getInstance() {
		return instance;
	}
	public int sales() throws Exception {
		try {
			SalesDAO dao = new SalesDAO();
			System.out.println("-----------------------------------------------------------------------------------");
			System.out.println("1.전체검색\t2.메뉴별\t3.날짜별\t4.회원별\t5.현금,카드별\t6.뒤로가기");
			System.out.println("-----------------------------------------------------------------------------------");
			System.out.print("선택해주세요: ");
			
			int menu = ScanUtil.nextInt();
					
			switch(menu) {
				case 1:
			         System.out.println();
			         System.out.println("전체 매출");
			         dao.selectAmount();
			         List<receiptDTO> selectAmount = dao.selectAmount();
			         System.out.println("영수증번호" + '\t' + '\t' + '\t'+ "날짜" + '\t' + "" + '\t' + "가격" + '\t' + "메뉴" + '\t' + '\t' + "수량"
			               + '\t' + "결제금액" + '\t' + "구매자" + '\t' + "결제방식" + '\t');
			         System.out.println(
			               "----------------------------------------------------------------------------------------------------------------");
			         for (receiptDTO receiptDTO : selectAmount) {
			            System.out.println(receiptDTO.getId() + "" + '\t' + '\t' + receiptDTO.getSaledate() + '\t'
			                  + receiptDTO.getPrice() + '\t' + receiptDTO.getName() + '\t' + receiptDTO.getQuantity() + '\t'
			                  + receiptDTO.getAmount() + '\t' + '\t' + receiptDTO.getHpNumber() + '\t'
			                  + receiptDTO.getPayment());
			         }
			         System.out.println(
				               "----------------------------------------------------------------------------------------------------------------");
					
					 System.out.print("총 매출: ");
			         receipt1DTO dto = dao.selectTotalSale();
			         System.out.println(dto.getSales());	
					 return View.ADMINMENU;
					
				case 2:
			         System.out.println("메뉴 입력");
			         String menuName = ScanUtil.nextLine();
			         receipt1DTO dto2 = dao.selectMenu(menuName);//메뉴별 매출검색
			         dao.selectMenuReciept(menuName); //영수증출력
			         List<receipt1DTO> selectMenuReciept = dao.selectMenuReciept(menuName);
			         System.out.println("영수증번호" + '\t' + '\t' + "날짜" + '\t' + '\t' + "" + '\t' + "가격" + '\t' + "메뉴" + '\t' + '\t' + "수량"
			               + '\t' + "결제금액" + '\t' + "구매자" + '\t' + "결제방식" + '\t');
			         System.out.println(
			               "----------------------------------------------------------------------------------------------------------------");
			         for (receipt1DTO receipt1DTO : selectMenuReciept) {
			            System.out.println(receipt1DTO.getId() + "" + '\t' + '\t' + receipt1DTO.getSaledate() + '\t'
			                  + receipt1DTO.getPrice() + '\t' + receipt1DTO.getName() + '\t' + receipt1DTO.getQuantity() + '\t'
			                  + receipt1DTO.getAmount() + '\t' + '\t' + receipt1DTO.getHpNumber() + '\t'
			                  + receipt1DTO.getPayment());
			         }
			         System.out.println(
			               "----------------------------------------------------------------------------------------------------------------");
			         System.out.println("메뉴명: " + menuName + "  매출: " + dto2.getSales());//매출 호출
			         return View.ADMINMENU;
			         
				case 3:
				try {
					System.out.println("(YYYYMMDD형식)");
			         System.out.println("첫번째 일정 입력");
			         String firstDate = ScanUtil.nextLine();
			         System.out.println("두번째 일정 입력(조회하고자 하는 날짜 하루 이후 입력)");
			         String secondDate = ScanUtil.nextLine();
			           receipt1DTO dto3 = dao.selectDate(firstDate, secondDate);
			           dao.selectDateReciept(firstDate, secondDate);
			         List<receipt1DTO> selectDateReciept = dao.selectDateReciept(firstDate, secondDate);
			         System.out.println("영수증번호" + '\t' + '\t' + "날짜" + '\t' + "" + '\t' + "가격" + '\t' + "메뉴" + '\t' + '\t' + "수량"
			               + '\t' + "결제금액" + '\t' + "구매자" + '\t' + "결제방식" + '\t');
			         System.out.println(
			               "----------------------------------------------------------------------------------------------------------------");
			         for (receipt1DTO receipt1DTO : selectDateReciept) {
			            System.out.println(receipt1DTO.getId() + "" + '\t' + '\t' + receipt1DTO.getSaledate() + '\t'
			                  + receipt1DTO.getPrice() + '\t' + receipt1DTO.getName() + '\t' + receipt1DTO.getQuantity() + '\t'
			                  + receipt1DTO.getAmount() + '\t' + '\t' + receipt1DTO.getHpNumber() + '\t'
			                  + receipt1DTO.getPayment());
			         }
			         System.out.println(
			               "----------------------------------------------------------------------------------------------------------------");
			         System.out.println("기간: "+ firstDate+"~" + secondDate +"총 매출: "+ dto3.getSales());
			         return View.ADMINMENU;
				} catch (Exception e) {
					System.err.println("잘못 입력하셨습니다.");
					return View.SALES;
				}
			         
				case 4:
			         System.out.println("휴대폰 번호 입력");
			         String hpNumber1 = ScanUtil.nextLine();
			           receipt1DTO dto4 = dao.selectCustomer(hpNumber1);
			         dao.selectHpNumberReciept(hpNumber1);
			         List<receipt1DTO> selectHpNumberReciept = dao.selectHpNumberReciept(hpNumber1);
			         System.out.println("영수증번호" + '\t' + '\t' + "날짜" + '\t' + "" + '\t' + "가격" + '\t' + "메뉴" + '\t' + '\t' + "수량"
			               + '\t' + "결제금액" + '\t' + "구매자" + '\t' + "결제방식" + '\t');
			         System.out.println(
			               "----------------------------------------------------------------------------------------------------------------");
			         for (receipt1DTO receipt1DTO : selectHpNumberReciept) {
			            System.out.println(receipt1DTO.getId() + "" + '\t' + '\t' + receipt1DTO.getSaledate() + '\t'
			                  + receipt1DTO.getPrice() + '\t' + receipt1DTO.getName() + '\t' + receipt1DTO.getQuantity() + '\t'
			                  + receipt1DTO.getAmount() + '\t' + '\t' + receipt1DTO.getHpNumber() + '\t'
			                  + receipt1DTO.getPayment());
			         }
			         System.out.println(
			               "----------------------------------------------------------------------------------------------------------------");
			         System.out.println(hpNumber1 +"총 매출: "+ dto4.getSales());
					
					return View.ADMINMENU;
					
				case 5:
					System.out.println("현금/카드 입력");
					String payment1 = ScanUtil.nextLine();
					receipt1DTO dto5 = dao.selectPayment(payment1);//메뉴별 매출검색
					dao.selectPaymentReciept(payment1); //영수증출력
					List<receipt1DTO> selectPaymentReciept = dao.selectPaymentReciept(payment1);
					System.out.println("영수증번호" + '\t' + '\t' + "날짜" + '\t' + "" + '\t' + "가격" + '\t' + "메뉴" + '\t' + '\t' + "수량"
							+ '\t' + "결제금액" + '\t' + "구매자" + '\t' + "결제방식" + '\t');
					System.out.println(
							"----------------------------------------------------------------------------------------------------------------");
					for (receipt1DTO receipt1DTO : selectPaymentReciept) {
						System.out.println(receipt1DTO.getId() + "" + '\t' + '\t' + receipt1DTO.getSaledate() + '\t'
								+ receipt1DTO.getPrice() + '\t' + receipt1DTO.getName() + '\t' + receipt1DTO.getQuantity() + '\t'
								+ receipt1DTO.getAmount() + '\t' + '\t' + receipt1DTO.getHpNumber() + '\t'
								+ receipt1DTO.getPayment());
					}
					System.out.println(
							"----------------------------------------------------------------------------------------------------------------");
					
					System.out.println(payment1 +"총 매출: "+ dto5.getSales());
					
					return View.ADMINMENU;
					
				case 6:
					return View.ADMINMENU;
					
				default:
					System.err.println("잘못 입력하셨습니다.");
					return View.SALES;
			}
		} catch (Exception e) {
			System.err.println("잘못 입력하셨습니다");
			return View.SALES;
		}
	}
}