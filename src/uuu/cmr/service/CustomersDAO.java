package uuu.cmr.service;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import uuu.cmr.entity.Customer;
import uuu.cmr.entity.VGBException;
import uuu.cmr.entity.VIP;

class CustomersDAO {
	private static final String SELECT_CUSTOMER_BY_ID = 
			"SELECT id,password,name,birthday,gender,email,address,discount,class_name"
			+ " FROM customers"
			+ " WHERE id=?";
	public Customer selectCustomerById(String id)throws VGBException{
		Customer c = null;

		try (Connection connection = RDBConnection.getConnection(); // 1,2 取得連線，最後要close
				PreparedStatement pstmt = connection.prepareStatement(SELECT_CUSTOMER_BY_ID);// 3.準備指令，最後要close
		) {
			// 3.1 傳入?(參數)的值
			pstmt.setString(1, id);
			try (ResultSet rs = pstmt.executeQuery();// 4.執行指令, 最後要close rs
			) {
				// 5.處理rs
				while (rs.next()) {
					String className = rs.getString("class_name");
					if ("VIP".equals(className)) {
						c = new VIP();
						((VIP) c).setDiscount(rs.getInt("discount"));
					} else {
						c = new Customer();
					}
					c.setId(rs.getString("id"));// TODO
					c.setPassword(rs.getString("password"));
					c.setName(rs.getString("name"));
					c.setBirthday(rs.getString("birthday"));
					c.setGender(rs.getString("gender").charAt(0));
					c.setEmail(rs.getString("email"));
					c.setAddress(rs.getString("address"));
				}
			}
		} catch (SQLException e) {
			throw new VGBException("查詢客戶失敗", e);
		}	
		return c;
	}
	
	private static final String INSERT_CUSTOMER = "INSERT INTO customers " + 
			"(id, password, name, birthday, gender, email,"
			+ "address, discount, class_name)" + 
			"VALUES (?,?,?,?,?,?,?,?,?)";
	public void insert(Customer c)throws VGBException{		
		try (
				Connection connection = RDBConnection.getConnection(); // 1,2 取得連線，最後要close
				PreparedStatement pstmt = connection.prepareStatement(INSERT_CUSTOMER);// 3.準備指令，最後要close
		){			
			//3.1 傳入?(參數)的值
			//TODO
			pstmt.setString(1, c.getId());
			pstmt.setString(2, c.getPassword());
			pstmt.setString(3, c.getName());
			pstmt.setString(4, c.getBirthday()!=null?c.getBirthday().toString():null);
			pstmt.setString(5, String.valueOf(c.getGender()));
			pstmt.setString(6, c.getEmail());
			pstmt.setString(7, c.getAddress());
			if(c instanceof VIP) {
				pstmt.setInt(8, ((VIP)c).getDiscount());
			}else {
				pstmt.setInt(8, 0);
			}
			pstmt.setString(9, c.getClass().getSimpleName());
			
			//4.執行指令
			pstmt.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			String colName;
			if(e.getMessage().indexOf("email_UNIQUE")>=0) {
				colName="Email";
			}else {
				colName="帳號";
			}
			//Logger.getLogger("測試元件TestCustomerInsert").log(Level.SEVERE, "執行指令失敗，"+ colName +"重複註冊!", e);
			throw new VGBException("客戶"+ colName +"重複註冊!", e);
		}catch (SQLException e) {
			throw new VGBException("新增客戶失敗", e);
		}	
	}
	
	private static final String UPDATE_CUSTOMER = "UPDATE customers SET " + 
			"password=?, name=?, birthday=?, gender=?, email=?,"
			+ "address=?, discount=?, class_name=? " + 
			"WHERE id=?";
	public void update(Customer c)throws VGBException{		
		try (
				Connection connection = RDBConnection.getConnection(); // 1,2 取得連線，最後要close
				PreparedStatement pstmt = connection.prepareStatement(UPDATE_CUSTOMER);// 3.準備指令，最後要close
		){			
			//3.1 傳入?(參數)的值
			//TODO
			pstmt.setString(9, c.getId());
			pstmt.setString(1, c.getPassword());
			pstmt.setString(2, c.getName());
			pstmt.setString(3, c.getBirthday()!=null?c.getBirthday().toString():null);
			pstmt.setString(4, String.valueOf(c.getGender()));
			pstmt.setString(5, c.getEmail());
			pstmt.setString(6, c.getAddress());
			if(c instanceof VIP) {
				pstmt.setInt(7, ((VIP)c).getDiscount());
			}else {
				pstmt.setInt(7, 0);
			}
			pstmt.setString(8, c.getClass().getSimpleName());
			
			//4.執行指令
			pstmt.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			String colName;
			if(e.getMessage().indexOf("email_UNIQUE")>=0) {
				colName="Email";
			}else {
				colName="帳號";
			}
			//Logger.getLogger("測試元件TestCustomerInsert").log(Level.SEVERE, "執行指令失敗，"+ colName +"重複註冊!", e);;
		}catch (SQLException e) {
			throw new VGBException("修改客戶失敗", e);
		}	
	}
}
