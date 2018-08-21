# 20180821 hanati-day4

---

## *어제에 이어 OOP진행*

---

## 변수 기타 제한자 - Static

* 변수 앞에 static을 붙여 인스턴스에 상관없이  공유가능한 변수의 개념을 갖는다.
* **클래스(static) 변수**라고도 한다.

  * 인스턴스 변수는 객체가 생성될 때마다 각 객체에 변수가 생성되지만, 클래스 변수는 클래스로부터 생성된 객체들의 수와 상관없이 한개만 생성된다.
  * 한 클래스로부터 생성된 모든 객체들은 **클래스변수를 공유**한다.
* 인스턴스 변수와는 다르게 클래스 변수는 클래스 이름을 통해서 접근한다.

![](https://jjsk27.files.wordpress.com/2012/01/image27.png)

*Account.java*

  ```java
  //클래스이름.변수명
  // ex
  ...
      	// 클래스(static) 변수
  	public final static String bankName = "하나은행"; 
	    // final을 붙여서 읽기전용(?)으로 만들어 줍니다.
  ...
      // 클래스 메소드
      	public static int sum(int a, int b) {
		return a + b;
	}
...
  ```
*AccountExample.java*

```java
...
		// static 변수 사용하기
		System.out.println(Account.bankName);
		// 객체(Instance)생성 안하고도 사용할 수 있다.
		// Account.bankName = "Hana Bank";
		// 사실 이렇게 바꿀 수 있으나, 이렇게 막 바꿀 수 있으면 안되잖어
		// 그래서 final 을 다시 가서 붙여준다.
		// static class 사용하기
		System.out.println(Account.sum(30, 20));
...
```

---
### JVM 메모리 관리 모델

*jvm 메모리 관리모델 예시*

![jvm관리모델예시](https://t1.daumcdn.net/cfile/tistory/255CFB3F5143DC321F)

* 메소드, 스택, 힙영역 이외에도 다른 영역이 있지만 그렇게 알아야 할 필요 X
* `static area(method area)`에는 컴파일 된 `byte code`가 올라간다.
* `stack area`에는 지역변수가 할당된다.
* `heap area`에는 인스턴스가 할당된다.
* 즉, 객체를 생성했을 때(`new`명령어를 통해서) 해당되는 것이 `static area`에 있는지 확인하고, 있다면 이를 복사해서 `heap area`에 인스턴스로 생성한다.
* `heap area`가 크기가 제일 크다. 왜냐하면 인스턴스를 계속 만들어 내야하기 때문에.
* 객체를 생성했는데, 이게 `static area`에 없다면 표준API에서 찾는다. 이때 표준 API에 있다면 그대로 생성, 아니라면 컴파일 에러
* `static`이라고 해서 꼭 바꾸지 못하는 것이 아니다.

*이해를 돕기 위한 그림*

![](http://postfiles10.naver.net/20101009_169/force44_1286554648870_pLu2QR_png/100810_1617_56JVM2.png?type=w3)

* `final static` , `static final`은 똑같은 기능을 하고, 이것은 수정할 수 없는 것이다.
* `static`이 없으면 인스턴스 메소드이기 때문에 반드시 `heap area`에 인스턴스가 있어야한다.
* `Life cycle`이 짧은건 `stack`- `heap` - `static` 순으로 짧다. 

---

## Garbage Collcetion

![](https://t1.daumcdn.net/cfile/tistory/26765B4258C27F891A)

* **Minor G.C**

  * 새로 생성된 대부분의 객체(`Instance`)는 Eden 영역에 위치한다. Eden영역에서 G.C가 한 번 발생한 후, 살아남은 객체는 Survivor 영역 중 하나로 이동된다. 이 과정을 반복하다가, 계속해서 살아 남은 객체는 일정시간 참조되고 있다는 뜻으로, `old`영역으로 이동시킨다.

* **Major G.C**

  * `Old`영역에 있는 모든 객체들을 검사해서, 참조하지 않은 객체들을 한꺼번에 삭제한다. 시간이 오래 걸리고, 실행 중 모든 프로세스가 정지된다. 이것을 `stop-the-world`라고 하는데, `Major G.C`가 발생하면 가비지컬렉션을 실행하는 스레드를 제외한 나머지 스레드는 모두 작업을 멈춘다. 가비지 컬렉션 작업을 완료한 이후에야 중단했던 작업을 다시 시작한다.

* **가비지 컬렉션을 어떤 원리로 소멸시킬 대상을 선정하는가?**

  * 알고리즘에 따라 동작 방식이 매우 다양하지만, 공통적인 원리가 있다. Garbage Collection는 힙 내의 객체 중에서 가비지(Garbage)를 찾아내고, 찾아낸 가비지를 처리해서 힙의 메모리를 회수한다. 참조되고 있지 않은 객체(Instance)를 가비지라고 하며, 객체가 가비지인지 아닌지 판단하기 위해서 ReachAbility라는 개념을 사용한다. 어떤 힙 영역에 할당된 객체가 유효한 참조가 있으면 reachAbility, 없다면 unreachability로 판단한다. 하나의 객체는 다른 객체를 참조하고, 다른 객체는 또 다른 객체를 참조할 수 있기에 참조 사슬이 형성되는데, 이 참조 사슬 중 최초에 참조한 것을 Root Set이라고 칭한다. 힙 영역안에 있는 객체들은 총 4가지 경우에 대한 참조를 하게 된다.

    1. 힙 내의 다른 객체에 의한 참조
    2. Java Stack, 즉 java 메서드 실행 시에 사용하는 지역변수와 파라미터들에 참조
    3. Native Stack에 의해 생성된 객체에 대한 참조
    4. 메서드 영역의 정적 변수에 의한 참조

    * 2, 3, 4 는 Root Set이다. 즉 참조 사슬 중 최초에 참조한 것이다. 

  ![](https://t1.daumcdn.net/cfile/tistory/27134A4D576BD1F526)

* 인스턴스가 가비지 컬렉션의 대상이 되었다고 해서 바로 소멸되는 것은 아니다. 
* 빈번한 가비지 컬렉션의 실행은 시스템의 부담을 일으키기 때문이다.
* 그렇기에 성능에 영향을 미치지 않도록 가비지 컬렉션 실행 타이밍은 별도의 알고리즘을 기반으로 계산이 되며, 이 계산결과를 기반으로 수행된다.

---

## 다시 한 번 클래스 구성요소를 보자

```
[접근제한자] [기타 제한자] class 클래스이름 [extends 부모클래스] [implements 인터페이스] {
	[멤버변수(인스턴스변수 or 클래스변수)]
	[상수]
	[static 초기화 블록]
	[생성자]
	[멤버메소드(인스턴스메소드 or 클래스메소드)]
	[내부클래스]
}
```

* static 초기화 블록 
  * 클래스가 `static area`에 로드되었을 때, 제일 먼저 실행되야 하는 부분이다.
  * 사전 작업같은 느낌.
  * 보통은 임베디드에서 사용한다.

```java
...
// static 초기화 블럭(특수한 목적의 명령어 실행)
	static {
		System.out.println("초기화 블럭 실행!");
	}
...
```

---

## 자바 표준 API의 기본 클래스 사용하기

*APIExample.java*

```java
package day4_180821;
/**
 * 자바 표준 API의 기본 클래스 사용하기
 */

public class APIExample {
	public static void main(String[] args) {
		
		// 명시적 생성
		String str;
		str = new String("자바가 싫습니다.ㄹㅇ루다가");
		System.out.println(str);
		
		// 묵시적 생성
		str = "ㅎㅇㅎㅇ";
        
		// string의 길이
		System.out.println(str.length());
		// string에서 특정 위치의 문자 구하기
		System.out.println(str.charAt(0));
		
		// Math 클래스
		int x = -45;
		System.out.println(Math.abs(x));
	}
}
```

---

## JAVA Package란?

* 서로 관련이 있는 클래스 또는 인터페이스들을 하나의 묶음(그룹) 단위로 구성하는 것.
  * 파일 시스템에서 폴더를 통해 파일을 분류하고 관리하듯이, 패키지를 통해 클래스를 분류하고 관리
* 장점
  * **규모가 큰 프로그램 개발 시 클래스들에 대한 효율적 관리 가능**
  * 클래스 이름간의 충돌을 사전 방지
  * 패키지 단위의 접근권한 제어 가능

## JAVA Package 구성 및 컴파일

* 구성하고자 하는 패키지의 이름과 계층구조(디렉터리 구조) 결정

  * 패키지의 이름은 주로 역할과 관련 지어 명명하거나, 회사명으로 사용
  * Sun마이크로시스템즈에서는 인터넷 도메인 역순을 배열해 소문자를 사용하는 것을 추천.

* 클래스 작성시 자바 소스 코드 최 상단에 사용하고자 하는 package 선언

  ```java
  package kr.co.some.member;
  public someClass{
      // 클래스 구성 요소...
  }
  ```

* 패키지 컴파일 명령어

  ```
  javac   [–d]   [패키지저장위치] SomeClass.java
  javac   [-d]   [c:]   SomeClass.java  // c:/에 패키지 구성
  javac   [-d]   [ . ]   SomeClass.java // 현재 위치에 패키지 구성
  javac   [-d]   [ . ]   *.java   // 패키지에 존재하는 모든 클래스 컴파일
  ```

* 실제로 해보자

 *Some.java*

  ```java
package kr.co.kosta;

public class Some {
	public static void main(String[] args) {
		
	}
}
  ```
 *Student.java*

  ```java
package kr.or.kosta.school;

public class Student{

	String name;
	
	public void info(){
		System.out.println("학생입니다");
	}
	
}  package kr.or.kosta.school;

public class Student{

	String name;
	
	public void info(){
		System.out.println("학생입니다");
	}
	
}
  ```

  ```
  C:\kosta187\workspace\oop>javac -d . Some.java
  C:\kosta187\workspace\oop>javac -d . Student.java
  ```

## Import 사용

* 다른 패키지에 존재하는 클래스를 사용하기 위해 `import`문을 사용하여 사용하고자 하는 클래스의 경로를 지정해야한다.

*`import`하는 방법 3가지*

```java
package kr.co.kosta;

// 특정 클래스 경로 지정하기
//import kr.or.kosta.school.Student; // 권장사항
// 패키지 내의 모든 클래스 경로를 지정
//import kr.or.kosta.school.*; -> 메모리를 많이 잡아먹기에 안쓰는게 낫다.

public class Some {
	public static void main(String[] args) {
		
         // inline import
         kr.or.kosta.school.Student student;
        
        System.out.println();
	}
}
```
* 패키지로 만든 class파일 실행하는 명령어

```
C:\kosta187\workspace\oop>java kr.or.kosta.Some
```

* 자바 표준API는 여러 패키지로 구성되어 제공된다. 다음은 그 중 자주 쓰이는 패키지들이다.
  * java.lang : 프로그래밍의 기본적인 클래스들을 제공
  * java.util   : 프로그래밍의 유용한 유틸리티 클래스들을 제공
  * java.io     : 프로그램 입출력과 관련된 클래스들을 제공
  * java.net  : 네트워크 프로그램과 관련된 클래스들을 제공
  * java.awt : GUI(Window) 프로그램 개발을 위한 클래스들을 제공

## +역컴파일러

```java
C:\kosta187\workspace\oop>javap kr.co.kosta.Some // 역컴파일러 명령어
Compiled from "Some.java"
public class kr.co.kosta.Some {
  public kr.co.kosta.Some();
  public static void main(java.lang.String[]);
}
```

---

## Eclipse

* IDE(통합개발환경)
* workspace
  * project Group를 말합니다.
  * src - source code
* Intellij 써보고싶지만 어렵더라..
* Perspective - 개발자 모드
* Java EE Perspective는 서버개발자용
* Java SE Perspective가 그냥
* Window - Preferences - General - Workspace - Text file Encoding에서 UTF-8로!
* 알아보기 힘든 문자가 나오면 당황하지않고 Editor 프로그램으로 복붙해주자.
* 보통 ctr + space로 완성할 수 있는 단축키들은 템플릿이라고 한다.
* Copy Lines = ctr + alt + 방향키 아래(`그러나 제조사에 따라 해당 키가 다른 단축키일 수 있으니 조심하자.`)

---

## 메소드 [반복]

* 객체와 관련된 기능(행위)을 정의한다.

* 인스턴스 메소드 - 특정 객체의 속성 관련된 기능 정의

  ```java
  public void instanceMethod(){
      // 속성과 관련된 기능 구현
  }
  ```

* 클래스(static) 메소드 - 모든 객체가 공통적으로 사용할 기능 정의

  ```java
  public static void staticMethod{
      // 공통 기능 구현
  }
  ```

* 메소드 형식

  * 메소드의 이름은 소문자로 시작하는 것이 관례(Camel 표기법)

  ```java
  [접근 제한자] [기타 제한자] 반환형 methodName(매개변수리스트){
            //  기능 구현
   }
  ```

* 메소드 반환형

  * 반환값이 없는 경우 void
  * 기본 자료형 뿐만 아니라 참조자료형의 데이터도 반환.

  ```java
  public int sum(int x, int y){
      return x + y;
  }
  ```

  ```java
  public Student getStudent(int ssn){
      ...
          return student;
  }
  ```

* 메소드 오버로딩

  * 한 클래스 내에 같은 이름을 가진 메서드를 여러 개 중복정의 하는 것

  * 규칙 

    * 메서드 이름은 같으나 매개변수의 개수나 데이터 유형이 서로 달라야한다.
    * 메서드의 반환형 만으로는 메서드 오버로딩 불가

    ```java
    // 매개변수가 없는 메소드 정의
    public static void speedUp() {
         currentSpeed++;
    }
    
    // 정수형 매개변수 하나인 메소드 정의
    public static void speedUp(int speed) {
         currentSpeed += speed;
    }
    ```

* Ctr + F11 = 맨 마지막 명령문 실행

* Run java는 다른키. 편한걸로 바꿔줘도 무방.

---

## Array

* 대량의 데이터를 저장하고, 관리하기 위한 가장 기본이 되는 데이터 구조
  * 이름 없는 변수들의 순차적 나열
* 자바는 배열을 하나의 클래스로 취급한다.
* 생성된 배열 객체(인스턴스)는 직접 접근할 수 없고 반드시 참조변수를 통해 접근해야 한다.
* 배열은 동일한 데이터 유형만 저장할 수 있으며, 한번 생성된 후에는 배열의 크기를 변경할 수 없다.
* 배열은 각각의 원소에 할당하는 데이터 유형에 따라 기본 데이터 타입(8개) 배열과 참조(`reference`) 타입 배열로 구분된다.
* 배열 사용법

```java
package day4_180821_OopStudy;

/**
 * 
 * 1차원 배열 선언, 생성, 초기화
 *
 */

public class ArrayExample {
	public static void main(String[] args) {
		// 아래 형태 모두 배열 선언가능하다.
		int [] x; // 타입 [] 변수명으로 선언
		int xy []; // 타입 변수명 [] 으로 선언
		// 배열 생성
		int[] p = new int[6]; // 이렇게 생성하면 int형이니까 자동으로 초기화되어서, 값들이 모두 0으로 초기화 된다.
		// 초기화
		p[0] = 5;
		p[1] = 4;
		//...
		p[5] = 0;
		// 특정값을 대입하며 배열 선언 - 초기화와 동시에 배열을 선언하여 생성.
		int [] intArray = {1, 2, 3};
		int [] intArray2 = new int[] {5, 4, 3, 2, 1};
	}
}
```

*ArrayExample.java*

```java
package day4_180821_OopStudy;

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
		
	}
}

```

* 확장된 for문
  *  for(데이터형 접근변수명:반복하고자 하는 배열){}
  * index로의 접근은 안됩니다.

*ArrayExample.java 미션추가ver*

```java
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
```

