package CustomerGoods;

	public class receiptDTO {

		private int id;
		   private String saledate;
		   private int price;
		   private String name;
		   private int quantity;
		   private String amount;
		   private String HpNumber;
		   private String payment;

		   public receiptDTO(int id, String saledate, int price, String name, int quantity, String amount, String hpNumber,
		         String payment) {
		      this.id = id;
		      this.saledate = saledate;
		      this.price = price;
		      this.name = name;
		      this.quantity = quantity;
		      this.amount = amount;
		      HpNumber = hpNumber;
		      this.payment = payment;
		   }

		   public String getSaledate() {
		      return saledate;
		   }

		   public void setSaledate(String saledate) {
		      this.saledate = saledate;
		   }

		   public String getPayment() {
		      return payment;
		   }

		   public void setPayment(String payment) {
		      this.payment = payment;
		   }

		   public String getAmount() {
		      return amount;
		   }

		   public void setAmount(String amount) {
		      this.amount = amount;
		   }

		   public int getId() {
		      return id;
		   }

		   public void setId(int id) {
		      this.id = id;
		   }

		   public int getPrice() {
		      return price;
		   }

		   public void setPrice(int price) {
		      this.price = price;
		   }

		   public String getName() {
		      return name;
		   }

		   public void setName(String name) {
		      this.name = name;
		   }

		   public int getQuantity() {
		      return quantity;
		   }

		   public void setQuantity(int quantity) {
		      this.quantity = quantity;
		   }

		   public String getHpNumber() {
		      return HpNumber;
		   }

		   public void setHpNumber(String hpNumber) {
		      HpNumber = hpNumber;
		   }

		   public receiptDTO(int price, String name, int quantity) {
		      this.price = price;
		      this.name = name;
		      this.quantity = quantity;
		   }

		   public receiptDTO(int price, String name, int quantity, String hpNumber) {
		      this.price = price;
		      this.name = name;
		      this.quantity = quantity;
		      HpNumber = hpNumber;
		   }

		   public receiptDTO(int price, String hpNumber) {
		      this.price = price;
		      HpNumber = hpNumber;
		   }

		   @Override
		   public String toString() {
		      return "receiptDTO [id=" + id + ", price=" + price + ", name=" + name + ", quantity=" + quantity + ", HpNumber="
		            + HpNumber + ", saledate=" + saledate + ", payment=" + payment + ", amount=" + amount + "]";
		   }

		}