package login;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.GrayFilter;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import gui.MainGUI;
import intro.Intro;

// # 로그인창 출력

public class Travel_Start {

	public static void main(String[] args) {
		//인트로
		Intro it = new Intro();
		it.show_intro();
		MainGUI maingui = new MainGUI();


		Travel_Info travel_info = new Travel_Info();
		Travel_Check travel_check = new Travel_Check();
		

		//-------------- 프레임 생성 --------------------------------		
		// 로그인 프레임
		Frame frame = new Frame("로그인");
		frame.setBounds(300, 300, 430, 250);
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setLayout(null);

		// 회원가입 프레임
		Frame frame2 = new Frame("회원가입");
		frame2.setBounds(500, 500, 430, 250);
		frame2.setBackground(Color.LIGHT_GRAY);
		frame2.setLayout(null);
		Font font = new Font("굴림",Font.BOLD,17);
		//-------------------------------------------------------


		//--------------------로그인 프레임---------------------------		
		// ID , PWD 표시 레이블
		int idY = 80;
		int pwY = 130;

		Label lid = new Label("ID");
		lid.setBounds(30,idY,30,40);
		lid.setFont(font);
		Label lpwd = new Label("PWD");
		lpwd.setBounds(30,pwY,30,40);
		lpwd.setFont(font);

		// ID, PWD 입력 TextField
		TextField tf_id = new TextField();
		tf_id.setBounds(80,idY,200,25);
		JPasswordField tf_pwd = new JPasswordField();
		tf_pwd.setBounds(80,pwY,200,25);

		// PWD를 확인하는 체크박스
		Checkbox checkbox = new  Checkbox("비밀번호 확인");
		checkbox.setBounds(40, 30, 100, 60);
		Label checkpwd = new Label(); // 비밀번호 확인 레이블
		checkpwd.setBounds(150, 30, 100, 60);
		checkbox.addItemListener( new ItemListener() {

			@Override // 체크박스를 클릭하면  아이디 표시
			public void itemStateChanged(ItemEvent e) {
				String str = tf_pwd.getText();
				if(e.getStateChange() == 1) {
					checkpwd.setText(str);
				}else {
					checkpwd.setText("");
				}


			}
		} );
		//

		// 로그인버튼
		Button btnlog = new Button("로그인");
		btnlog.setBounds(300,idY,80,80);

		// 회원가입 버튼
		Button btnnew = new Button("회원가입");
		btnnew.setBounds(300,pwY+50,80,30);

		// 비어있는 ID, PWD를 표시해주는 레이블
		Label none_idnpass = new Label();
		none_idnpass.setBounds(80,pwY+25,220,80);
		none_idnpass.setFont(font);
		//--------------------------------------------------------


		//-----------------------회원가입 프레임-------------------------
		// 회원가입 - 아이디,패스워드 Textfield
		TextField tf_nid = new TextField();
		tf_nid.setBounds(80,idY,200,25);
		TextField tf_npwd = new TextField();
		tf_npwd.setBounds(80,pwY,200,25);

		// 회원가입 - ID,PWD 표시 레이블
		Label lid2 = new Label("ID");
		lid2.setBounds(30,idY,30,40);
		lid2.setFont(font);
		Label lpwd2 = new Label("PWD");
		lpwd2.setBounds(30,pwY,30,40);
		lpwd2.setFont(font);

		// 아이디 중복 표시 레이블
		Label lidCheck = new Label();
		lidCheck.setBounds(20,pwY+25,300,80);
		lidCheck.setFont(font);

		// 회원가입 - 아이디 중복확인 버튼
		Button btnidCheck = new Button("아이디 중복확인");
		btnidCheck.setBounds(300,idY,100,80);

		// 회원가입 - 가입하기 버튼
		Button btnMember = new Button("가입하기");
		btnMember.setBounds(300,pwY+50,100,30);
		btnMember.setEnabled(false);

		//-------------------------------------------------------		

		//--------------------로그인 프레임---------------------------		
		// 로그인 - Login 버튼 클릭
		btnlog.addActionListener( new ActionListener() {
			String id = "";
			String pwd = "";

			@Override // 아이디 또는 비밀번호가 빈값일때 레이블 출력
			public void actionPerformed(ActionEvent e) {
				if(tf_id.getText().trim().equals("")) {
					none_idnpass.setText("아이디를 입력해주세요.");
				}else if(tf_pwd.getText().trim().equals("")) {
					none_idnpass.setText("비밀번호를 입력해주세요.");
				}else {
					
					// Travel_info클래스로 아이디,비밀번호 넘김
					travel_info.setId( tf_id.getText() );
					travel_info.setPassword( tf_pwd.getText() );
					String path = "info/" + travel_info.getId();
					File f = new File(path);
					System.out.println(f);
					
					// 입력한 아이디의 폴더가 없으면 출력
					if(!f.exists()) {
						none_idnpass.setText("가입하지않은 아이디");
						tf_id.setText("");
						btnnew.setBackground(Color.YELLOW);

					}else {
						//로드하기 
						Travel_Loader travel_loader = new Travel_Loader(travel_info);
						id = travel_loader.getInfo().getId();
						pwd = travel_loader.getInfo().getPassword();

						if(tf_id.getText().equals(id) && tf_pwd.getText().equals(pwd)) {
							none_idnpass.setText(tf_id.getText() + "님 환영합니다");
							frame.dispose();
							maingui.run_main_gui();


						}else {
							none_idnpass.setText("비밀번호 틀림");
						}

					}


				}

			}
		} );


		// 로그인 - 회원가입 버튼
		btnnew.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame2.setVisible(true);

			}
		} );

		//---------------------------------------------------------------------------------

		//--------------------------------회원가입 프레임-----------------------------------------

		// 회원가입 - ID 중복확인 버튼
		btnidCheck.addActionListener( new ActionListener() {

			@Override // Info 클래스에 값을 넘긴 후 중복확인 비교
			public void actionPerformed(ActionEvent e) {
				travel_info.setId( tf_nid.getText() ); 
				travel_info.setPassword( tf_npwd.getText() );

				String path = "info/" + travel_info.getId();
				File f = new File(path);

				if(tf_nid.getText().trim().equals("")) {
					lidCheck.setText("아이디를 입력해주세요!");
				}

				if(!f.exists()) { 
					lidCheck.setText("가입이 가능한 아이디 입니다!");   
					travel_check.setCheck(true); 
					//TextField 갱신 
					tf_npwd.setText(tf_npwd.getText()+"");

				}else if(f.exists() && !tf_nid.getText().trim().equals("")){ 
					lidCheck.setText("중복된 아이디 입니다!"); 
					tf_nid.setText("");					 
					tf_nid.requestFocus(); 				
					travel_check.setCheck(false); 		
					//TextField 갱신
					tf_npwd.setText(""); 

				}else if(tf_nid.getText().trim().equals("")) { 
					lidCheck.setText("아이디를 입력해주세요!");
				}
			}
		});

		// 회원가입 - ID 텍스트필드에 값이 추가되거나 삭제가되면 가입하기 버튼 비활성화
		tf_nid.addTextListener( new TextListener() {

			@Override
			public void textValueChanged(TextEvent e) {
				if( tf_nid.getText() != null ) {
					travel_check.setCheck(false); 
					btnMember.setEnabled(false); 
				}

			}
		} );

		// 회원가입 - 가입하기 버튼 활성화 (조건)
		tf_npwd.addTextListener( new TextListener() {

			@Override
			public void textValueChanged(TextEvent e) {
				//Travel_Check 클래스에 check값이 true이면서 ID-TextField가 빈값이 아니라면
				if( travel_check.isCheck() == true && !tf_npwd.getText().trim().equals(""))  { 
					System.out.println("활성화");
					btnMember.setEnabled(true); //가입하기 버튼 활성화
					btnMember.setBackground(Color.YELLOW);
				}else {
					btnMember.setEnabled(false); // 버튼 비활성화
				}

			}
		} );

		// 회원가입 - 가입하기 버튼
		btnMember.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				travel_info.setId(tf_nid.getText());
				travel_info.setPassword(tf_npwd.getText());

				new Travel_Writer(travel_info);
				// 회원가입하기버튼 클릭 후 폴더(정보ID,PWD)가 만들어지면 확인해서 있으면 회원가입 성공 없으면 실패 
				String path = "info/" + travel_info.getId();
				File f = new File(path);
				if( !f.exists() ) {
					JOptionPane.showMessageDialog(frame2,"회원가입 실패!");
				}else {
					JOptionPane.showMessageDialog(frame2,"회원가입 성공!");
					tf_nid.setText("");
					tf_npwd.setText("");
					frame2.dispose();
				}
			}
		} );
		//--------------------------------------------------------------------------------------------		

		frame.add(tf_id); 		// 아이디창
		frame.add(tf_pwd); // 비밀번호창
		frame.add(checkbox); // 비밀번호확인 체크박스
		frame.add(checkpwd);
		frame.add(lid);  //아이디레이블
		frame.add(lpwd); //패스워드 레이블
		frame.add(none_idnpass); // 로그인,패스워드 빈값일때 나오는 레이블

		frame.add(btnlog); // 로그인버튼
		frame.add(btnnew); //회원가입 버튼

		frame2.add(tf_nid);     // 회원가입 - ID(TextField)
		frame2.add(tf_npwd);    // 회원가입 - PWD(TextField)
		frame2.add(btnidCheck); // 회원가입 - IdCheck 버튼
		frame2.add(lid2); 		// 회원가입 - ID 레이블
		frame2.add(lpwd2); 		// 회원가입 - PWD 레이블
		frame2.add(btnMember); 	// 회원가입 - 회원가입하기 버튼
		frame2.add(lidCheck); 	// 회원가입 - ID 중복인지 아닌지 레이블


		frame.setVisible(true);
		frame.addWindowListener( new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);

			};

		} );

		frame2.addWindowListener( new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				frame2.dispose();

			};

		} );


	}//main

}



























