package ProgressBar;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class CopyFile extends JFrame {
	private JProgressBar jpb = new JProgressBar();
	private JButton jbtCopy = new JButton("Copy");
	private JTextField jtfFrom = new JTextField();
	private JTextField jtfTo = new JTextField();

	public CopyFile() {
		JPanel jPanel2 = new JPanel();
		jPanel2.setLayout(new BorderLayout());
		jPanel2.setBorder(new TitledBorder("From"));
		jPanel2.add(jtfFrom, BorderLayout.CENTER);

		JPanel jPanel3 = new JPanel();
		jPanel3.setLayout(new BorderLayout());
		jPanel3.setBorder(new TitledBorder("To"));
		jPanel3.add(jtfTo, BorderLayout.CENTER);

		JPanel jPanle1 = new JPanel();
		jPanle1.setLayout(new GridLayout(2, 1));
		jPanle1.add(jPanel2);
		jPanle1.add(jPanel3);

		JPanel jPanel4 = new JPanel();
		jPanel4.add(jbtCopy);

		this.add(jpb, BorderLayout.NORTH);
		this.add(jPanle1, BorderLayout.CENTER);
		this.add(jPanel4, BorderLayout.SOUTH);

		jpb.setStringPainted(true);

		jbtCopy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new Thread(new CopyFileTask()).start();
			}
		});
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CopyFile frame = new CopyFile();
		frame.setSize(400, 180);
		frame.setVisible(true);

	}

	class CopyFileTask implements Runnable {
		private int currentValue;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			BufferedInputStream in = null;
			BufferedOutputStream out = null;

			try {
				File inFile = new File(jtfFrom.getText().trim());
				in = new BufferedInputStream(new FileInputStream(inFile));
				
				File outFile=new File(jtfTo.getText());
				out=new BufferedOutputStream(new FileOutputStream(outFile));
				
				long totalBytes=in.available();
				
				jpb.setValue(0);
				jpb.setMaximum(100);
				
				int r;
				long bytesRead=0;
				byte[] b=new byte[10];
				while((r=in.read(b,0,b.length))!=-1){
					out.write(b, 0, r);
					bytesRead+=r;
					currentValue=(int) (bytesRead*100/totalBytes);
					
					jpb.setValue(currentValue);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try{
				if(in!=null)in.close();
				if(out!=null)out.close();
			}catch(Exception ex){}
			}
		}

	}
}
