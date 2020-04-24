package intro;


import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class intro extends Thread {
	public static void main(String[] args) {
		Boolean t = true;
		Boolean t2 = true;
		Frame f = new Frame();
		
		f.setBounds(20, 20, 1000, 600);
		ImageIcon intro1 = new ImageIcon("1.png");
		JLabel j1 = new JLabel(intro1); //이미지 추가
		j1.setBounds(0, 0, 800, 600);//인트로1 위치
		
		ImageIcon intro2 = new ImageIcon("2.png");
		JLabel j2 = new JLabel(intro2); //이미지 추가
		j2.setBounds(0, 0, 800, 600);//인트로2 위치
		
		ImageIcon intro3 = new ImageIcon("3.png");
		JLabel j3 = new JLabel(intro3); //이미지 추가
		j2.setBounds(300, 0, 800, 600);//인트로2 위치
		
		f.add(j1);
		f.setVisible(t);
		
		try {
			Thread.sleep(2000);
			t = false;
			f.remove(j1);
			f.add(j2);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		f.setVisible(t2);
		
		try {
			Thread.sleep(500);
			t2 = false;
			f.remove(j2);
			f.add(j3);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		f.setVisible(true);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		//
	
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});


	}//main
}
