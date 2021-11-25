package Main;

import org.omg.Messaging.SyncScopeHelper;

import CustomerJoin.CustomerJoinDAO;
import CustomerJoin.CustomerJoinDTO;
import Util.ScanUtil;
import Util.View;

public class CustomerInfoService {
	private CustomerInfoService() {}
	private static CustomerInfoService instance = new CustomerInfoService();
	public static CustomerInfoService getInstance() {
		return instance;
	}
	
	public int service() throws Exception {
		try {
			System.out.println("-------------------------------------------------------------------");
			System.out.println("1.회원정보 수정\t\t2.마일리지 확인\t\t3.회원 탈퇴\t\t4.뒤로가기");
			System.out.println("-------------------------------------------------------------------");
			System.out.print("메뉴를 선택해주세요: ");
			int input = ScanUtil.nextInt();
			switch(input) {
				case 1:
					System.out.println("수정하기");
					System.out.print("수정할 이름: ");
					String changeName = ScanUtil.nextLine();
					System.out.print("수정할 휴대전화번호: ");
					String changeHpNumber = ScanUtil.nextLine();
					System.out.print("기존 이름: ");
					String originalName =ScanUtil.nextLine();
					System.out.print("기존 휴대전화번호: ");
					String originalHpNumber = ScanUtil.nextLine();
					CustomerJoinDAO.updateCustomer(new CustomerJoinDTO(changeName, changeHpNumber, originalName, originalHpNumber));
					return View.CUSTOMERMENU;

				case 2:
					try {
						System.out.print("조회할 휴대폰 번호를 입력하세요: ");
						String customerHpNumber = ScanUtil.nextLine();
						CustomerJoinDTO dto = CustomerJoinDAO.selectMilieage(customerHpNumber);
						System.out.println("검색 결과");
						System.out.println("이름\t\t 휴대폰번호\t\t마일리지");
						System.out.print(dto.getName() + "\t");
						System.out.print(dto.getHpNumber() + "\t");
						System.out.print(dto.getMilieage() + "\n");
					}catch (Exception e) {
						System.err.println("회원이 아닙니다");
					}
					return View.CUSTOMERMENU;
				
				case 3:
			          System.out.print("삭제할 이름을 입력해주세요: ");
			          String id = ScanUtil.nextLine();
			          CustomerJoinDAO.deleteCustomer(id);
			          return View.CUSTOMERMENU;
			            
				 case 4:
					 return View.CUSTOMERMENU;
				
				 default :
					 System.err.println("잘못 입력하셨습니다.");
					 return View.CUSTOMERINFO;
			}
		} catch (Exception e) {
			System.err.println("잘못 입력하셨습니다.");
			return View.CUSTOMERINFO;
		}
	}
}
