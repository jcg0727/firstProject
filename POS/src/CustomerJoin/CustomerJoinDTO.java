package CustomerJoin;

public class CustomerJoinDTO {
	private static int id = 0;
	   private String name;
	   private String hpNumber;
	   private int milieage = 0;
	   private String changeName;
	   private String changeHpNumber;
	   
	   
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

	   public int getMilieage() {
	      return milieage;
	   }

	   public void setMilieage(int milieage) {
	      this.milieage = milieage;
	   }

	   public CustomerJoinDTO(String name, String hpNumber) {
	      this.id++;
	      this.name = name;
	      this.hpNumber = hpNumber;
	      this.milieage = milieage;
	   }

	   public CustomerJoinDTO(String name, String hpNumber, int milieage) {
	      this.name = name;
	      this.hpNumber = hpNumber;
	      this.milieage = milieage;
	   }
	   public CustomerJoinDTO(int id ,String name, String hpNumber) {
	       this.id = this.id;
	      this.name = name;
	      this.hpNumber = hpNumber;
	   }

	   public String getChangeName() {
	      return changeName;
	   }

	   public void setChangeName(String changeName) {
	      this.changeName = changeName;
	   }

	   public String getChangeHpNumber() {
	      return changeHpNumber;
	   }

	   public void setChangeHpNumber(String changeHpNumber) {
	      this.changeHpNumber = changeHpNumber;
	   }

	   public CustomerJoinDTO(String changeName, String changeHpNumber,String name, String hpNumber ) {
	      this.name = name;
	      this.hpNumber = hpNumber;
	      this.changeName = changeName;
	      this.changeHpNumber = changeHpNumber;
	   }

	   
	   


	
	
}

	