package main;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelMaker extends Panel {
	
		
	
	Font font2 = new Font("", Font.PLAIN, 30);
	public PanelMaker(int loX, int loY, int siX,int siY){
		setBounds(loX, loY, siX, siY);
				
		TextField tf = new TextField(20);
		tf.setFont(font2);
		Button add = new Button("입력");
		add.setPreferredSize(new Dimension(150,40));
		Button delete = new Button("삭제");
		delete.setPreferredSize(new Dimension(150,40));
		
		
		add(tf);
		add(add);
		add(delete);
		setVisible(true);
		
		
		
		
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				removeAll();
				
			}
		});
		
		
		
		
	}//PanelMaker
	
}
