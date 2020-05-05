package gui;

// # 길찾기 시작을 눌렀을 시 보여줄 GUI 클래스
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
	int at[];;	//도착 시간
	int at_type[];	//0 : 도착 시간 이전에 도착, 1: 도착 시간에 맞춰서 도착
	int lt[];

	//공용폰트
		Font locF = new Font("",Font.BOLD, 22);
		Font buttonF = new Font("", Font.BOLD, 20);


	public StartGUI(DataEvent de) {
		this.de = de;
	}
	//테스트용
	public void test(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
		}
		System.out.println();
	}

	//step3 : 시간준수 / 소요시간설정
	public void select_time_promise() {

		Label loc = null;
		

		Frame f = new Frame("시간 준수");
		f.setBounds(100, 70, 450, 800);
		f.setVisible(true);
		f.setBackground(Color.GRAY);
		f.setLayout(null);

		// =======================상단 제목 레이블====================
		Label title = new Label(" 머물 시간을 정해주세요     ");
		title.setFont(locF);
		title.setBackground(Color.WHITE);
		title.setBounds(30, 40, 400, 50);
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
		int locy = 100;

		int[] cb = new int[locCnt]; // 체크박스 참 거짓 담을 정수 배열
		int[] lt = new int[locCnt];
		for(int i = 0; i < lt.length; i++) {
			lt[i] = 0;
		}
		

		TextField[] tb_arr = new TextField[locCnt];

		//getSelectName_list();
		for (i = 0; i < locCnt; i++) {
			// ==========장소레이블 만들기=================
			loc = new Label(de.getSelect_name_list().get(i));
			loc.setFont(buttonF);
			loc.setBackground(Color.WHITE);
			loc.setBounds(30, locy, 210, 40);
			f.add(loc);
			
			// ==========텍스트박스 만들기=================
			tb_arr[i] = new TextField(10);
			tb_arr[i].setBounds(260, locy, 170, 40);
			tb_arr[i].setFont(locF);
			f.add(tb_arr[i]);

			locy += 50;


		}//for

		// ========================다음 버튼 만들기=============================
		Button btnNext = new Button("다음");
		btnNext.setBounds(360, 700, 60, 60);
		btnNext.setFont(buttonF);
		btnNext.setVisible(true);
		f.add(btnNext);


		// 다음 버튼 클릭시 이벤트 처리
		btnNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < locCnt; i++) {
					lt[i] = Integer.parseInt(tb_arr[i].getText());

				}
				
				f.dispose();
				de.run_tsp(lt);
				
				//추가할 것 : 다음으로 넘어가는 함수 호출
			}
		});// btnNext 이벤트 처리



	}




	//=======================step2 : 시작 장소 선택=================================
	public void select_start() {
		Label loc = null;

		// ---------------------길찾기 화면------------------------
		Frame f = new Frame("길 찾기");
		f.setBounds(100, 70, 450, 800);
		f.setVisible(true);
		f.setBackground(Color.GRAY);
		f.setLayout(null);

		// =======================상단 제목 레이블====================
		Label title = new Label(" 출발 위치를 선택해주세요          선택     ");
		title.setFont(locF);
		title.setBackground(Color.WHITE);
		title.setBounds(30, 40, 400, 50);
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
		int locy = 100;


		Checkbox[] cbIndex = new Checkbox[locCnt];
		CheckboxGroup cbg = new CheckboxGroup();

		for (i = 0; i < locCnt; i++) {

			// ==========장소레이블 만들기=================
			loc = new Label(de.getSelect_name_list().get(i));
			loc.setFont(buttonF);
			loc.setBackground(Color.WHITE);
			loc.setBounds(30, locy, 310, 40);
			f.add(loc);


			// ----------라디오 버튼 만들기-------------------

			cbIndex[i] = new Checkbox("", cbg, false);
			cbIndex[i].setBounds(380, locy, 40, 40);
			cbIndex[0].setState(true);
			f.add(cbIndex[i]);

			locy += 50;

		} // for

		// ========================다음 버튼 만들기=============================
		Button btnNext = new Button("다음");
		btnNext.setBounds(360, 700, 60, 60);
		btnNext.setFont(buttonF);
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
				f.dispose();
				select_time_promise();
				//de.run_tsp();
				//추가할 것 : 다음으로 넘어가는 함수 호출
			}
		});// btnNext 이벤트 처리
	}// start()

	//====================================step1 : 장소 선택
	public void start() {

		Label loc = null;

		// ---------------------길찾기 화면------------------------
		Frame f = new Frame("길 찾기");
		f.setBounds(100, 70, 450, 800);
		f.setVisible(true);
		f.setBackground(Color.GRAY);
		f.setLayout(null);

		// =======================상단 제목 레이블====================
		Label title = new Label("방문할 장소를 선택하세요        선택 ");
		title.setFont(locF);
		title.setBackground(Color.WHITE);
		title.setBounds(30, 40, 390, 50);
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
		int locy = 100;

		int[] cb = new int[locCnt]; // 체크박스 참 거짓 담을 정수 배열

		Checkbox[] ckb_arr = new Checkbox[locCnt];


		for (i = 0; i < locCnt; i++) {
			// ==========장소레이블 만들기=================
			loc = new Label(de.getName_list().get(i));
			loc.setFont(buttonF);
			loc.setBackground(Color.WHITE);
			loc.setBounds(30, locy, 280, 40);
			f.add(loc);

			// ----------체크박스 만들기---------------

			ckb_arr[i] = new Checkbox();
			ckb_arr[i].setBounds(360, locy, 40, 40);
			f.add(ckb_arr[i]);


			locy += 50;

		} // for

		// ========================다음 버튼 만들기=============================
		Button btnNext = new Button("다음");
		btnNext.setBounds(360, 700, 60, 60);
		btnNext.setFont(buttonF);
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
				}


				de.get_select(cb);
				f.dispose();
				select_start();
				//추가할 것 : 다음으로 넘어가는 함수 호출
			}
		});// btnNext 이벤트 처리
	}// start()

}
