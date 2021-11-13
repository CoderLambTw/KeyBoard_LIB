package uuu.cmr.entity;

public class VIP extends Customer{
	private int discount=10; //折扣10%(10%off)
	
	public VIP() {
		
	}
	
	public VIP(String id, String password, String name, char gender, String email) {
		super(id, password, name, gender, email);
	
	}

	public VIP(String id, String password, String name) {
		super(id, password, name);
		
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
	public String toString() {
		return super.toString() + ",折扣=" + discount;
	}
	
}
