package gui;

// # ��ã�� ������ ������ �� ������ GUI Ŭ����
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import data.DataEvent;
import tsp.Node;

public class StartGUI {
	DataEvent de;
	int i;
	int cbi;
	int at[];;	//���� �ð�
	int at_type[];	//0 : ���� �ð� ������ ����, 1: ���� �ð��� ���缭 ����
	int lt[];

	//������Ʈ
		Font locF = new Font("",Font.BOLD, 22);
		Font buttonF = new Font("", Font.BOLD, 20);


	public StartGUI(DataEvent de) {
		this.de = de;
	}
	//�׽�Ʈ��
	public void test(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
		}
		System.out.println();
	}

	//step3 : �ð��ؼ� / �ҿ�ð�����
	public void select_time_promise() {

		Label loc = null;
		

		Frame f = new Frame("�ð� �ؼ�");
		f.setBounds(100, 70, 450, 800);
		f.setVisible(true);
		f.setBackground(Color.GRAY);
		f.setLayout(null);

		// =======================��� ���� ���̺�====================
		Label title = new Label(" �ӹ� �ð��� �����ּ���     ");
		title.setFont(locF);
		title.setBackground(Color.WHITE);
		title.setBounds(30, 40, 400, 50);
		title.setVisible(true);
		f.add(title);

		// ��� ���� ��ư
		f.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				f.dispose();
			}

		});

		// --------------��� ���̺� �߰��ϱ�(�ִ� ũ�� 15)--------------------------
		int locCnt = de.getSelect_name_list().size();
		int locy = 100;

		int[] cb = new int[locCnt]; // üũ�ڽ� �� ���� ���� ���� �迭
		int[] lt = new int[locCnt];
		for(int i = 0; i < lt.length; i++) {
			lt[i] = 0;
		}
		

		TextField[] tb_arr = new TextField[locCnt];

		//getSelectName_list();
		for (i = 0; i < locCnt; i++) {
			// ==========��ҷ��̺� �����=================
			loc = new Label(de.getSelect_name_list().get(i));
			loc.setFont(buttonF);
			loc.setBackground(Color.WHITE);
			loc.setBounds(30, locy, 210, 40);
			f.add(loc);
			
			// ==========�ؽ�Ʈ�ڽ� �����=================
			tb_arr[i] = new TextField(10);
			tb_arr[i].setBounds(260, locy, 170, 40);
			tb_arr[i].setFont(locF);
			f.add(tb_arr[i]);

			locy += 50;


		}//for

		// ========================���� ��ư �����=============================
		Button btnNext = new Button("����");
		btnNext.setBounds(360, 700, 60, 60);
		btnNext.setFont(buttonF);
		btnNext.setVisible(true);
		f.add(btnNext);


		// ���� ��ư Ŭ���� �̺�Ʈ ó��
		btnNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < locCnt; i++) {
					lt[i] = Integer.parseInt(tb_arr[i].getText());

				}
				
				f.dispose();
				de.run_tsp(lt);
				
				//�߰��� �� : �������� �Ѿ�� �Լ� ȣ��
			}
		});// btnNext �̺�Ʈ ó��



	}




	//=======================step2 : ���� ��� ����=================================
	public void select_start() {
		Label loc = null;

		// ---------------------��ã�� ȭ��------------------------
		Frame f = new Frame("�� ã��");
		f.setBounds(100, 70, 450, 800);
		f.setVisible(true);
		f.setBackground(Color.GRAY);
		f.setLayout(null);

		// =======================��� ���� ���̺�====================
		Label title = new Label(" ��� ��ġ�� �������ּ���          ����     ");
		title.setFont(locF);
		title.setBackground(Color.WHITE);
		title.setBounds(30, 40, 400, 50);
		title.setVisible(true);
		f.add(title);

		// ��� ���� ��ư
		f.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				f.dispose();
			}

		});

		// --------------��� ���̺� �߰��ϱ�(�ִ� ũ�� 15)--------------------------
		int locCnt = de.getSelect_name_list().size();
		int locy = 100;


		Checkbox[] cbIndex = new Checkbox[locCnt];
		CheckboxGroup cbg = new CheckboxGroup();

		for (i = 0; i < locCnt; i++) {

			// ==========��ҷ��̺� �����=================
			loc = new Label(de.getSelect_name_list().get(i));
			loc.setFont(buttonF);
			loc.setBackground(Color.WHITE);
			loc.setBounds(30, locy, 310, 40);
			f.add(loc);


			// ----------���� ��ư �����-------------------

			cbIndex[i] = new Checkbox("", cbg, false);
			cbIndex[i].setBounds(380, locy, 40, 40);
			cbIndex[0].setState(true);
			f.add(cbIndex[i]);

			locy += 50;

		} // for

		// ========================���� ��ư �����=============================
		Button btnNext = new Button("����");
		btnNext.setBounds(360, 700, 60, 60);
		btnNext.setFont(buttonF);
		btnNext.setVisible(true);
		f.add(btnNext);

		// ���� ��ư Ŭ���� �̺�Ʈ ó��
		btnNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < locCnt; i++) {
					if (cbIndex[i].getState() == true) {
						cbi = i;
						//System.out.println(cbi[0]);
					}
				}
				de.setStartPoint(cbi);
				f.dispose();
				select_time_promise();
				//de.run_tsp();
				//�߰��� �� : �������� �Ѿ�� �Լ� ȣ��
			}
		});// btnNext �̺�Ʈ ó��
	}// start()

	//====================================step1 : ��� ����
	public void start() {

		Label loc = null;

		// ---------------------��ã�� ȭ��------------------------
		Frame f = new Frame("�� ã��");
		f.setBounds(100, 70, 450, 800);
		f.setVisible(true);
		f.setBackground(Color.GRAY);
		f.setLayout(null);

		// =======================��� ���� ���̺�====================
		Label title = new Label("�湮�� ��Ҹ� �����ϼ���        ���� ");
		title.setFont(locF);
		title.setBackground(Color.WHITE);
		title.setBounds(30, 40, 390, 50);
		title.setVisible(true);
		f.add(title);

		// ��� ���� ��ư
		f.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				f.dispose();
			}

		});

		// --------------��� ���̺� �߰��ϱ�(�ִ� ũ�� 15)--------------------------
		int locCnt = de.getName_list().size();
		int locy = 100;

		int[] cb = new int[locCnt]; // üũ�ڽ� �� ���� ���� ���� �迭

		Checkbox[] ckb_arr = new Checkbox[locCnt];


		for (i = 0; i < locCnt; i++) {
			// ==========��ҷ��̺� �����=================
			loc = new Label(de.getName_list().get(i));
			loc.setFont(buttonF);
			loc.setBackground(Color.WHITE);
			loc.setBounds(30, locy, 280, 40);
			f.add(loc);

			// ----------üũ�ڽ� �����---------------

			ckb_arr[i] = new Checkbox();
			ckb_arr[i].setBounds(360, locy, 40, 40);
			f.add(ckb_arr[i]);


			locy += 50;

		} // for

		// ========================���� ��ư �����=============================
		Button btnNext = new Button("����");
		btnNext.setBounds(360, 700, 60, 60);
		btnNext.setFont(buttonF);
		btnNext.setVisible(true);
		f.add(btnNext);

		// ���� ��ư Ŭ���� �̺�Ʈ ó��
		btnNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// ---------------üũ�ڽ� ���� ���� üũ ����-------------------
				for (int i = 0; i < locCnt; i++) {
					System.out.println(ckb_arr[i].getState());
				}

				for (int i = 0; i < locCnt; i++) {
					if (ckb_arr[i].getState() == true) {
						cb[i] = 1;
					} else {
						cb[i] = 0;
					}
				}


				de.get_select(cb);
				f.dispose();
				select_start();
				//�߰��� �� : �������� �Ѿ�� �Լ� ȣ��
			}
		});// btnNext �̺�Ʈ ó��
	}// start()

}
