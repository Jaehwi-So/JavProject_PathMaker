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

		// ---------------------길찾기 화면------------------------
		Frame f = new Frame("길 찾기");
		f.setBounds(100, 70, 800, 600);
		f.setVisible(true);
		f.setBackground(Color.GRAY);
		f.setLayout(null);

		// =======================상단 제목 레이블====================
		Label title = new Label("              출발 위치를 선택해주세요                                                                                                                                  선택        시작점");
		title.setBackground(Color.WHITE);
		title.setBounds(60, 40, 680, 30);
		title.setVisible(true);
		f.add(title);

		// 상단 종료 버튼
		f.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				f.dispose();
			}

		});

		// --------------장소 레이블 추가하기(최대 크기 15)--------------------------
		int locCnt = de.getSelect_name_list().size();
		int locy = 80;
		
		
		Checkbox[] cbIndex = new Checkbox[locCnt];
		CheckboxGroup cbg = new CheckboxGroup();

		for (i = 0; i < locCnt; i++) {
			
			// ==========장소레이블 만들기=================
			loc = new Label(de.getSelect_name_list().get(i));
			loc.setBackground(Color.WHITE);
			loc.setBounds(60, locy, 550, 20);
			f.add(loc);


			// ----------라디오 버튼 만들기-------------------

			cbIndex[i] = new Checkbox("", cbg, false);
			cbIndex[i].setBounds(700, locy, 30, 20);
			cbIndex[0].setState(true);
			f.add(cbIndex[i]);

			locy += 30;

		} // for

		// ========================다음 버튼 만들기=============================
		Button btnNext = new Button("다음");
		btnNext.setBounds(720, 540, 30, 30);
		btnNext.setVisible(true);
		f.add(btnNext);

		// 다음 버튼 클릭시 이벤트 처리
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
				//추가할 것 : 다음으로 넘어가는 함수 호출
			}
		});// btnNext 이벤트 처리
	}// start()
	public void start() {

		Label loc = null;

		// ---------------------길찾기 화면------------------------
		Frame f = new Frame("길 찾기");
		f.setBounds(100, 70, 800, 600);
		f.setVisible(true);
		f.setBackground(Color.GRAY);
		f.setLayout(null);

		// =======================상단 제목 레이블====================
		Label title = new Label("              방문할 장소를 선택해 주세요                                                                                                                                  선택        시작점");
		title.setBackground(Color.WHITE);
		title.setBounds(60, 40, 680, 30);
		title.setVisible(true);
		f.add(title);

		// 상단 종료 버튼
		f.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				f.dispose();
			}

		});

		// --------------장소 레이블 추가하기(최대 크기 15)--------------------------
		int locCnt = de.getName_list().size();
		int locy = 80;
		
		int[] cb = new int[locCnt]; // 체크박스 참 거짓 담을 정수 배열
		int[] cbi = new int[1]; // 라디오 버튼 인덱스 담을 배열
		
		Checkbox[] ckb_arr = new Checkbox[locCnt];
		Checkbox[] cbIndex = new Checkbox[locCnt];
		CheckboxGroup cbg = new CheckboxGroup();

		for (i = 0; i < locCnt; i++) {
			// ==========장소레이블 만들기=================
			loc = new Label(de.getName_list().get(i));
			loc.setBackground(Color.WHITE);
			loc.setBounds(60, locy, 550, 20);
			f.add(loc);

			// ----------체크박스 만들기---------------

			ckb_arr[i] = new Checkbox();
			ckb_arr[i].setBounds(650, locy, 30, 20);
			f.add(ckb_arr[i]);

			// ----------라디오 버튼 만들기-------------------

			cbIndex[i] = new Checkbox("", cbg, false);
			cbIndex[i].setBounds(700, locy, 30, 20);
			f.add(cbIndex[i]);

			locy += 30;

		} // for

		// ========================다음 버튼 만들기=============================
		Button btnNext = new Button("다음");
		btnNext.setBounds(720, 540, 30, 30);
		btnNext.setVisible(true);
		f.add(btnNext);

		// 다음 버튼 클릭시 이벤트 처리
		btnNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// ---------------체크박스 선택 해제 체크 포문-------------------
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
				//추가할 것 : 다음으로 넘어가는 함수 호출
			}
		});// btnNext 이벤트 처리
	}// start()

}
