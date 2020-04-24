package main;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import data.DataEvent;
import data.Database;
import intro.intro;

public class MainGUI {

	//공용폰트
	Font locF = new Font("",Font.BOLD, 20);
	Font buttonF = new Font("", Font.BOLD, 18);

	public DataEvent machineD;
	public Database database;
	//2020.04.23 ★★★추가사항★★★  GUI_IsCheck 클래스 추가!!!!!!!!!!!!!
	public GUI_IsCheck gui_ischeck = new GUI_IsCheck();

	//이벤트 처리에 필요한 전역변수들
	String main_label_text = "";
	TextArea main_label;
	String location_name;
	int[] location_distance;
	int sequence;

	TextArea nowList;
	String nowList_text = "";
	//============ 메인화면 레이블 텍스트 갱신 =====================
	void renew_label() {
		main_label_text = "";
		ArrayList<String> tmp = machineD.getName_list();
		nowList_text = "현재 목록명 : "+ machineD.getDb().getName() ;
		//main_label_text += " <"+ machineD.getDb().getName() + ">\n";
		for(int i =0 ; i < tmp.size(); i++) {		

			main_label_text += tmp.get(i) + "       \n";	
		}
		//지역명
		nowList.setText(nowList_text);
		main_label.setText(main_label_text);
	}

	//=============새로 만들기 버튼 클릭 이벤트(새 목록 만들기 버튼 클릭 시 실행)==================
	void create_new(Frame fr) {
		Font f = new Font("",Font.PLAIN, 25);
		Frame frame = new Frame();

		frame.setLayout(null);
		frame.setBounds(400, 200, 300, 350);
		frame.setBackground(Color.LIGHT_GRAY);

		Label loc = new Label("새 목록 입력");
		loc.setBounds(20, 40, 200, 100);
		loc.setFont(locF);

		TextField text = new TextField(10);
		text.setBounds(20, 130, 250, 70);
		text.setFont(f);

		Button make = new Button("생성");
		make.setBounds(20, 250, 100, 40);
		make.setFont(buttonF);
		make.setEnabled(false);

		Button esc = new Button("취소");
		esc.setBounds(170, 250, 100, 40);
		esc.setFont(buttonF);

		frame.add(text);
		frame.add(make);
		frame.add(esc);
		frame.add(loc);

		frame.setVisible(true);

		//TextField 에 값이 들어간 경우에만 입력버튼 활성화
		text.addTextListener(new TextListener() {

			@Override
			public void textValueChanged(TextEvent e) {

				if(text.getText().trim().equals("")) {
					make.setEnabled(false);
				}else {
					make.setEnabled(true);	
				}
			}
		});

		//make 버튼 클릭
		make.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				gui_ischeck.setCheck(true); // 2020.04.23 ★★★추가사항★★★ 생성버튼 클릭시 GUI_IsCheck에 있는 값이 true가 됨

				machineD = new DataEvent(fr);
				String s = text.getText();
				machineD.add_database(s);
				renew_label();
				frame.dispose();
			}
		});
		//x버튼 종료
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.dispose();
			}
		});

		//esc버튼 종료
		esc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}

	//================== 목록에 장소 추가 (장소추가 버튼 클릭 시 실행)=========================
	void add_location() {
		Font f = new Font("",Font.BOLD, 20);
		Frame frame = new Frame();
		frame.setLayout(null);
		frame.setBounds(400, 200, 450, 350);
		frame.setBackground(Color.LIGHT_GRAY);

		Label loc = new Label("추가할 장소명 입력 :");
		loc.setBounds(20, 40, 400, 100);
		loc.setFont(locF);

		TextField text = new TextField(10);
		text.setBounds(20, 130, 400, 70);
		text.setFont(f);

		Button make = new Button("완료");
		make.setBounds(40, 250, 140, 40);
		make.setFont(buttonF);
		make.setEnabled(false);

		Button esc = new Button("취소");
		esc.setFont(buttonF);
		esc.setBounds(260, 250, 140, 40);

		frame.add(text);
		frame.add(make);
		frame.add(esc);
		frame.add(loc);
		frame.setVisible(true);

		sequence = 0;
		location_distance = new int[machineD.getName_list().size()];
		//완료 버튼 클릭
		make.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(sequence == 0) {
					location_name = text.getText();
					if(machineD.getName_list().size() == 0) {
						machineD.add_list(location_name, location_distance);
						renew_label();
						frame.setVisible(false);
					}
					else {
						loc.setText("<" + machineD.getName_list().get(sequence)+" & " +location_name+"> 사이의 거리 입력");
						sequence++;
					}
				}
				else if(sequence <= machineD.getName_list().size()) {
					location_distance[sequence - 1] = Integer.parseInt(text.getText());
					if(machineD.getName_list().size() == sequence) {
						machineD.add_list(location_name, location_distance);
						renew_label();
						frame.setVisible(false);
					}
					else {
						loc.setText("<" + machineD.getName_list().get(sequence)+" & " +location_name+"> 사이의 거리 입력");
						sequence++;
					}
				}
				text.setText("");
			}
		});

		//TextField 에 값이 들어간 경우에만 입력버튼 활성화
		text.addTextListener(new TextListener() {

			@Override
			public void textValueChanged(TextEvent e) {

				if(text.getText().trim().equals("")) {
					make.setEnabled(false);
				}else {
					make.setEnabled(true);	
				}
			}
		});

		//x버튼 종료
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.dispose();
			}
		});

		//esc버튼 종료
		esc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();

			}
		});
	}

	//================ 목록에서 장소 삭제(장소삭제 버튼 클릭 시 실행) =============================
	void delete_location() {
		Font f = new Font("",Font.PLAIN, 20);
		Frame frame = new Frame();
		frame.setLayout(null);
		frame.setBounds(400, 200, 300, 350);
		frame.setBackground(Color.LIGHT_GRAY);

		Label loc = new Label("목록에서 삭제할 장소");
		loc.setBounds(20, 40, 220, 100);
		loc.setFont(locF);

		TextField text = new TextField(10);
		text.setBounds(20, 130, 250, 70);
		text.setFont(f);

		Button make = new Button("삭제");
		make.setBounds(20, 250, 100, 40);
		make.setFont(buttonF);
		make.setEnabled(false);

		Button esc = new Button("취소");
		esc.setFont(buttonF);
		esc.setBounds(170, 250, 100, 40);

		frame.add(text);
		frame.add(make);
		frame.add(esc);
		frame.add(loc);

		frame.setVisible(true);

		//TextField 에 값이 들어간 경우에만 입력버튼 활성화
		text.addTextListener(new TextListener() {

			@Override
			public void textValueChanged(TextEvent e) {

				if(text.getText().trim().equals("")) {
					make.setEnabled(false);
				}else {
					make.setEnabled(true);	
				}

			}
		});

		//make 버튼 클릭
		make.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = text.getText();
				int idx = machineD.getName_list().indexOf(name);
				machineD.delete_list(idx);
				renew_label();
				frame.dispose();
			}
		});
		//x버튼 종료
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.dispose();
			}
		});

		//esc버튼 종료
		esc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}


	public static void main(String[] args) {	
		//인트로 시작부분
		intro it = new intro();
		//it.main(args);

		MainGUI m = new MainGUI();
		Frame f = new Frame("초기 화면");
		Font font = new Font("", Font.PLAIN, 20);
		m.machineD = new DataEvent(f);
		f.setLayout(null);
		f.setBounds(20, 20, 1000, 600);
		f.setBackground(Color.lightGray);

		//메인 바탕 패널
		Panel mainPanel = new Panel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 1000, 530);
		mainPanel.setBackground(Color.white);

		mainPanel.setVisible(true);
		f.add(mainPanel);
		//레이블
		//추가된 지점들
		Font labelFont = new Font("", Font.BOLD, 30);
		m.main_label = new TextArea("",0,0, TextArea.SCROLLBARS_NONE);
		m.main_label.setBounds(200, 170, 600, 320);
		m.main_label.setBackground(Color.lightGray);
		m.main_label.setFont(labelFont);
		m.main_label.setEditable(false);

		//현재 목록 
		m.nowList = new TextArea("",0,0, TextArea.SCROLLBARS_NONE);
		m.nowList.setBounds(200, 80, 600, 60);
		m.nowList.setBackground(Color.lightGray);
		m.nowList.setFont(labelFont);
		m.nowList.setEditable(false);

		ImageIcon img = new ImageIcon("loc.jpg");
		JLabel jl = new JLabel(img);
		jl.setBounds(0, 0, 1000, 600);
		f.add(jl);
		mainPanel.add(m.nowList);
		mainPanel.add(m.main_label);
		mainPanel.add(jl);

		//하단부 추가 버튼
		Panel bottomAdd = new Panel();
		bottomAdd.setLayout(null);
		bottomAdd.setFont(font);
		bottomAdd.setBackground(Color.gray);
		bottomAdd.setBounds(0, 525, 1000, 70);
		int buttonXbase = 75;
		int buttonYbase = 10;
		int gan= 180;


		Button create = new Button("새 목록 만들기");
		create.setBounds(buttonXbase, buttonYbase, 130, 50);
		create.setFont(m.buttonF);

		Button load = new Button("불러오기");
		load.setBounds(buttonXbase+200, buttonYbase, 100, 50);
		load.setFont(m.buttonF);

		Button addInfo = new Button("장소추가");
		addInfo.setBounds(buttonXbase+gan*2, buttonYbase, 100, 50);
		addInfo.setFont(m.buttonF);

		Button deleteInfo = new Button("장소삭제");
		deleteInfo.setBounds(buttonXbase+gan*3, buttonYbase, 100, 50);
		deleteInfo.setFont(m.buttonF);

		Button start = new Button("길찾기 시작");
		start.setBounds(buttonXbase+gan*4, buttonYbase, 130, 50);
		start.setFont(m.buttonF);

		//새로 만들기 버튼 클릭
		create.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("새로 만들기");
				m.create_new(f);

			}
		});

		//불러오기 버튼 클릭
		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				m.machineD.load();
				m.gui_ischeck.setCheck(true);
				m.renew_label();
			}		
		});

		//장소추가 버튼 클릭
		addInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("장소추가");
				//m.add_location();

				// 2020.04.23 ★★★ 추가사항 ★★★ 
				// 장소추가 버튼 클릭시
				// GUI_IsCheck 클래스에 있는 ischeck값이 참이면 장소추가 프레임이 생성
				// false이면 새 목록을 만들어달라는 창 생성
				if( m.gui_ischeck.getisCheck() == true ) { //장소추가 버튼을 눌렀을때 새 목록만들기의 값이 없으면 경고창
					m.add_location();

				}else {
					JOptionPane.showMessageDialog(f,"새 목록을 만들어주세요!");

				}
			}
		});

		//장소삭제 버튼 클릭
		deleteInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("장소삭제");
				m.delete_location();
			}
		});

		//길찾기 시작 버튼 클릭
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("길찾기 시작");
				//m.machineD.run_tsp();
				StartGui sg= new StartGui(m.machineD);
				sg.start();
			}
		});


		bottomAdd.add(create);
		bottomAdd.add(load);
		bottomAdd.add(addInfo);
		bottomAdd.add(deleteInfo);
		bottomAdd.add(start);
		//하단부 추가 버튼

		//새로만들기 불러오기 정보 추가 길찾기 시작

		f.add(bottomAdd);//하단 추가 패널
		f.setVisible(true);

		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});





	}//main
}
