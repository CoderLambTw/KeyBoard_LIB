package uuu.cmr.test;

import java.util.Scanner;

import uuu.cmr.entity.Customer;

public class TestCustomer_cmr {

	public static void main(String[] args) {
		
		
		Scanner scanner = new Scanner(System.in);
		
		Customer c = new Customer(); 
		System.out.println("請輸入id: ");
		c.setId(scanner.next());
		
		System.out.println("請輸入名字: ");
		c.setName(scanner.next());
		
		System.out.println(c);

	}

}
