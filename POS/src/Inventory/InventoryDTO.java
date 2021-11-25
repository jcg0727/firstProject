package Inventory;

public class InventoryDTO {
	private String id;
	private int quantity;
	private int date;
	private int price;
	private String name;
	private String goodsName;
	public InventoryDTO(String id, int quantity, int date, int price, String name, String goodsName) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.date = date;
		this.price = price;
		this.name = name;
		this.goodsName = goodsName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
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
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	@Override
	public String toString() {
		return "InventoryDTO [id=" + id + ", quantity=" + quantity + ", date=" + date + ", price=" + price + ", name="
				+ name + ", goodsName=" + goodsName + "]";
	}
	
	
}