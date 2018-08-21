package day4_180821_OopStudy;
/**
 * 자바 표준 API의 기본 클래스 사용하기
 */

public class APIExample {
	public static void main(String[] args) {
		
		String str;
		str = new String("자바가 싫습니다.ㄹㅇ루다가");
		System.out.println(str);
		
		// string의 길이
		System.out.println(str.length());
		// string에서 특정 위치의 문자 구하기
		System.out.println(str.charAt(0));
		
		int x = -45;
		Math.abs(x);
		System.out.println(Math.abs(x));
	}
}
