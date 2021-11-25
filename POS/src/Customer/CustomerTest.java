package Customer;

import java.util.List;

public class CustomerTest {

	public static void main(String[] args) throws Exception {
		CustomerDAO customerDAO = new CustomerDAO();
		List<CustomerDTO> selectCusotmerList = customerDAO.selectCustomerList();
		System.out.println("번호 \t 이름 \t\t 연락처 \t\t 마일리지");
		for(CustomerDTO mem : selectCusotmerList) {
			System.out.println(mem.getId() +" \t "+ mem.getName() + "\t" + mem.getHpNumber() + "\t" + mem.getMileage());	
		}	

	}

}

