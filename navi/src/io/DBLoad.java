package io;

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

import data.Database;

public class DBLoad {
	FileInputStream fis = null;
	ObjectInputStream ois = null;

	// 불러오기
	public Database load_db(Frame f) {
		Database info = new Database();
		try {

			FileDialog fd = new FileDialog(f, "불러오기", FileDialog.LOAD);
			fd.setVisible(true);

			String path = fd.getDirectory() + fd.getFile();
			System.out.println(path);

			fis = new FileInputStream(path);
			ois = new ObjectInputStream(fis);

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
