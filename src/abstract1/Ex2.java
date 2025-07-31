package abstract1;

interface AA{
	abstract void method();
}
class BB implements AA{

	@Override
	public void method() {
	
	}
	
}

public class Ex2 {
	public static void main(String[] args) {
		AA aa = new BB();
	}
}
