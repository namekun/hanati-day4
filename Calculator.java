package day4_180821_OopStudy;

public class Calculator {

	public static int sum(int x, int y) {
		return x + y;
	}

	// 메서드 오버로딩 ( 중복 정의 )	
	public static double sum(double x, double y) {
		return x + y; 
	}

	public static void main(String[] args) {
		Calculator.sum(5, 34);	// 이렇게 불러와도 되지만, 굳이 같은 class내에 있는 애를 이렇게까지 할 필요는 없다.
		
		double a = sum(3.34, 5.45);
		System.out.println(a);
	}
	
	

}
