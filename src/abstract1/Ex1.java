package abstract1;

abstract class A{  //추상 클래스는 객체 생성 못함
	abstract void method();
}
class B extends A{
	@Override // 추상 메소드라 반드시 재정의 필요
	void method() { 
		
	}
}

public class Ex1 {
	public static void main(String[]args) {
//		A a = new A(); //추상 클래스는 객체 생성 못함
	}
}
