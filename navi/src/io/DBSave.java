package io;

import java.awt.Button;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import data.Database;

public class DBSave {
	Database ex = new Database();

	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	ObjectOutputStream oos = null;
	FileOutputStream fos = null;

	// 저장
	public void save_db(Database exe, Frame f) {
		//System.out.println("경로 : ");
		if(exe.getPath().equals("")) {
			try {
				FileDialog fd = new FileDialog(f, "저장하기", FileDialog.SAVE);
				fd.setVisible(true);

				String path = fd.getDirectory() + fd.getFile() + ".sav";
				//String path = "data/"+ exe.getName() + ".sav";
				System.out.println(path);

				fos = new FileOutputStream(path);
				oos = new ObjectOutputStream(fos);

				oos.writeObject(exe);

				
				// FileDialog에서 취소버튼을 누르지 않고 정상적으로 저장한 겨우
				if (fd.getFile() != null) {
					JOptionPane.showMessageDialog(f, "저장완료");
					exe.setPath(path);
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			} finally {
				try {
					oos.close();
					fos.close();
				} catch (Exception e3) {
					// TODO: handle exception
				}
			}
		}

		else {
			try {
				String path = exe.getPath();
				System.out.println(path);

				fos = new FileOutputStream(path);
				oos = new ObjectOutputStream(fos);

				oos.writeObject(exe);
				System.out.println("저장완료");
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			} finally {
				try {
					oos.close();
					fos.close();
				} catch (Exception e3) {
					// TODO: handle exception
				}
			}
		}
	}
}
