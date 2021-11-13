package uuu.cmr.service;

import java.time.LocalDateTime;
import java.util.List;

import uuu.cmr.entity.Customer;
import uuu.cmr.entity.Order;
import uuu.cmr.entity.Outlet;
import uuu.cmr.entity.Product;
import uuu.cmr.entity.VGBException;
import uuu.cmr.entity.VIP;

public class OrderService {
	private OrdersDAO dao = new OrdersDAO();
	
	public void createOrder(Order order)throws VGBException{
		dao.insert(order);
	}
	
	public List<Order> getOrderHistory(String customerId) throws VGBException{
		return dao.selectOrderHistory(customerId);
	}
	
	//電子商務用不到，是OCPJP mod10多型的示範
	public double order(Customer c, Product p, int quantity) {
		double amount = p.getUnitPrice() * quantity;
		if(c instanceof VIP && !(p instanceof Outlet)) {
			//VIP vip = (VIP)c;
			//System.out.println(vip == c); //true
			//amount = amount * (100-vip.getDiscount())/100;
			amount = amount * (100-((VIP)c).getDiscount())/100;
		}
		
		return amount;
	}
	
	public Order getOrderById(String orderId) throws VGBException{
		return dao.selectOrderById(orderId);
	}
	public void updateStatusToPAID(int orderId, String customerId, String cardF6, String cardL4,

            String auth, String paymentDate, String amount) throws VGBException {

        StringBuilder paymentNote = new StringBuilder("信用卡號:");

        paymentNote.append(cardF6==null?"4311-95":cardF6).append("**-****").append(cardL4==null?2222:cardL4);

        paymentNote.append(",授權碼:").append(auth==null?"777777":auth);

        paymentNote.append(",交易時間:").append(paymentDate==null?LocalDateTime.now():paymentDate);

//        paymentNote.append(",刷卡金額:").append(amount);

        System.out.println("orderId = " + orderId);

        System.out.println("customerId = " + customerId);

        System.out.println("paymentNote = " + paymentNote);

        dao.updateStatusToPAID(orderId, customerId, paymentNote.toString());

    }
}
