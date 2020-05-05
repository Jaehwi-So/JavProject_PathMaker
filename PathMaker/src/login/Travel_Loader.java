package login;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Travel_Loader {
	
	// # �α����Ҷ� ������ �ҷ����� Ŭ����
	
	private Travel_Info info; // �α����Ҷ� �Է��� ID,PWD ��
	
	public Travel_Info getInfo() {
		return info;
	}
	
	FileInputStream fis = null;
	ObjectInputStream ois = null;
	
	public Travel_Loader(Travel_Info info) {
		this.info = info;
		
		String path = "info/" +info.getId()+ "/UserInfo.sav";
		
		File f = new File(path);
		
		if(f.exists()) {
			
			try {
				fis = new FileInputStream(path);
				ois = new ObjectInputStream(fis);
				
				this.info = (Travel_Info)ois.readObject();
				
			} catch (Exception e) {
				// TODO: handle exception
			}finally {
				try {
					ois.close();
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
}
