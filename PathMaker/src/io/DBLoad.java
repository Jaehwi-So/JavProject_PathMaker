package io;
// # 외부에서 데이터를 불러오는 클래스

import java.awt.Button;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.JOptionPane;

import data.Database;
import gui.MainGUI;

public class DBLoad {
	FileInputStream fis = null;
	ObjectInputStream ois = null;
	MainGUI maingui = new MainGUI();

	// 불러오기
	public Database load_db(Frame f) {
		Database info = new Database();
		try {

			FileDialog fd = new FileDialog(f, "불러오기", FileDialog.LOAD);
			fd.setVisible(true);

			String path = fd.getDirectory() + fd.getFile();
			System.out.println(path);

			fis = new FileInputStream(path);
			try {
				ois = new ObjectInputStream(fis);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(f, "다시 시도해 주세요");
			}
			
			
			



			info = (Database) ois.readObject();
			System.out.println(info.getName()+"불러옴");			
		} catch (Exception e2) {
			e2.printStackTrace();
		} finally {
			try {
				ois.close();
				fis.close();
			} catch (Exception e3) {
				// TODO: handle exception
			}
		}
		return info;
	}
}
