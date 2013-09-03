import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class EventdispatcherThreadDemo extends JApplet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame=new JFrame("EventdispatcherThreadDemo");
				frame.add(new EventdispatcherThreadDemo());
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(200,200);
				frame.setVisible(true);
			}
		});

	}

}
