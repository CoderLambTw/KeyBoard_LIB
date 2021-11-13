package uuu.cmr.test;

import uuu.cmr.entity.Outlet;

public class TestOutlet {

	public static void main(String[] args) {
		Outlet outlet = new Outlet();
		outlet.setId(1);
		outlet.setName("Java SE 9技術手冊");
		outlet.setUnitPrice(650);
		outlet.setDiscount(21);
		outlet.setStock(5);
		outlet.setPhotoUrl("https://ppt.cc/fWXIKx");
		outlet.setDescription("提供Java 9新功能快速索引。"
				+ "深入探討Java模組平臺系統");
		
//		System.out.println("身分證字號: " + outlet.getId());
//		System.out.println("會員名稱: " + outlet.getName());
//		System.out.println("售價: " + outlet.getUnitPrice());//取得售價
//		System.out.println(outlet.getDiscount());
//		System.out.println(outlet.getDiscountString());
//		System.out.println("訂價: " + outlet.getListPrice());//取得訂價
//		System.out.println("圖片連結: " + outlet.getPhotoUrl());
//		System.out.println("產品敘述: " + outlet.getDescription());
		System.out.println(outlet);

	}

}
