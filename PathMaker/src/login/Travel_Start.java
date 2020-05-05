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

// # �α���â ���

public class Travel_Start {

	public static void main(String[] args) {
		//��Ʈ��
		Intro it = new Intro();
		it.show_intro();
		MainGUI maingui = new MainGUI();


		Travel_Info travel_info = new Travel_Info();
		Travel_Check travel_check = new Travel_Check();
		

		//-------------- ������ ���� --------------------------------		
		// �α��� ������
		Frame frame = new Frame("�α���");
		frame.setBounds(300, 300, 430, 250);
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setLayout(null);

		// ȸ������ ������
		Frame frame2 = new Frame("ȸ������");
		frame2.setBounds(500, 500, 430, 250);
		frame2.setBackground(Color.LIGHT_GRAY);
		frame2.setLayout(null);
		Font font = new Font("����",Font.BOLD,17);
		//-------------------------------------------------------


		//--------------------�α��� ������---------------------------		
		// ID , PWD ǥ�� ���̺�
		int idY = 80;
		int pwY = 130;

		Label lid = new Label("ID");
		lid.setBounds(30,idY,30,40);
		lid.setFont(font);
		Label lpwd = new Label("PWD");
		lpwd.setBounds(30,pwY,30,40);
		lpwd.setFont(font);

		// ID, PWD �Է� TextField
		TextField tf_id = new TextField();
		tf_id.setBounds(80,idY,200,25);
		JPasswordField tf_pwd = new JPasswordField();
		tf_pwd.setBounds(80,pwY,200,25);

		// PWD�� Ȯ���ϴ� üũ�ڽ�
		Checkbox checkbox = new  Checkbox("��й�ȣ Ȯ��");
		checkbox.setBounds(40, 30, 100, 60);
		Label checkpwd = new Label(); // ��й�ȣ Ȯ�� ���̺�
		checkpwd.setBounds(150, 30, 100, 60);
		checkbox.addItemListener( new ItemListener() {

			@Override // üũ�ڽ��� Ŭ���ϸ�  ���̵� ǥ��
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

		// �α��ι�ư
		Button btnlog = new Button("�α���");
		btnlog.setBounds(300,idY,80,80);

		// ȸ������ ��ư
		Button btnnew = new Button("ȸ������");
		btnnew.setBounds(300,pwY+50,80,30);

		// ����ִ� ID, PWD�� ǥ�����ִ� ���̺�
		Label none_idnpass = new Label();
		none_idnpass.setBounds(80,pwY+25,220,80);
		none_idnpass.setFont(font);
		//--------------------------------------------------------


		//-----------------------ȸ������ ������-------------------------
		// ȸ������ - ���̵�,�н����� Textfield
		TextField tf_nid = new TextField();
		tf_nid.setBounds(80,idY,200,25);
		TextField tf_npwd = new TextField();
		tf_npwd.setBounds(80,pwY,200,25);

		// ȸ������ - ID,PWD ǥ�� ���̺�
		Label lid2 = new Label("ID");
		lid2.setBounds(30,idY,30,40);
		lid2.setFont(font);
		Label lpwd2 = new Label("PWD");
		lpwd2.setBounds(30,pwY,30,40);
		lpwd2.setFont(font);

		// ���̵� �ߺ� ǥ�� ���̺�
		Label lidCheck = new Label();
		lidCheck.setBounds(20,pwY+25,300,80);
		lidCheck.setFont(font);

		// ȸ������ - ���̵� �ߺ�Ȯ�� ��ư
		Button btnidCheck = new Button("���̵� �ߺ�Ȯ��");
		btnidCheck.setBounds(300,idY,100,80);

		// ȸ������ - �����ϱ� ��ư
		Button btnMember = new Button("�����ϱ�");
		btnMember.setBounds(300,pwY+50,100,30);
		btnMember.setEnabled(false);

		//-------------------------------------------------------		

		//--------------------�α��� ������---------------------------		
		// �α��� - Login ��ư Ŭ��
		btnlog.addActionListener( new ActionListener() {
			String id = "";
			String pwd = "";

			@Override // ���̵� �Ǵ� ��й�ȣ�� ���϶� ���̺� ���
			public void actionPerformed(ActionEvent e) {
				if(tf_id.getText().trim().equals("")) {
					none_idnpass.setText("���̵� �Է����ּ���.");
				}else if(tf_pwd.getText().trim().equals("")) {
					none_idnpass.setText("��й�ȣ�� �Է����ּ���.");
				}else {
					
					// Travel_infoŬ������ ���̵�,��й�ȣ �ѱ�
					travel_info.setId( tf_id.getText() );
					travel_info.setPassword( tf_pwd.getText() );
					String path = "info/" + travel_info.getId();
					File f = new File(path);
					System.out.println(f);
					
					// �Է��� ���̵��� ������ ������ ���
					if(!f.exists()) {
						none_idnpass.setText("������������ ���̵�");
						tf_id.setText("");
						btnnew.setBackground(Color.YELLOW);

					}else {
						//�ε��ϱ� 
						Travel_Loader travel_loader = new Travel_Loader(travel_info);
						id = travel_loader.getInfo().getId();
						pwd = travel_loader.getInfo().getPassword();

						if(tf_id.getText().equals(id) && tf_pwd.getText().equals(pwd)) {
							none_idnpass.setText(tf_id.getText() + "�� ȯ���մϴ�");
							frame.dispose();
							maingui.run_main_gui();


						}else {
							none_idnpass.setText("��й�ȣ Ʋ��");
						}

					}


				}

			}
		} );


		// �α��� - ȸ������ ��ư
		btnnew.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame2.setVisible(true);

			}
		} );

		//---------------------------------------------------------------------------------

		//--------------------------------ȸ������ ������-----------------------------------------

		// ȸ������ - ID �ߺ�Ȯ�� ��ư
		btnidCheck.addActionListener( new ActionListener() {

			@Override // Info Ŭ������ ���� �ѱ� �� �ߺ�Ȯ�� ��
			public void actionPerformed(ActionEvent e) {
				travel_info.setId( tf_nid.getText() ); 
				travel_info.setPassword( tf_npwd.getText() );

				String path = "info/" + travel_info.getId();
				File f = new File(path);

				if(tf_nid.getText().trim().equals("")) {
					lidCheck.setText("���̵� �Է����ּ���!");
				}

				if(!f.exists()) { 
					lidCheck.setText("������ ������ ���̵� �Դϴ�!");   
					travel_check.setCheck(true); 
					//TextField ���� 
					tf_npwd.setText(tf_npwd.getText()+"");

				}else if(f.exists() && !tf_nid.getText().trim().equals("")){ 
					lidCheck.setText("�ߺ��� ���̵� �Դϴ�!"); 
					tf_nid.setText("");					 
					tf_nid.requestFocus(); 				
					travel_check.setCheck(false); 		
					//TextField ����
					tf_npwd.setText(""); 

				}else if(tf_nid.getText().trim().equals("")) { 
					lidCheck.setText("���̵� �Է����ּ���!");
				}
			}
		});

		// ȸ������ - ID �ؽ�Ʈ�ʵ忡 ���� �߰��ǰų� �������Ǹ� �����ϱ� ��ư ��Ȱ��ȭ
		tf_nid.addTextListener( new TextListener() {

			@Override
			public void textValueChanged(TextEvent e) {
				if( tf_nid.getText() != null ) {
					travel_check.setCheck(false); 
					btnMember.setEnabled(false); 
				}

			}
		} );

		// ȸ������ - �����ϱ� ��ư Ȱ��ȭ (����)
		tf_npwd.addTextListener( new TextListener() {

			@Override
			public void textValueChanged(TextEvent e) {
				//Travel_Check Ŭ������ check���� true�̸鼭 ID-TextField�� ���� �ƴ϶��
				if( travel_check.isCheck() == true && !tf_npwd.getText().trim().equals(""))  { 
					System.out.println("Ȱ��ȭ");
					btnMember.setEnabled(true); //�����ϱ� ��ư Ȱ��ȭ
					btnMember.setBackground(Color.YELLOW);
				}else {
					btnMember.setEnabled(false); // ��ư ��Ȱ��ȭ
				}

			}
		} );

		// ȸ������ - �����ϱ� ��ư
		btnMember.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				travel_info.setId(tf_nid.getText());
				travel_info.setPassword(tf_npwd.getText());

				new Travel_Writer(travel_info);
				// ȸ�������ϱ��ư Ŭ�� �� ����(����ID,PWD)�� ��������� Ȯ���ؼ� ������ ȸ������ ���� ������ ���� 
				String path = "info/" + travel_info.getId();
				File f = new File(path);
				if( !f.exists() ) {
					JOptionPane.showMessageDialog(frame2,"ȸ������ ����!");
				}else {
					JOptionPane.showMessageDialog(frame2,"ȸ������ ����!");
					tf_nid.setText("");
					tf_npwd.setText("");
					frame2.dispose();
				}
			}
		} );
		//--------------------------------------------------------------------------------------------		

		frame.add(tf_id); 		// ���̵�â
		frame.add(tf_pwd); // ��й�ȣâ
		frame.add(checkbox); // ��й�ȣȮ�� üũ�ڽ�
		frame.add(checkpwd);
		frame.add(lid);  //���̵��̺�
		frame.add(lpwd); //�н����� ���̺�
		frame.add(none_idnpass); // �α���,�н����� ���϶� ������ ���̺�

		frame.add(btnlog); // �α��ι�ư
		frame.add(btnnew); //ȸ������ ��ư

		frame2.add(tf_nid);     // ȸ������ - ID(TextField)
		frame2.add(tf_npwd);    // ȸ������ - PWD(TextField)
		frame2.add(btnidCheck); // ȸ������ - IdCheck ��ư
		frame2.add(lid2); 		// ȸ������ - ID ���̺�
		frame2.add(lpwd2); 		// ȸ������ - PWD ���̺�
		frame2.add(btnMember); 	// ȸ������ - ȸ�������ϱ� ��ư
		frame2.add(lidCheck); 	// ȸ������ - ID �ߺ����� �ƴ��� ���̺�


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



























