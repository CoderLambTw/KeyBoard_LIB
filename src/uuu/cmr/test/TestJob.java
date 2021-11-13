package uuu.cmr.test;

public class TestJob {

	public static void main(String[] args) {
//		String str = "we we go to the ... we +-146";
//		String[] a = str.split(" ");
//		for(int i=0;i<a.length;i++)
//			System.out.println(a[i]);
		
//		String str1 = "a,b,c,d,e,f,g";
//		String[] a1 = str1.split(",");
//		for(int i=0;i<a1.length;i++)
//			System.out.println(a1[i]+"1");
		
		int line = 5;
		for(int i=1; i<=line;i++) { //第i行
			for(int j=1;j<=line-i;j++) {//每行顯示空白之數量
				System.out.print(" ");
			}
			for(int k=1;k <= i; k++) {//每行顯示*之數量
				System.out.print("*");
			}System.out.println();
		}
		
		
//		for(int i=0,len=a.length;i<len;i++)
//			System.out.println(a[i]);
	}
}
