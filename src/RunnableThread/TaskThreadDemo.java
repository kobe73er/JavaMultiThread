package RunnableThread;

public class TaskThreadDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runnable printA=new PrintChar('a',100);
		Runnable printB=new PrintChar('b', 100);
		Runnable printNum=new printNum(100);
		
		Thread thread1=new Thread(printA);
		Thread thread2=new Thread(printB);
		Thread thread3=new Thread(printNum);
		
		thread1.setPriority(Thread.MAX_PRIORITY);
		System.out.println(thread1.getPriority());
		System.out.println(thread2.getPriority());
		System.out.println(thread3.getPriority());
		thread1.start();
		//thread2.setPriority(Thread.MAX_PRIORITY);
		thread2.start();
		//thread3.setPriority(Thread.MAX_PRIORITY);
		thread3.start();
	}

}
