package ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import RunnableThread.PrintChar;
import RunnableThread.printNum;

public class ExecutorDemo {
	public static void main(String args[]) {
//		ExecutorService executor=Executors.newFixedThreadPool(5);
		ExecutorService executor=Executors.newCachedThreadPool();
		
		executor.execute(new PrintChar('a', 100));
		executor.execute(new PrintChar('b', 100));
		executor.execute(new printNum(100));
		
		executor.shutdown();

		
	}
}
