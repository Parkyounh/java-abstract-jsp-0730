package promotion;
class A{}
class B extends A{} 
class C extends A{}
class D extends B{}
class E extends B{}
class F extends C{}


public class ABCDEF {
	public static void method(B b) {
		System.out.println("asd");
	}
	
	public static void main(String[] args) {
//		B b = (B)new A();
		
		method(new B());
	}
}
