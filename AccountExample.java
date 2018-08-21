package day4_180821_OopStudy;

/**
 * 프로그램 실행을 위한 애플리케이션 클래스 정의
 */
import java.util.Scanner;

public class AccountExample {
	public static void main(String[] args) {
		System.out.println("은행 계좌 애플리케이션 시작됨....");

		// 클래스로부터 객체(인스턴스) 생성
		// Account account = new Account(); // reference 타입 선언하고, new 명령어를 통해서 메모리 할당.

		// 인스턴스의 속성과 기능 사용
		//account.accountNum = "1111-2222-3333";
		//account.accountOwner = "Mr.Kim";
		//account.restMoney = 100000;
		//account.passwd = 1234;
		//System.out.println(account.accountNum);

		// 생성자를 이용한 객체 생성
		Account account = new Account("1111-2222-3333", "Mr.kim", 1234, 100000);

		int passwd;
		System.out.print("비밀번호를 입력하세요 : ");
		Scanner sc = new Scanner(System.in);
		passwd = sc.nextInt();

		boolean result = account.checkPasswd(passwd);
		if (result) {
			long money = account.deposit(100000);
			System.out.println("입금 후 잔액 : " + money);
			money = account.withdraw(10000);
			System.out.println("출금 후 잔액 : " + money);
		} else {
			System.out.println("비밀번호를 확인하세요");
		}

		//Account account2 = new Account();
		//인스턴스 변수의 경우 JVM에 의해 초기화된다.
		//System.out.println(account2.accountNum);
		//System.out.println(account2.restMoney);
		//account2.accountNum = "2222-3333-4444";
		//account2.accountOwner = "Mr.song";
		//account2.restMoney = 500000;
		//account2.passwd = 1111;

		Account account2 = new Account("2222-3333-4444", "Mr.song");
		
		account2.setPasswd(12345);
		account2.setRestMoney(700000);
		
		System.out.println(account2.getAccountNum());
		System.out.println(account2.getAccountOwner());
		System.out.println(account2.getPasswd());
		System.out.println(account2.restMoney);

		Account account3 = new Account();
		
		// static 변수 사용하기
		System.out.println(Account.bankName);
		// 객체(Instance)생성 안하고도 사용할 수 있다.
		// Account.bankName = "Hana Bank";
		// 사실 이렇게 바꿀 수 있으나, 이렇게 막 바꿀 수 있으면 안되잖어
		// 그래서 final 을 다시 가서 붙여준다.
		// static class 사용하기
		System.out.println(Account.sum(30, 20));
		
		System.out.println("은행 계좌 애플리케이션 종료됨....");
	}
}
