package login;

import java.awt.TextField;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

public class Travel_Writer {
	
	// # 회원 가입시 사용자의 id,pwd가 폴더에 저장되는 클래스
	
	Travel_Start tralog = new Travel_Start();
	
	ObjectOutputStream oos;
	FileOutputStream fos = null;
	
	


	// id가 있는지 없는지 확인하는 클래스
	public Travel_Writer( Travel_Info info ) { 
		String path = "info/" +info.getId()+ "/UserInfo.sav";
		File dir = new File("info/" + info.getId());
		
		if( !dir.exists()) {
			dir.mkdirs();
		}
		
		//Object객체를 통해 ID와 PWD를 저장
		try {
			fos = new FileOutputStream(path);
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject(info);
	
			//JOptionPane.showMessageDialog(,"회원가입 성공!"); f2(프레임)이 없어서 나중에 확인!!!!!!!!!!!!!!!!!!!!!!!!
			
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
