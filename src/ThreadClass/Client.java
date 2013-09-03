package ThreadClass;

public class Client {
	public static void someMethod(){
		ThreadClass thread1=new ThreadClass();
		thread1.start();
		
		ThreadClass thread2=new ThreadClass();
		thread2.start();
	}
	public static void main(String args[]){
		someMethod();
	}

}
