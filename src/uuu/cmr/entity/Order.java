package uuu.cmr.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
 
	private int id; //PKey, Auto-increment
	private Customer member; //required
	private LocalDate orderDate=LocalDate.now();	 //required
	private LocalTime orderTime=LocalTime.now();	 //required
	private int status=0; //0-新訂單,1-已通知, 2-已付款, 3-已出貨
	 
	private PaymentType paymentType;	//required
	private double paymentFee;
	private String paymentNote="";
	
	private ShippingType shippingType;  //required
	private double shippingFee;	 
	private String shippingNote="";
	 
	private String recipientName;	 //required
	private String recipientEmail;	 //required
	private String recipientPhone;	 //required
	private String shippingAddress;	 //required
	private double totalAmount; 
	 
	private List<OrderItem> orderItemsList = new ArrayList<>();
	
	//mutator(s)
	public void add(ShoppingCart cart) { //給Controller把購物車中的購物明細變成訂單中的訂單明細
		if(cart==null || cart.isEmpty()) {
			throw new IllegalArgumentException("把購物車中的購物明細變成訂單時購物車不得為null或必須有明細");
		}
		
		for(CartItem cartItem:cart.getCartItemSet()) {
			OrderItem orderItem = new OrderItem();
			
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setQuantity(cart.getQuantity(cartItem));
			orderItem.setPrice(cartItem.getProduct().getUnitPrice());
			
			orderItemsList.add(orderItem);
		}
	}
	
	public void add(OrderItem item) { //給DAO把訂單明細table中的每一筆訂單明細資料加入訂單
		orderItemsList.add(item);
	}
	
	//accessor
	public List<OrderItem> getOrderItemsList(){
		return new ArrayList<>(orderItemsList); //回傳原來list的複本
	}

	public int size() {
		return orderItemsList.size();
	}
	
	public int getTotalQuantity() {
		int sum = 0;
		for(OrderItem orderItem:orderItemsList) {
			sum+=orderItem.getQuantity();
		}		
		return sum;
	}
	
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;		
	}
	 
	public double getTotalAmount() {
		double sum = 0;
		if(orderItemsList==null || orderItemsList.isEmpty()){
			return this.totalAmount;
		}
		
		for(OrderItem orderItem:orderItemsList) {
			sum+=orderItem.getQuantity()*orderItem.getPrice();
		}
		
		return sum;
	}
	public double getTotalAmountWithFee() {

	       return getTotalAmount()+paymentFee+shippingFee;

	    }

	 
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getMember() {
		return member;
	}

	public void setMember(Customer member) {
		this.member = member;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalTime getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(LocalTime orderTime) {
		this.orderTime = orderTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public double getPaymentFee() {
		return paymentFee;
	}

	public void setPaymentFee(double paymentFee) {
		this.paymentFee = paymentFee;
	}

	public String getPaymentNote() {
		return paymentNote;
	}

	public void setPaymentNote(String paymentNote) {
		this.paymentNote = paymentNote;
	}

	public ShippingType getShippingType() {
		return shippingType;
	}

	public void setShippingType(ShippingType shippingType) {
		this.shippingType = shippingType;
	}

	public double getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(double shippingFee) {
		this.shippingFee = shippingFee;
	}

	public String getShippingNote() {
		return shippingNote;
	}

	public void setShippingNote(String shippingNote) {
		this.shippingNote = shippingNote;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getRecipientEmail() {
		return recipientEmail;
	}

	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}

	public String getRecipientPhone() {
		return recipientPhone;
	}

	public void setRecipientPhone(String recipientPhone) {
		this.recipientPhone = recipientPhone;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	@Override
	public String toString() {
		return "Order [編號=" + id + ", 訂購人=" + member + ", 訂購日期時間=" + orderDate + "T" + orderTime
				+ ", 狀態=" + status 
				+ ",\n 付款資訊=" + paymentType + ", 付款手續費=" + paymentFee + ", 付款紀錄=" + paymentNote 
				+ ",\n 貨運資訊=" + shippingType + ", 貨運手續費=" + shippingFee + ", 貨運紀錄=" + shippingNote 
				+ ",\n 收件人=" + recipientName + ", " + recipientEmail + "," + recipientPhone 
				+ ",\n 收件地址 " + shippingAddress
				+ ",\n 訂單明細=" + orderItemsList 
				+ ",\n 共" + size() + "項, " + getTotalQuantity() + "件, 總金額=" + getTotalAmount() + "]";
	}


	
}
 
