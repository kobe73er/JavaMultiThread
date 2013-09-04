package ProducerAndConsumer;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerAndConsumer {

	private static Buffer buffer = new Buffer();
	
	public static void main(String[] args){
		ExecutorService executor=Executors.newFixedThreadPool(2);
		executor.execute(new ProducerTask());
		executor.execute(new ConsumerTask());
		executor.shutdown();
	}

	public static class ProducerTask implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				int i = 1;
				while (true) {
					System.out.println("Producer writes " + i);
					buffer.write(i++);
					Thread.sleep((int) (Math.random() * 10000));
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static class ConsumerTask implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true){
				try{
					while(true){
						System.out.println("\t\t\tConsumer reads "+buffer.read());
						Thread.sleep((int) (Math.random() * 10000));
					}
				}catch(InterruptedException ex){
					ex.printStackTrace();
				}
			}

		}

	}

	public static class Buffer {
		private static final int CAPACITY = 1;
		private LinkedList<Integer> queue = new LinkedList<Integer>();

		private static Lock lock = new ReentrantLock();

		private static Condition notEmpty = lock.newCondition();
		private static Condition notFull = lock.newCondition();

		public void write(int value) {
			lock.lock();

			try {
				while (queue.size() == CAPACITY) {
					System.out.println("Wait for not Full Condition!");
					notFull.await();
				}
				queue.offer(value);
				notEmpty.signal();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				lock.unlock();
			}

		}

		public int read() {
			int value = 0;
			lock.lock();
			try {
				while (queue.isEmpty()) {
					System.out.println("Wait for not Empty Condition!");
					notEmpty.await();
				}
				value = queue.remove();
				notFull.signal();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				lock.unlock();
				return value;
			}
		}

	}
}
