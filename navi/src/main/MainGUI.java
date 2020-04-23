package main;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import data.DataEvent;
import data.Database;

public class MainGUI {

	DataEvent machineD;

	//이벤트 처리에 필요한 전역변수들
	String main_label_text = "";
	Label main_label;
	String location_name;
	int[] location_distance;
	int sequence;

	//============ 메인화면 레이블 텍스트 갱신 =====================
	void renew_label() {
		main_label_text = "";
		ArrayList<String> tmp = machineD.getName_list();
		main_label_text += "현재 목록명 : <"+ machineD.getDb().getName() + ">\n";
		for(int i =0 ; i < tmp.size(); i++) {		
			main_label_text += tmp.get(i) + "       \n";	
		}
		main_label.setText(main_label_text);
	}

	//=============새로 만들기 버튼 클릭 이벤트(새 목록 만들기 버튼 클릭 시 실행)==================
	void create_new() {
		Font f = new Font("",Font.PLAIN, 30);
		Frame frame = new Frame();

		frame.setBounds(500, 500, 300, 350);
		frame.setBackground(Color.gray);

		Label loc = new Label("목록 이름");
		loc.setBounds(20, 0, 200, 100);

		TextField text = new TextField(10);
		text.setBounds(20, 200, 200, 70);
		text.setFont(f);

		Button make = new Button("생성");
		make.setBounds(20, 300, 80, 40);
		make.setEnabled(false);

		Button esc = new Button("취소");
		esc.setBounds(150, 300, 80, 40);


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

				machineD = new DataEvent();
				String s = text.getText();
				machineD.add_database(s);
				renew_label();
				frame.setVisible(false);
			}
		});
		//x버튼 종료
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.setVisible(false);
			}
		});

		//esc버튼 종료
		esc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});

	}

	//================== 목록에 장소 추가 (장소추가 버튼 클릭 시 실행)=========================
	void add_location() {
		Font f = new Font("",Font.PLAIN, 30);
		Frame frame = new Frame();

		frame.setBounds(500, 500, 300, 350);
		frame.setBackground(Color.gray);

		Label loc = new Label("추가할 장소명 입력 :");
		loc.setBounds(20, 20, 300, 100);

		TextField text = new TextField(10);
		text.setBounds(20, 100, 200, 70);
		text.setFont(f);

		Button make = new Button("완료");
		make.setBounds(20, 300, 80, 40);
		make.setEnabled(false);

		Button esc = new Button("취소");
		esc.setBounds(150, 300, 80, 40);


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
				frame.setVisible(false);
			}
		});

		//esc버튼 종료
		esc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);

			}
		});
	}

	//================ 목록에서 장소 삭제(장소삭제 버튼 클릭 시 실행) =============================
	void delete_location() {
		Font f = new Font("",Font.PLAIN, 30);
		Frame frame = new Frame();

		frame.setBounds(500, 500, 300, 350);
		frame.setBackground(Color.gray);

		Label loc = new Label("목록에서 삭제할 장소의 이름을 입력해주세요");
		loc.setBounds(20, 0, 200, 100);

		TextField text = new TextField(10);
		text.setBounds(20, 200, 200, 70);
		text.setFont(f);

		Button make = new Button("삭제");
		make.setBounds(20, 300, 80, 40);
		make.setEnabled(false);

		Button esc = new Button("취소");
		esc.setBounds(150, 300, 80, 40);


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
				frame.setVisible(false);
			}
		});
		//x버튼 종료
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.setVisible(false);
			}
		});

		//esc버튼 종료
		esc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
	}


	public static void main(String[] args) {	

		MainGUI m = new MainGUI();
		Frame f = new Frame("초기 화면");
		Font font = new Font("", Font.PLAIN, 18);
		m.machineD = new DataEvent();
		f.setLayout(null);
		f.setBounds(20, 20, 1000, 600);
		f.setBackground(Color.lightGray);

		//레이블
		m.main_label = new Label(m.main_label_text);
		m.main_label.setBounds(100, 100, 800, 400);
		m.main_label.setBackground(Color.gray);
		m.main_label.setFont(font);
		f.add(m.main_label);

		//하단부 추가 버튼
		Panel bottomAdd = new Panel();
		bottomAdd.setLayout(null);
		bottomAdd.setFont(font);
		bottomAdd.setBackground(Color.DARK_GRAY);
		bottomAdd.setBounds(0, 500, 1000, 100);
		int buttonXbase = 75;
		int gan= 180;

		Button create = new Button("새 목록 만들기");
		create.setBounds(buttonXbase, 25, 130, 50);

		Button load = new Button("불러오기");
		load.setBounds(buttonXbase+gan, 25, 100, 50);

		Button addInfo = new Button("장소추가");
		addInfo.setBounds(buttonXbase+gan*2, 25, 100, 50);

		Button deleteInfo = new Button("장소삭제");
		deleteInfo.setBounds(buttonXbase+gan*3, 25, 100, 50);

		Button start = new Button("길찾기 시작");
		start.setBounds(buttonXbase+gan*4, 25, 130, 50);


		//새로 만들기 버튼 클릭
		create.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("새로 만들기");
				m.create_new();

			}
		});

		//불러오기 버튼 클릭
		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("불러오기");
			}		
		});

		//장소추가 버튼 클릭
		addInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("장소추가");
				m.add_location();
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
				System.out.println("길찾기 시작");
				m.machineD.run_tsp();
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
