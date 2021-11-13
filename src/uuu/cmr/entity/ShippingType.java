package uuu.cmr.entity;

public enum ShippingType {
	SHOP("到店取貨", 0), HOME("送貨到府", 50), STORE("超商取貨", 65);
	
	private final String description;
	private final double fee;
	
	private ShippingType(String description, double fee) {
		this.description = description;
		this.fee = fee;
	}

	public String getDescription() {
		return description;
	}

	public double getFee() {
		return fee;
	}

}
