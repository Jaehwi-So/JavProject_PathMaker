package login;

import java.awt.TextField;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

public class Travel_Writer {
	
	// # ȸ�� ���Խ� ������� id,pwd�� ������ ����Ǵ� Ŭ����
	
	Travel_Start tralog = new Travel_Start();
	
	ObjectOutputStream oos;
	FileOutputStream fos = null;
	
	


	// id�� �ִ��� ������ Ȯ���ϴ� Ŭ����
	public Travel_Writer( Travel_Info info ) { 
		String path = "info/" +info.getId()+ "/UserInfo.sav";
		File dir = new File("info/" + info.getId());
		
		if( !dir.exists()) {
			dir.mkdirs();
		}
		
		//Object��ü�� ���� ID�� PWD�� ����
		try {
			fos = new FileOutputStream(path);
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject(info);
	
			//JOptionPane.showMessageDialog(,"ȸ������ ����!"); f2(������)�� ��� ���߿� Ȯ��!!!!!!!!!!!!!!!!!!!!!!!!
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				oos.close();
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}// try-catch
		
	}
	
}
