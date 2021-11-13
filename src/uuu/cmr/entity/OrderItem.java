package uuu.cmr.entity;

import uuu.cmr.entity.Product;

public class OrderItem {
 
	private int orderId;
	 
	private double price;
	 
	private int quantity;
	 
	private Product product;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "訂單明細 [編號=" + orderId  + ", 交易價=" + price + ", 數量=" + quantity
				+ ", 購買產品=" + product + "]";
	}	 
	 
}
 
