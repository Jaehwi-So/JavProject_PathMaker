package login;

import java.awt.TextField;
import java.io.Serializable;

// # 사용자의 id와 pwd가 저장 및 반환 되는 클래스

public class Travel_Info implements Serializable{ 

	private String id;
	private String password;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
