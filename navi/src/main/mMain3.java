package main;
//노가다 panel로 묶음
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class mMain3 {
	public static void main(String[] args) {

		Frame f = new Frame("문장 입력기");
		Font font = new Font("", Font.PLAIN, 18);
		f.setLayout(null);
		f.setBounds(20, 20, 1000, 1000);
		f.setBackground(Color.lightGray);


		//하단부 추가 버튼
		Panel addP = new Panel();
		addP.setLayout(null);
		addP.setFont(font);
		addP.setBackground(Color.DARK_GRAY);
		addP.setBounds(0, 900, 1000, 100);
		Button addB = new Button("추가");
		addB.setBounds(105, 25, 100, 50);

		Button newaddB = new Button("새지점 입력");
		newaddB.setBounds(330, 25, 100, 50);

		Button makeB = new Button("새로만들기");
		makeB.setBounds(555, 25, 100, 50);

		Button loadB = new Button("불러오기");
		loadB.setBounds(780, 25, 100, 50);

		addP.add(makeB);
		addP.add(loadB);
		addP.add(newaddB);
		addP.add(addB);
		//하단부 추가 버튼

		//경로 추가 
		Font font2 = new Font("", Font.PLAIN, 40);
		
		int loX = 0;
		int loY = 50;
		int siX =900;
		int siY =50;
		int g = 50;
		
		PanelMaker  pm =  new PanelMaker(loX, loY, siX, siY);
		PanelMaker  pm1 =  new PanelMaker(loX, loY+g, siX, siY);
		PanelMaker  pm2 =  new PanelMaker(loX, loY+2*g, siX, siY);
		PanelMaker  pm3 =  new PanelMaker(loX, loY+3*g, siX, siY);
		PanelMaker  pm4 =  new PanelMaker(loX, loY+4*g, siX, siY);
		PanelMaker  pm5 =  new PanelMaker(loX, loY+5*g, siX, siY);
		PanelMaker  pm6 =  new PanelMaker(loX, loY+6*g, siX, siY);
		PanelMaker  pm7 =  new PanelMaker(loX, loY+7*g, siX, siY);
		PanelMaker  pm8 =  new PanelMaker(loX, loY+8*g, siX, siY);
		PanelMaker  pm9 =  new PanelMaker(loX, loY+9*g, siX, siY);
		PanelMaker  pm10 =  new PanelMaker(loX, loY+10*g, siX, siY);
				
		addB.addActionListener(new ActionListener() {
			 
			 //추가버튼 누르는 횟수
			int cnt =0;
			@Override
			public void actionPerformed(ActionEvent e) {
				cnt++;
				
					if(cnt == 1) {
						f.add(pm1);
						f.resize(1001,1000);
					}
					if(cnt == 2) {
						f.add(pm2);
						f.resize(1000,1000);
					}
					if(cnt == 3) {
						f.add(pm3);
						f.resize(1001,1000);
					}
					if(cnt == 4) {
						f.add(pm4);
						f.resize(1000,1000);
					}
					if(cnt == 5) {
						f.add(pm5);
						f.resize(1001,1000);
					}
					if(cnt == 6) {
						f.add(pm6);
						f.resize(1000,1000);
					}
					if(cnt == 7) {
						f.add(pm7);
						f.resize(1001,1000);
					}
					if(cnt == 8) {
						f.add(pm8);
						f.resize(1000,1000);
					}
					if(cnt == 9) {
						f.add(pm9);
						f.resize(1001,1000);
					}
					if(cnt == 10) {
						f.add(pm10);
						f.resize(1000,1000);
					}
				
			}
		});
		
	
		f.add(pm);//경로 추가 패널
		f.add(addP);//하단 추가 패널
		f.setVisible(true);

		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});


	}//main
}
