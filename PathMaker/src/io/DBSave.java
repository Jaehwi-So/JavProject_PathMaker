package io;

//# �ܺο� �����͸� �����ϴ� Ŭ����

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

	// ����
	public void save_db(Database exe, Frame f) {
		//System.out.println("��� : ");
		if(exe.getPath().equals("")) {
			try {
				FileDialog fd = new FileDialog(f, "�����ϱ�", FileDialog.SAVE);
				fd.setVisible(true);

				String path = fd.getDirectory() + fd.getFile() + ".sav";
				//String path = "data/"+ exe.getName() + ".sav";

				fos = new FileOutputStream(path);
				oos = new ObjectOutputStream(fos);

				oos.writeObject(exe);

				
				// FileDialog���� ��ҹ�ư�� ������ �ʰ� ���������� ������ �ܿ�
				if (fd.getFile() != null) {
					JOptionPane.showMessageDialog(f, "����Ϸ�");
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
				System.out.println("����Ϸ�");
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
