package ThreadCommunicate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadCommunicate {
	private static Account account = new Account();

	public static void main(String args[]) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new DepositTask());
		executor.execute(new WithdrawTask());
		executor.shutdown();

		System.out.println("Thread 1\t\tThread 2\t\tBalacne");
	}

	public static class DepositTask implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				try {
					account.deposit((int) (Math.random() * 10) + 1);
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

	public static class WithdrawTask implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				account.withdraw((int) (Math.random() * 10) + 1);
			}
		}
	}

	public static class Account {
		private static Lock lock = new ReentrantLock();
		private static Condition newDeposit = lock.newCondition();

		private int balance = 0;

		public int getBalacne() {
			return balance;
		}

		public void deposit(int amount) {
			lock.lock();
			try {
				balance += amount;
				System.out.println("Deposit " + amount + "\t\t\t\t\t"
						+ getBalacne());
				newDeposit.signalAll();
			} finally {
				lock.unlock();
			}
		}

		public void withdraw(int amount) {
			lock.lock();
			try {
				while (balance < amount)
					newDeposit.await();

				balance -= amount;
				System.out.println("\t\t\tWithdraw " + amount + "\t\t"
						+ getBalacne());
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}
}
