package RunnableThread;
public class PrintChar implements Runnable {
	private char charToPrint;
	private int times;

	public PrintChar(char c, int t) {
		super();
		// TODO Auto-generated constructor stub
		charToPrint = c;
		times = t;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < times; i++) {
			System.out.print(charToPrint);
		}
	}

}
