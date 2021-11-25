package Employee;

public class EmployeeDTO {
	private String name;
	private String hpNumber;
	private int employeeId;
	
	public EmployeeDTO(String name, String hpNumber, int eployeeId) {
		super();
		this.name = name;
		this.hpNumber = hpNumber;
		this.employeeId = eployeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHpNumber() {
		return hpNumber;
	}
	public void setHpNumber(String hpNumber) {
		this.hpNumber = hpNumber;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEployeeId(int eployeeId) {
		this.employeeId = employeeId;
	}
	@Override
	public String toString() {
		return "EmployeeDTO [name=" + name + ", hpNumber=" + hpNumber + ", eployeeId=" + employeeId + "]";
	}
	
	
}
