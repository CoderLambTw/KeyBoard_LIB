package uuu.cmr.service;

import uuu.cmr.entity.Customer;
import uuu.cmr.entity.VGBException;

public class CustomerService {
	private CustomersDAO dao = new CustomersDAO();
	
	public Customer login(String id, String pwd) 
		throws VGBException{
		if(id==null || id.length()==0 || pwd==null || pwd.length()==0) {
			throw new IllegalArgumentException("會員登入必須輸入帳號密碼");
		}
		Customer c = null;
		c = dao.selectCustomerById(id);
		if(c!=null && c.getPassword().equals(pwd)){
			return c;
		}else {
			throw new VGBException("登入失敗，帳號或密碼不正確!");
		}
	}
	public void register(Customer c)throws VGBException{
		if(c==null) {
			throw new IllegalArgumentException("會員註冊時會員物件不得為null");
		}
		
		dao.insert(c);
	}
	public void update(Customer c)throws VGBException{
		if(c==null) {
			throw new IllegalArgumentException("會員修改時會員物件不得為null");
		}
		
		dao.update(c);
	}
}
