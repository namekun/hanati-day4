package day4_180821_OopStudy;

/**
 * 일상생활의 객체를 추상화 하기 위한 모델링 클래스 정의 은행계좌 객체
 */

class Account {
	
	// static 초기화 블럭(특수한 목적의 명령어 실행)
	static {
		System.out.println("초기화 블럭 실행!");
	}
	
	
	// 클래스(static) 변수
	public final static String bankName = "하나은행";
	
	// 인스턴스 변수 선언
	String accountNum; // string은 class이름 입니다.
	String accountOwner; // 만약 string을 초기화 시켜주지 않으면 null값으로 잡히게 됩니다.
	int passwd; // int는 초기화 시키지 않으면 0으로 초기화된다.
	long restMoney;

	// 생성자
	public Account(String accountNum, String accountOwner, int passwd, long restMoney) {
		this.accountNum = accountNum;
		this.accountOwner = accountOwner;
		this.passwd = passwd;
		this.restMoney = restMoney;

	}

	// 생성자 오버로딩
	public Account(String accountNum, String accountOwner, int passwd) {
		//this.accountNum = accountNum;
		//this.accountOwner = accountOwner;
		//this.passwd = passwd;
		this(accountNum, accountOwner, 0, 0);
		// 자신의 클래스 안에서 또다른 생성자를 호출할 때 이친구를 부릅니다.
	}

	public Account(String accountNum, String accountOwner) {
		this.accountNum = accountNum;
		this.accountOwner = accountOwner;
	}

	// 생성자 오버로딩 (default constructor)
	public Account() {
		this(null, null);
	}

	// setter, getter 메소드
	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getAccountOwner() {
		return accountOwner;
	}

	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
	}

	public int getPasswd() {
		return passwd;
	}

	public void setPasswd(int passwd) {
		this.passwd = passwd;
	}

	public long getRestMoney() {
		return restMoney;
	}
	
	public void setRestMoney(long restMoney) {
		this.restMoney = restMoney;
	}


	// 인스턴스 메소드
	public long deposit(long money) {
		restMoney += money;
		return restMoney;
	}

	public long withdraw(long money) {
		restMoney -= money;
		System.out.printf("%d원 출금했습니다.\n", money);
		return restMoney;
	}

	public boolean checkPasswd(int pw) {
		return passwd == pw;
	}
	
	// 클래스(static) 메소드
	public static int sum(int a, int b) {
		return a + b;
	}
	

}
