package uuu.cmr.entity;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Customer {
	public static final char MALE = 'M';
	public static final char FEMALE = 'F';
	public static final int PASSWORD_MIN_LENGTH = 6;
	public static final int PASSWORD_MAX_LENGTH = 20;
	/**
	 * id PKey, 身分證號, 必要欄位
	 */
	private String id; //Pkey, ROD Id, required 
	/**
	 * password 長度必須為6~20字元, 必要欄位
	 */
	 
	private String password; //required	 
	private String name; //required	 
	private LocalDate birthday; //required 	 
	private char gender; //required, M:Male, F:Female	 
	private String email; //required	 
	private String address=""; //optional	 
	private String phone=""; //optional	 
	private boolean married; //optional 
	//private int status; 0:新會員,1:已啟用
	
	public Customer() {		
	}	
	
	public Customer(String id, String password, String name) {
		this.setId(id);
		this.setPassword(password);
		this.setName(name);
	}

	public Customer(String id, String password, String name, 
			char gender, String email) {
		this(id,password,name); //建構式可在第一行可用this呼叫同一類別之其他建構式
//		this.setId(id);
//		this.setPassword(password);
//		this.setName(name);
		this.setGender(gender);
		this.setEmail(email);
	}

	public String getId() {
		return id;
	}
	
	private static final String ID_FIRST_CHAR_SEQUENCE = "ABCDEFGHJKLMNPQRSTUVWXSYZIO";
	private static final String ID_PATTERN = "[A-Za-z][12][0-9]{8}";
	
	public void setId(String id) {
		if(this.checkId(id)==true) {
			this.id = id;
		}else {
			Logger.getLogger("錯誤訊息紀錄").log(Level.SEVERE,"身分證號不正確: " + id);
			throw new DataInvalidException("身分證號不正確");
		}	
	
	}
	
	public boolean checkId(String id) {
		/*TODO:身份證字號驗證方式
		 * 檢查id不是null而且格式符合(ROC ID Regular Expression[書寫內容可參考維基百科、Regular Expression library])
		 * Regular Expression:[]內輸入字元條件,{}表左邊[]需要出現的次數
		基本條件:長度要為10個字元
				第一個字元為英文字母, 第二位數字1表男性 2表女性
				剩下8個數字為0至9(有其特殊規則,維基搜尋)*/
		String idFirstCharSeq = "ABCDEFGHJKLMNPQRSTUVWXYZIO";
		//各字元之要求條件
		if(id!=null && id.matches("[A-Za-z][12][0-9]{8}")) {
			//TODO:將id的第一碼英文字元轉換成對應的整數
			int firstNum = idFirstCharSeq.indexOf(id.charAt(0))+10;
			
			//TODO:n1*1+n2*9+n3*8+n4*7+n5*6+n6*5+n7*4+n8*3+n9*2+n10*1+n11*1
			int sum = 0;//firstNum/10 + firstNum%10*9;
//			sum += (id.charAt(1)-'0')*8;
//			sum += (id.charAt(2)-'0')*7;
//			sum += (id.charAt(3)-'0')*6;
			
			//TODO:當加總後%10等於0
			return (sum%10==0);
		}else {
			return false;
		}	
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if(password!=null &&
				password.length()>=PASSWORD_MIN_LENGTH 
				&& password.length()<=PASSWORD_MAX_LENGTH) {
			this.password = password;
		}else {
			//System.out.println("必須輸入6~20個字元的密碼! EX: " + password);
			throw new DataInvalidException("密碼長度需為" + PASSWORD_MIN_LENGTH + "~" + PASSWORD_MAX_LENGTH + "個字元!");
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name!=null && (name = name.trim()).length()>0) { //字串長度>0
			this.name = name;
		}else {
			//System.out.println("必須輸入姓名");
			throw new DataInvalidException("必須輸入姓名!");
		}
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		if(gender== MALE || gender== FEMALE) {
			this.gender = gender;
		}else {
			//System.out.println("性別資料不正確，必須是'M'或'F'!");
			throw new DataInvalidException("性別資料不正確，必須是"+MALE+"或"+FEMALE+"!");
		}
	}

	public String getEmail() {
		return email;
	}

	private static final String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
	public void setEmail(String email) {
		if(email!=null && email.matches(EMAIL_PATTERN)) {
			this.email = email;
		}else {
			//System.out.println("Email不正確");
			throw new DataInvalidException("Email格式不正確!");
		}
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean getMarried() {
		return married;
	}

	public void setMarried(boolean married) {
		this.married = married;
	}

	/**
	 * 指派客戶生日日期
	 * @param theDay 客戶的出生日期, 必須在今天之前
	 */
	
	public void setBirthday(LocalDate theDay) {
		if(theDay.isBefore(LocalDate.now())) {
			birthday = theDay;
		}else {
			Logger.getLogger("錯誤訊息紀錄").log(Level.SEVERE,"客戶生日必須在今天之前");
			throw new DataInvalidException("客戶生日不正確，必須在今天之前");
		}
	}
	
	public void setBirthday(String dateStr) {
		try {
			LocalDate dateObj = LocalDate.parse(dateStr);
			setBirthday(dateObj);
		}catch(DateTimeParseException ex) {
			throw new DataInvalidException("客戶生日資料不正確，必須符合iso 8601格式! (如:2000-01-01)");
		}
	}
	/**
	 * overloading method 示範
	 * @param year 客戶的出生年份
	 * @param month 客戶的出生月份
	 * @param day 客戶的出生日
	 */
	public void setBirthday(int year, int month, int day) {
		LocalDate dateObj = LocalDate.of(year, month, day);
		setBirthday(dateObj);
	}
	
	/**
	 * 取得客戶生日日期
	 * @return 客戶生日日期(LocalDate)
	 */
	public LocalDate getBirthday() {
		return birthday;
	}
	
	/**
	 * @return 傳回年齡
	 */
	public int getAge() {
		if(birthday!=null) {
			int birthYear = birthday.getYear();
			int thisYear = LocalDate.now().getYear();
			return thisYear-birthYear;
		}else {
			return 30; //chap13介紹正確的寫法
		}
	}
	/**
	 * @param birthYear 出生日期的年份
	 * @param thisYear 計算年齡時的年份
	 * @return 傳回年齡
	 */
	public int getAge(int birthYear, int thisYear) {
		return thisYear-birthYear;
	}

	@Override
	public String toString() {
		return this.getClass().getName() + "\n[id=" + id 
				+ ", 密碼=" + password 
				+ ", 姓名=" + name 
				+ ", 生日=" + birthday
				+ ", 性別=" + gender 
				+ ", 信箱=" + email 
				+ ", 地址=" + address 
				+ ", 電話號碼=" + phone 
				+ ", 結婚與否="  + married 
				+ ", 年齡=" + getAge() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Customer))
			return false;
		Customer other = (Customer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	
	 
}
 
