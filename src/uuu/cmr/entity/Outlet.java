package uuu.cmr.entity;

public class Outlet extends Product {
	private int discount; //無預設值，因每個商品折扣不一定
	
	public Outlet() {}
	
	public Outlet(int id, String name, double unitPrice, int stock) {
		super(id, name, unitPrice, stock);
	}
	
	public Outlet(int id, String name, double unitPrice) {
		super(id, name, unitPrice);
	}
	
	public Outlet(int id, String name, double unitPrice, int stock, int discount) {
		super(id, name, unitPrice);
	}	
	public int getDiscount() {
		return discount;
	}
	
	public void setDiscount(int discount) {
		if(discount >= 0 && discount<=100) {
			this.discount = discount;
		}else {
			System.out.println("VIP折扣不正確");
		}
	}
	
	public String getDiscountString() {
		int discount = 100-this.discount;
		if(discount%10==0) {
			discount = discount/10;
		}
				
		return discount+"折"; 
	}
	
	@Override  
	public double getUnitPrice() {//查詢售價
		return super.getUnitPrice() * (100-discount)/100;
	}
	
	public double getListPrice() {//查詢訂價
		return super.getUnitPrice();
	}

	@Override
	public String toString() {
		return super.toString() + " \n 產品折扣=" + discount 
				+ "% off, "
				+ "售價=" + getUnitPrice();
	}
	
	
}
