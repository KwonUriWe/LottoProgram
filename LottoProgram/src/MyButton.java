import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

//serial에 대한 경고를 표시하지 않음
@SuppressWarnings("serial")

//기본 버튼 클래스 불러다가 내가 원하는 모양으로 변경하는 클래스 만듬
public class MyButton extends JButton{
	private Color txtColor = Color.white;
	private Color bgColor = Color.black;

	public MyButton (String text) {
		super(text);
		setBorderPainted(false);
		setOpaque(false);
	}
	
	public void setTxtColor(Color c) {
		txtColor = c;
	}

	public void setBgColor(Color c) {
		bgColor = c;
	}
	
	
	@Override		//overide > 메소드에 public 붙이기 필수
	public void paint(Graphics grp) {
		
		int w = getWidth();
		int h = getHeight();
		
		grp.setColor(bgColor);		 
		grp.fillRoundRect(0, 0, w, h, 100, 100);
		
		grp.setColor(txtColor);		 
		grp.drawString(getText(), (int)getSize().getWidth()/2-8, (int)getSize

().getWidth()/2+7);
	}
}
