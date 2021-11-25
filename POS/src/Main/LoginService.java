package Main;

import Util.ScanUtil;
import Util.View;

public class LoginService {
	private LoginService() {}
	private static LoginService instance = new LoginService();
	public static LoginService getInstance() {
		return instance;
	}
	
	public int home() { 
		try {
			   System.out.println("___  ___                     _____  _                ______  _                    \r\n" + 
			   		"|  \\/  |                    /  ___|| |               | ___ \\| |                   \r\n" + 
			   		"| .  . |  ___   __ _   __ _ \\ `--. | |_   __ _  _ __ | |_/ /| |  __ _   ___   ___ \r\n" + 
			   		"| |\\/| | / _ \\ / _` | / _` | `--. \\| __| / _` || '__||  __/ | | / _` | / __| / _ \\\r\n" + 
			   		"| |  | ||  __/| (_| || (_| |/\\__/ /| |_ | (_| || |   | |    | || (_| || (__ |  __/\r\n" + 
			   		"\\_|  |_/ \\___| \\__, | \\__,_|\\____/  \\__| \\__,_||_|   \\_|    |_| \\__,_| \\___| \\___|\r\n" + 
			   		"                __/ |                                                             \r\n" + 
			   		"               |___/  ");
			System.out.println("────────────────────────────");
			  System.out.println("\t1.관리자 메뉴                         ");
			  System.out.println("\t2.손님 메뉴                           ");
			  System.out.println("\t3.시스템 종료                         ");
			  System.out.println("────────────────────────────" );
			System.out.println("번호를 입력해주세요 : " );
			int input = ScanUtil.nextInt();
			switch(input) {
				case 1:
					return View.ADMINLOGIN;
				case 2:
					return View.CUSTOMERMENU;
				case 3:
					System.err.println("시스템을 종료합니다");
					System.exit(0);
				default :
					System.err.println("잘못 입력하셨습니다");
					return View.HOME;
			}
		} catch (Exception e) {
			System.err.println("잘못 입력하셨습니다.");
			return View.HOME;
		}
	}
	
	
	public int admin() throws Exception {
		System.out.println("-----------------------------");
		System.out.print("아이디를 입력해 주세요 : ");
		String b = ScanUtil.nextLine();
		System.out.print("비밀번호를 입력해 주세요 : ");
		String c = ScanUtil.nextLine();
		System.out.println("-----------------------------");
			if(b.equals("1234") && c.equals("1234")) {
				return View.ADMINMENU;
			}else {
				System.err.println("아이디 또는 비밀번호를 잘못 입력 하였습니다.");
				return View.ADMINLOGIN;
			}
	}
}
