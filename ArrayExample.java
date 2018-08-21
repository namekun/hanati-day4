package day4_180821_OopStudy;

import java.util.Arrays;

/**
 * 
 * 1차원 배열 선언, 생성, 초기화
 *
 */

public class ArrayExample {
	public static void main(String[] args) {
		int[] array;
		array = new int[10];
		array[0] = 0;
		array[1] = 1;
		array[2] = 2;
		array[5] = 5;
		array[9] = 10;

		// 하나씩 출력해보자
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
//		int[] array2 = new int[] {1, 2, 3, 4, 5};
		int[] array2 = { 1, 2, 3, 4, 5 };

		for (int i = 0; i < array2.length; i++) {
			System.out.print(array2[i]);
		}
		System.out.println();
		// 확장된 for문
		// for(데이터형 접근변수명:반복하고자 하는 배열){}
		// index 접근은 안됩니다.
		for (int a : array2) {
			System.out.print(a);
		} // 위의 for문과 같다.
		System.out.println();

		// 미션 1 (배열 복사)
		int[] array3 = { 3, 1, 9, 2, 5 };
		int[] array4 = new int[7];
		for (int i = 0; i < array3.length; i++) {
			array4[i] = array3[i];
		}
		for (int b : array4) {
			System.out.print(b + " ");
		}
		System.out.println();

		// 미션 2 (sort)
		int[] lottos = { 34, 12, 3, 9, 25, 2 };
//		Arrays.sort(lottos);
		int tmp;
		for (int i = 0; i < lottos.length; i++) {
			for (int j = i + 1; j < lottos.length; j++) {
				if (lottos[i] > lottos[j]) {
					tmp = lottos[i];
					lottos[i] = lottos[j];
					lottos[j] = tmp;
				}
			}
		}

		for (int a : lottos) {
			System.out.print(a + " ");
		}
	}
}
