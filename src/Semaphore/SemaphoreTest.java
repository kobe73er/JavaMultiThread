package Semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import AccountWithSyncUsingLock.AccountWithSyncUsingLock.Account;
import AccountWithSyncUsingLock.AccountWithSyncUsingLock.AddPennyTask;

public class SemaphoreTest {
	private static Account account = new Account();

	public static void main(String args[]) {
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < 100; i++) {
			executor.execute(new AddPennyTask());
		}
		executor.shutdown();

		while (!executor.isTerminated()) {
		}

		System.out.println("What it balance? " + account.getBalance());
	}

	public static class AddPennyTask implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			account.deposit(1);
		}

	}

	public static class Account {

		private static Semaphore semaphore = new Semaphore(1);
		private int balance = 0;

		public int getBalance() {
			return balance;
		}

		public void deposit(int amount) {
			// use a semaphore
			try {
				semaphore.acquire();
				int newBalacne = balance + amount;
				Thread.sleep(5);
				balance = newBalacne;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				// release a semaphore
				semaphore.release();
			}
		}

	}

}
