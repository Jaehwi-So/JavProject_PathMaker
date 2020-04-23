package main;

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

import javax.swing.JOptionPane;

public class mMain {
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

		Button resetB = new Button("초기화");
		resetB.setBounds(555, 25, 100, 50);

		Button loadB = new Button("불러오기");
		loadB.setBounds(780, 25, 100, 50);

		addP.add(resetB);
		addP.add(loadB);
		addP.add(newaddB);
		addP.add(addB);
		//하단부 추가 버튼

		//경로 추가 부분
		Font font2 = new Font("", Font.PLAIN, 40);

		//판넬 사이즈
		int loX = 0;
		int loY = 50;
		int siX =900;
		int siY =50;
		int g = 50; //판넬간 간격

		int tbN = 10;//최대 텍스트박스 수
		ArrayList<PanelMaker> arr = new ArrayList<>();
		for(int i =0; i < tbN; i++) {
			PanelMaker pm = new PanelMaker(loX, loY, siX, siY);
			loY +=g;
			arr.add(pm);
		}
		//추가 버튼 클릭시 경로입력박스 추가.		
		addB.addActionListener(new ActionListener() {
			int cnt = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
				cnt++;
				f.add(arr.get(cnt));
				//프레임 갱신용 크기변경
				f.resize(1001, 1000);
				f.resize(1000, 1000);
				if(cnt == tbN-1) {
					JOptionPane.showMessageDialog(f, "그만해");
				}
				//reset 버튼 클릭시 초기화
				resetB.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						for(int i =1; i <tbN; i++ ) {
							f.remove(arr.get(i));
							cnt = 0;//경로 1개만 남기고 제거
						}//for
					}
				});//reset 초기화 버튼
			}
		});//경로입력박스 추가 버튼
		

		f.add(arr.get(0));
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
