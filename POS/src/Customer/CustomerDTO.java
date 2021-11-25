package Customer;

public class CustomerDTO {
	private int id;
	private String name;
	private String hpNumber;
	private int mileage;
	
	public CustomerDTO(int id, String name, String hpNumber, int mileage) {
		super();
		this.id = id;
		this.name = name;
		this.hpNumber = hpNumber;
		this.mileage = mileage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	@Override
	public String toString() {
		return "CustomerDTO [id=" + id + ", name=" + name + ", hpNumber=" + hpNumber + ", mileage=" + mileage + "]";
	}
	
	
}
