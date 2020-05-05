package login;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Travel_Loader {
	
	// # 로그인할때 파일을 불러오는 클래스
	
	private Travel_Info info; // 로그인할때 입력한 ID,PWD 값
	
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
