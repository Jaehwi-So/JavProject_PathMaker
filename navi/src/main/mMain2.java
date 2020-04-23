package main;
//노가다 위치 직접입력
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

public class mMain2 {
	public static void main(String[] args) {

		Frame f = new Frame("문장 입력기");
		Font font = new Font("", Font.PLAIN, 18);

		f.setBounds(200, 200, 1000, 800);
		f.setBackground(Color.lightGray);


		//하단부 추가 버튼
		Panel addP = new Panel();
		addP.setLayout(null);
		addP.setFont(font);
		addP.setBackground(Color.DARK_GRAY);
		addP.setBounds(0, 700, 1000, 100);
		Button addB = new Button("추가");
		addB.setBounds(105, 25, 100, 50);

		Button deleteB = new Button("????");
		deleteB.setBounds(330, 25, 100, 50);

		Button makeB = new Button("새로만들기");
		makeB.setBounds(555, 25, 100, 50);

		Button loadB = new Button("불러오기");
		loadB.setBounds(780, 25, 100, 50);

		addP.add(makeB);
		addP.add(loadB);
		addP.add(deleteB);
		addP.add(addB);
		//하단부 추가 버튼

		//경로 추가 
		Panel locate = new Panel();
		locate.setLayout(null);
		TextField tf1 = new TextField(20);
		tf1.setBounds(100,100,500,80);
		Button add = new Button("입력");
		add.setBounds(630, 100, 150, 80);
		Button delete = new Button("삭제");
		delete.setBounds(800, 100, 150, 80);

		locate.add(tf1);
		locate.add(add);
		locate.add(delete);
		//경로 추가
		TextField tf2 = new TextField(20);
		tf2.setBounds(100, 200, 500, 80);
		Button add2 = new Button("입력");
		add2.setBounds(630, 200, 150, 80);
		Button delete2 = new Button("삭제");
		delete2.setBounds(800, 200, 150, 80);
		//경로2
		TextField tf3 = new TextField(20);
		tf3.setBounds(100, 300, 500, 80);
		Button add3 = new Button("입력");
		add3.setBounds(630, 300, 150, 80);
		Button delete3 = new Button("삭제");
		delete3.setBounds(800, 300, 150, 80);
		
		
		addB.addActionListener(new ActionListener() {

			int addcnt = 0; //추가버튼 누르는 횟수
			@Override
			public void actionPerformed(ActionEvent e) {
				addcnt++;
				if(addcnt == 1) {
				locate.add(tf2);
				locate.add(add2);
				locate.add(delete2);
				}
				
				if(addcnt == 2) {
					locate.add(tf3);
					locate.add(add3);
					locate.add(delete3);
				}
				
				
			}
		});


		f.add(locate, BorderLayout.CENTER);//경로 추가 패널
		f.add(addP, BorderLayout.SOUTH);//하단 추가 패널
		f.setVisible(true);

		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});


	}//main
}
	