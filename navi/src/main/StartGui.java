package main;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import data.DataEvent;

public class StartGui {
	DataEvent de;
	int i;
	int cbi;
	

	public StartGui(DataEvent de) {
		this.de = de;
	}
	public void select_start() {

		Label loc = null;

		// ---------------------��ã�� ȭ��------------------------
		Frame f = new Frame("�� ã��");
		f.setBounds(100, 70, 800, 600);
		f.setVisible(true);
		f.setBackground(Color.GRAY);
		f.setLayout(null);

		// =======================��� ���� ���̺�====================
		Label title = new Label("              ��� ��ġ�� �������ּ���                                                                                                                                  ����        ������");
		title.setBackground(Color.WHITE);
		title.setBounds(60, 40, 680, 30);
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
		int locy = 80;
		
		
		Checkbox[] cbIndex = new Checkbox[locCnt];
		CheckboxGroup cbg = new CheckboxGroup();

		for (i = 0; i < locCnt; i++) {
			
			// ==========��ҷ��̺� �����=================
			loc = new Label(de.getSelect_name_list().get(i));
			loc.setBackground(Color.WHITE);
			loc.setBounds(60, locy, 550, 20);
			f.add(loc);


			// ----------���� ��ư �����-------------------

			cbIndex[i] = new Checkbox("", cbg, false);
			cbIndex[i].setBounds(700, locy, 30, 20);
			cbIndex[0].setState(true);
			f.add(cbIndex[i]);

			locy += 30;

		} // for

		// ========================���� ��ư �����=============================
		Button btnNext = new Button("����");
		btnNext.setBounds(720, 540, 30, 30);
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
				de.run_tsp();
				//�߰��� �� : �������� �Ѿ�� �Լ� ȣ��
			}
		});// btnNext �̺�Ʈ ó��
	}// start()
	public void start() {

		Label loc = null;

		// ---------------------��ã�� ȭ��------------------------
		Frame f = new Frame("�� ã��");
		f.setBounds(100, 70, 800, 600);
		f.setVisible(true);
		f.setBackground(Color.GRAY);
		f.setLayout(null);

		// =======================��� ���� ���̺�====================
		Label title = new Label("              �湮�� ��Ҹ� ������ �ּ���                                                                                                                                  ����        ������");
		title.setBackground(Color.WHITE);
		title.setBounds(60, 40, 680, 30);
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
		int locy = 80;
		
		int[] cb = new int[locCnt]; // üũ�ڽ� �� ���� ���� ���� �迭
		int[] cbi = new int[1]; // ���� ��ư �ε��� ���� �迭
		
		Checkbox[] ckb_arr = new Checkbox[locCnt];
		Checkbox[] cbIndex = new Checkbox[locCnt];
		CheckboxGroup cbg = new CheckboxGroup();

		for (i = 0; i < locCnt; i++) {
			// ==========��ҷ��̺� �����=================
			loc = new Label(de.getName_list().get(i));
			loc.setBackground(Color.WHITE);
			loc.setBounds(60, locy, 550, 20);
			f.add(loc);

			// ----------üũ�ڽ� �����---------------

			ckb_arr[i] = new Checkbox();
			ckb_arr[i].setBounds(650, locy, 30, 20);
			f.add(ckb_arr[i]);

			// ----------���� ��ư �����-------------------

			cbIndex[i] = new Checkbox("", cbg, false);
			cbIndex[i].setBounds(700, locy, 30, 20);
			f.add(cbIndex[i]);

			locy += 30;

		} // for

		// ========================���� ��ư �����=============================
		Button btnNext = new Button("����");
		btnNext.setBounds(720, 540, 30, 30);
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
					System.out.println(cb[i]);
				}

				for (int i = 0; i < locCnt; i++) {
					if (cbIndex[i].getState() == true) {
						cbi[0] = i;
						//System.out.println(cbi[0]);
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
