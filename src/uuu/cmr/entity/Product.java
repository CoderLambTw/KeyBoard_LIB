package uuu.cmr.entity;

public class Product extends Object{
	private int id; //Pkey, auto-increment
	private String name; //required
	private double unitPrice; //required, 定價及售價
	private int stock=10; //required
	private String photoUrl; //optional
	private String description="";
	
	public Product() {}

	
	public Product(int id, String name, double unitPrice) {
		this.id = id;
		this.name = name;
		this.unitPrice = unitPrice;
	}
	
	
	public Product(int id, String name, double unitPrice, int stock) {
		this.id = id;
		this.name = name;
		this.unitPrice = unitPrice;
		this.stock = stock;
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
	public double getUnitPrice() {//查詢訂價(售價)
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {//設定訂價(即售價)
		this.unitPrice = unitPrice;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}


	@Override
	public String toString() {
		return this.getClass().getName() +  "\nid=" + id 
				+ ", 名稱=" + name 
				+ ", 原售價=" + unitPrice 
				+ ", 庫存=" + stock 
				+ ",\n 圖片網址="+ photoUrl 
				+ "\n 產品描述=" + description;
	}


	@Override
	protected void finalize() throws Throwable {
		System.out.println("bye!");
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
