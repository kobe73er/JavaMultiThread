package RunnableThread;

public class printNum implements Runnable {
	private int lastNum;

	public printNum(int lastNum) {
		super();
		this.lastNum = lastNum;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Thread thread4=new Thread(new PrintChar('c', 40));
		thread4.start();
		try{
		for(int i=1;i<=lastNum;i++){
			System.out.print(" "+i);
			if(i==50) thread4.join();
		}
	}
		catch(InterruptedException ex){}

//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		try {
//			for (int i = 1; i < lastNum; i++) {
//				System.out.print(" " + i);
//				if (i >= 50)
//					Thread.sleep(10);
//			}
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		// Thread.yield();
//	}
}
}
