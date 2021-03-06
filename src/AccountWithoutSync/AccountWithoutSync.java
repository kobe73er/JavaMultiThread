package AccountWithoutSync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//两种版本的同步方法
public class AccountWithoutSync {
	private static Account account = new Account();

	public static void main(String args[]) {
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < 100; i++) {
			executor.execute(new AddPennyTask());
		}
		executor.shutdown();
		while (!executor.isTerminated()) {
		}
		System.out.println("What is balance?" + account.getBalacne());
	}

	public static class AddPennyTask implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			synchronized (account) {
				account.deposit(1);
			}

		}

	}

	public static class Account {
		private int balance = 0;

		public int getBalacne() {
			return balance;
		}
      
		public /*synchronized*/ void deposit(int amount) {
			int newBalance = balance + amount;
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			balance = newBalance;
		}

	}
}
