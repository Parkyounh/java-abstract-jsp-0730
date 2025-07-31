package promotion;

class Controller{
	public MemberService service;

	public MemberService getService() {
		return service;
	}

	public void setService(MemberService service) {
		this.service = service;
	}
	
}
class Service{
	public void login() {
		System.out.println("로그인");
	}
}
class MemberService extends Service{
	public void login() {
		System.out.println("멤버 로그인");
	}
}
class BoardServoce extends Service{}

class AService extends MemberService{
	public void login() {
		System.out.println("A 로그인");
	}
}
class BService extends MemberService{}
class DService extends BoardServoce{}

public class ServiceEx {
	public static void main(String[] args) {
		Controller controller = new Controller();
		
//		controller.setService(new Service());				//에러
//		controller.setService(new BoardServoce()); 			//에러
//		controller.setService(new DService());		        //에러
		
		controller.setService(new MemberService());
		controller.service.login();
		
		controller.setService(new AService());
		controller.service.login();
		
		controller.setService(new BService());
		controller.service.login();
		
	
	}
}
