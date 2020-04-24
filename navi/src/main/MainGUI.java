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

	//������Ʈ
	Font locF = new Font("",Font.BOLD, 20);
	Font buttonF = new Font("", Font.BOLD, 18);

	public DataEvent machineD;
	public Database database;
	//2020.04.23 �ڡڡ��߰����סڡڡ�  GUI_IsCheck Ŭ���� �߰�!!!!!!!!!!!!!
	public GUI_IsCheck gui_ischeck = new GUI_IsCheck();

	//�̺�Ʈ ó���� �ʿ��� ����������
	String main_label_text = "";
	TextArea main_label;
	String location_name;
	int[] location_distance;
	int sequence;

	TextArea nowList;
	String nowList_text = "";
	//============ ����ȭ�� ���̺� �ؽ�Ʈ ���� =====================
	void renew_label() {
		main_label_text = "";
		ArrayList<String> tmp = machineD.getName_list();
		nowList_text = "���� ��ϸ� : "+ machineD.getDb().getName() ;
		//main_label_text += " <"+ machineD.getDb().getName() + ">\n";
		for(int i =0 ; i < tmp.size(); i++) {		

			main_label_text += tmp.get(i) + "       \n";	
		}
		//������
		nowList.setText(nowList_text);
		main_label.setText(main_label_text);
	}

	//=============���� ����� ��ư Ŭ�� �̺�Ʈ(�� ��� ����� ��ư Ŭ�� �� ����)==================
	void create_new(Frame fr) {
		Font f = new Font("",Font.PLAIN, 25);
		Frame frame = new Frame();

		frame.setLayout(null);
		frame.setBounds(400, 200, 300, 350);
		frame.setBackground(Color.LIGHT_GRAY);

		Label loc = new Label("�� ��� �Է�");
		loc.setBounds(20, 40, 200, 100);
		loc.setFont(locF);

		TextField text = new TextField(10);
		text.setBounds(20, 130, 250, 70);
		text.setFont(f);

		Button make = new Button("����");
		make.setBounds(20, 250, 100, 40);
		make.setFont(buttonF);
		make.setEnabled(false);

		Button esc = new Button("���");
		esc.setBounds(170, 250, 100, 40);
		esc.setFont(buttonF);

		frame.add(text);
		frame.add(make);
		frame.add(esc);
		frame.add(loc);

		frame.setVisible(true);

		//TextField �� ���� �� ��쿡�� �Է¹�ư Ȱ��ȭ
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

		//make ��ư Ŭ��
		make.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				gui_ischeck.setCheck(true); // 2020.04.23 �ڡڡ��߰����סڡڡ� ������ư Ŭ���� GUI_IsCheck�� �ִ� ���� true�� ��

				machineD = new DataEvent(fr);
				String s = text.getText();
				machineD.add_database(s);
				renew_label();
				frame.dispose();
			}
		});
		//x��ư ����
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.dispose();
			}
		});

		//esc��ư ����
		esc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}

	//================== ��Ͽ� ��� �߰� (����߰� ��ư Ŭ�� �� ����)=========================
	void add_location() {
		Font f = new Font("",Font.BOLD, 20);
		Frame frame = new Frame();
		frame.setLayout(null);
		frame.setBounds(400, 200, 450, 350);
		frame.setBackground(Color.LIGHT_GRAY);

		Label loc = new Label("�߰��� ��Ҹ� �Է� :");
		loc.setBounds(20, 40, 400, 100);
		loc.setFont(locF);

		TextField text = new TextField(10);
		text.setBounds(20, 130, 400, 70);
		text.setFont(f);

		Button make = new Button("�Ϸ�");
		make.setBounds(40, 250, 140, 40);
		make.setFont(buttonF);
		make.setEnabled(false);

		Button esc = new Button("���");
		esc.setFont(buttonF);
		esc.setBounds(260, 250, 140, 40);

		frame.add(text);
		frame.add(make);
		frame.add(esc);
		frame.add(loc);
		frame.setVisible(true);

		sequence = 0;
		location_distance = new int[machineD.getName_list().size()];
		//�Ϸ� ��ư Ŭ��
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
						loc.setText("<" + machineD.getName_list().get(sequence)+" & " +location_name+"> ������ �Ÿ� �Է�");
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
						loc.setText("<" + machineD.getName_list().get(sequence)+" & " +location_name+"> ������ �Ÿ� �Է�");
						sequence++;
					}
				}
				text.setText("");
			}
		});

		//TextField �� ���� �� ��쿡�� �Է¹�ư Ȱ��ȭ
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

		//x��ư ����
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.dispose();
			}
		});

		//esc��ư ����
		esc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();

			}
		});
	}

	//================ ��Ͽ��� ��� ����(��һ��� ��ư Ŭ�� �� ����) =============================
	void delete_location() {
		Font f = new Font("",Font.PLAIN, 20);
		Frame frame = new Frame();
		frame.setLayout(null);
		frame.setBounds(400, 200, 300, 350);
		frame.setBackground(Color.LIGHT_GRAY);

		Label loc = new Label("��Ͽ��� ������ ���");
		loc.setBounds(20, 40, 220, 100);
		loc.setFont(locF);

		TextField text = new TextField(10);
		text.setBounds(20, 130, 250, 70);
		text.setFont(f);

		Button make = new Button("����");
		make.setBounds(20, 250, 100, 40);
		make.setFont(buttonF);
		make.setEnabled(false);

		Button esc = new Button("���");
		esc.setFont(buttonF);
		esc.setBounds(170, 250, 100, 40);

		frame.add(text);
		frame.add(make);
		frame.add(esc);
		frame.add(loc);

		frame.setVisible(true);

		//TextField �� ���� �� ��쿡�� �Է¹�ư Ȱ��ȭ
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

		//make ��ư Ŭ��
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
		//x��ư ����
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.dispose();
			}
		});

		//esc��ư ����
		esc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}


	public static void main(String[] args) {	
		//��Ʈ�� ���ۺκ�
		intro it = new intro();
		//it.main(args);

		MainGUI m = new MainGUI();
		Frame f = new Frame("�ʱ� ȭ��");
		Font font = new Font("", Font.PLAIN, 20);
		m.machineD = new DataEvent(f);
		f.setLayout(null);
		f.setBounds(20, 20, 1000, 600);
		f.setBackground(Color.lightGray);

		//���� ���� �г�
		Panel mainPanel = new Panel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 1000, 530);
		mainPanel.setBackground(Color.white);

		mainPanel.setVisible(true);
		f.add(mainPanel);
		//���̺�
		//�߰��� ������
		Font labelFont = new Font("", Font.BOLD, 30);
		m.main_label = new TextArea("",0,0, TextArea.SCROLLBARS_NONE);
		m.main_label.setBounds(200, 170, 600, 320);
		m.main_label.setBackground(Color.lightGray);
		m.main_label.setFont(labelFont);
		m.main_label.setEditable(false);

		//���� ��� 
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

		//�ϴܺ� �߰� ��ư
		Panel bottomAdd = new Panel();
		bottomAdd.setLayout(null);
		bottomAdd.setFont(font);
		bottomAdd.setBackground(Color.gray);
		bottomAdd.setBounds(0, 525, 1000, 70);
		int buttonXbase = 75;
		int buttonYbase = 10;
		int gan= 180;


		Button create = new Button("�� ��� �����");
		create.setBounds(buttonXbase, buttonYbase, 130, 50);
		create.setFont(m.buttonF);

		Button load = new Button("�ҷ�����");
		load.setBounds(buttonXbase+200, buttonYbase, 100, 50);
		load.setFont(m.buttonF);

		Button addInfo = new Button("����߰�");
		addInfo.setBounds(buttonXbase+gan*2, buttonYbase, 100, 50);
		addInfo.setFont(m.buttonF);

		Button deleteInfo = new Button("��һ���");
		deleteInfo.setBounds(buttonXbase+gan*3, buttonYbase, 100, 50);
		deleteInfo.setFont(m.buttonF);

		Button start = new Button("��ã�� ����");
		start.setBounds(buttonXbase+gan*4, buttonYbase, 130, 50);
		start.setFont(m.buttonF);

		//���� ����� ��ư Ŭ��
		create.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("���� �����");
				m.create_new(f);

			}
		});

		//�ҷ����� ��ư Ŭ��
		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				m.machineD.load();
				m.gui_ischeck.setCheck(true);
				m.renew_label();
			}		
		});

		//����߰� ��ư Ŭ��
		addInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("����߰�");
				//m.add_location();

				// 2020.04.23 �ڡڡ� �߰����� �ڡڡ� 
				// ����߰� ��ư Ŭ����
				// GUI_IsCheck Ŭ������ �ִ� ischeck���� ���̸� ����߰� �������� ����
				// false�̸� �� ����� �����޶�� â ����
				if( m.gui_ischeck.getisCheck() == true ) { //����߰� ��ư�� �������� �� ��ϸ������ ���� ������ ���â
					m.add_location();

				}else {
					JOptionPane.showMessageDialog(f,"�� ����� ������ּ���!");

				}
			}
		});

		//��һ��� ��ư Ŭ��
		deleteInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("��һ���");
				m.delete_location();
			}
		});

		//��ã�� ���� ��ư Ŭ��
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("��ã�� ����");
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
		//�ϴܺ� �߰� ��ư

		//���θ���� �ҷ����� ���� �߰� ��ã�� ����

		f.add(bottomAdd);//�ϴ� �߰� �г�
		f.setVisible(true);

		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});





	}//main
}
