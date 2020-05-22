
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import org.json.simple.JSONObject;

//버튼 사용을 위해 extends	 JFrame
//이벤트 메소드를 사용하기 위해  implements  MouseListener, KeyListener
@SuppressWarnings("serial")
public class Lotto extends JFrame implements MouseListener, KeyListener {

	//로또 당첨 번호를 표시 할 버튼 배열  
	MyButton[] lottoN = new MyButton[7];
	
	//응모 할 번호를 입렬할 창 배열  
	JTextField[] myN = new JTextField[6];
	
	//검색 버튼
	JButton search = new JButton(new ImageIcon("./images/search.png"));
	
	//응모 할 번호 자동 선택 할 버튼
	JButton random = new JButton(new ImageIcon("./images/random.png"));

	//회차 입력할 창
	JTextField  turnTxt = new JTextField();
	
	//로또번호 라는 문구 띄울 라벨
	JLabel titleLabel = new JLabel("LOTTO.");
	
	//회차 정보, 경고 문구 표시할 라벨
	JLabel turnLabel = new JLabel("");

	//순위 문구 표시할 라벨
	JLabel rankLabel = new JLabel("");
	
	//출력할 창 설정 메소드
	public void init() {
		
		//모든 배치 설정값을 null로 줘서 직접 좌표 설정 할 수 있도록 함
		getContentPane().setLayout(null);
		
		//lottoN 배열 속 요소들 생성, 설정, 추가
		for(int i=0; i<lottoN.length; i++) {
			lottoN[i] = new MyButton("00");
			int wm = 65;	//수월한 설정을 위해 변수 선언
			lottoN[i].setBounds(10+(wm*i),55,60,60);	//위치 설정
			getContentPane().add(lottoN[i]);	//추가
		}

		//보너스넘버 색상 설정 변경
		lottoN[6].setBgColor(Color.blue);
		lottoN[6].setTxtColor(Color.yellow);
		
		//myN 배열 속 요소들 생성, 설정, 추가
		for(int i=0; i<myN.length; i++) {
			myN[i] = new JTextField();
			int wm = 65;	//수월한 설정을 위해 변수 선언
			myN[i].setBounds(10+(wm*i),(55+60+15),60,60);	//위치 설정
			myN[i].setHorizontalAlignment(JTextField.CENTER);	//커서 중앙정렬
			getContentPane().add(myN[i]);	//추가
		}
		
		//폰트 변경
		titleLabel.setFont(titleLabel.getFont().deriveFont(35f));
		rankLabel.setFont(titleLabel.getFont().deriveFont(30f));
		rankLabel.setForeground(Color.red);

		//위치,크기 설정 (x좌표,y좌표,너비,높이)
		titleLabel.setBounds(20,15,150,30);
		random.setBounds(10+(65*6),(55+60+15),60,60);
		turnLabel.setBounds(25, (60+60+60+30), 300, 50);
		turnTxt.setBounds(270, (55+60+60+30), 125, 60);
		search.setBounds(400, (55+60+60+30), 60, 60);
		rankLabel.setBounds(40, (60+60+60+30+60+5), 300, 30);
		
		search.setContentAreaFilled(false);	//버튼의 기본배경 표시 안함
		random.setContentAreaFilled(false);
		
		turnTxt.setHorizontalAlignment(JTextField.CENTER);	//커서 중앙 정렬
		
		//추가. 꼭 위치와 크기 설정 후 추가해야됨
		getContentPane().add(titleLabel);
		getContentPane().add(random);
		getContentPane().add(turnLabel);
		getContentPane().add(turnTxt);
		getContentPane().add(search);
		getContentPane().add(rankLabel);
	}
	
	//마우스, 키보드에 대한 이벤트 메소드
	public void event() {
		//search 마우스 이벤트 추가
		search.addMouseListener(this);
		
		//random 마우스 이벤트 추가
		random.addMouseListener(this);
		
		//turnTxt 키보드 이벤트 추가
		turnTxt.addKeyListener(this);
	}
	
	//번호 자동 선택 메소드
	void shuffle() {
		Random rd = new Random();
		int[] num = new int[6];		//숫자를 담을 임의의 int형 배열 선언
		for(int i=0; i<num.length; i++) {	//배열 각 요소에 숫자(난수)를 담는 for문
			num[i] = rd.nextInt(45)+1;
			for(int j=0; j<i; j++) {	//배열에 담긴 숫자의 중복여부를 확인하는 for문
				if(num[i] == num[j]) {	//중복되는 숫자가 있을 경우
					i--;		//로또번호 재생성을 위해 이전으로
					break;		//나가기
				}
			}
		}
		for(int i=0; i<num.length; i++) {
			myN[i].setText(String.valueOf(num[i]));
		}
	}
	
	//결과창
	void showResult(){	
				
		//숫자가 아닌 입력 거르기
//		boolean stNum = false;
//		boolean arrNum = false;
	
		try {
			Integer.parseInt(turnTxt.getText());	//문자로 입력받은 turnNum을 int형으로 변환
//			stNum = true;
			for(int i=0; i<myN.length; i++) {		//문자로 입력받은 myN을 int형으로 변환
				Integer.parseInt(myN[i].getText());
			}
//			arrNum = true;
		}
		catch (Exception e) {		//위 내용에 오류 발생
			turnLabel.setText("숫자를 입력 해 주세요.");
			return;
		}
		
		//url 접속
		try {			
			JsonReader jr = new JsonReader();		//입력받은 회차정보를 json url에 연결
			JSONObject jo = jr.connectionUrlToJSON(turnTxt.getText());	
			String[] a = new String[6];
			int nCnt = 0;
			int bCnt = 0;
			
			//url 접속 실패
			if(jo==null) {
				return;
			}
			
			//당첨 번호 값이 없는 회차 입력 거르기
			if(String.valueOf(jo.get("returnValue")).equals("fail")){
				turnLabel.setText("회차정보가 없습니다.");
				return;
			}		
			
			//1 이상 45 이하가 아닌 myN 입력 거르기
			if(Integer.parseInt(myN[0].getText()) < 1 || Integer.parseInt(myN[0].getText()) > 45
				|| Integer.parseInt(myN[1].getText()) < 1 || Integer.parseInt(myN[1].getText()) > 45
				|| Integer.parseInt(myN[2].getText()) < 1 || Integer.parseInt(myN[2].getText()) > 45
				|| Integer.parseInt(myN[3].getText()) < 1 || Integer.parseInt(myN[3].getText()) > 45
				|| Integer.parseInt(myN[4].getText()) < 1 || Integer.parseInt(myN[5].getText()) > 45
				|| Integer.parseInt(myN[5].getText()) < 1 || Integer.parseInt(myN[5].getText()) > 45) 
			{
				turnLabel.setText("로또 번호의 범위는 1~45 입니다.");
				return;
			}
			
			//중복값이 있는 myN 입력 거르기 
			ArrayList<Integer> checkList = new ArrayList<Integer>();
			for (int i=0; i<myN.length; i++) {	//setHash 리스트에 입력값 넣기
				checkList.add(Integer.parseInt(myN[i].getText()));
			}
			
			HashSet<Integer> hash = new HashSet<Integer>(checkList); //HashSet에 setHash 데이터 삽입
			ArrayList<Integer> checkedList = new ArrayList<Integer>(hash); // 중복 확인
			if (checkedList.size() < 6) {	// 중복값이 있으면 hash에서 제외되므로 checkedList의 크기가 6이 될 수 없다.
				turnLabel.setText("중복 입력된 로또 번호가 있습니다.");
			return;
			}

			//번호 표시 버튼의 숫자를 json에서 불러온 로또 당첨 번호에 맞춰 출력
			for(int i=0; i<lottoN.length-1; i++) {
				lottoN[i].setText(String.valueOf(jo.get("drwtNo"+(i+1))));
				a[i] = myN[i].getText();
			}
			lottoN[6].setText(String.valueOf(jo.get("bnusNo")));

			//myN과 lottoN 1~6을 비교
			for(int i=0; i<lottoN.length-1; i++) {	//lottoN과 myN의 요소들을 비교하기 위한 for문
				for(int j=0; j<a.length; j++) {
					if(a[j].equals(lottoN[i].getText())) {	//같은 값이 있을 경우
						lottoN[i].setBgColor(Color.yellow);	//lottoN 색상 변경
						lottoN[i].setTxtColor(Color.blue);
						nCnt++;								//몇 개인지 저장
					}
				}
			}
			
			//myN과 lottoN 7(보너스번호)를 비교
			for(int i=0; i<myN.length; i++) {	//lottoN[6]과 myN의 요소들을 비교하기 위한 for문
				if(a[i].equals(lottoN[6].getText())) {	//같은 값이 있을 경우
					lottoN[6].setBgColor(Color.yellow);	//lottoN 색상 변경
					lottoN[6].setTxtColor(Color.blue);
					bCnt++;								//몇 개인지 저장
				}
			}
			
			//저장해둔 개수 값을 비교하여 등수 출력
			if(nCnt==6)	{
				rankLabel.setText("축하합니다! 1등 입니다!!!!");
				random.setIcon(new ImageIcon("./images/1.png"));	//버튼 속 이미지 변경
				random.setBorderPainted(false);		//버튼의 테두리 표시 안함
			}
			else if (nCnt==5 && bCnt==1) {
				rankLabel.setText("축하합니다! 2등 입니다!!!");
				random.setIcon(new ImageIcon("./images/2.png"));
				random.setBorderPainted(false);
			}
			else if (nCnt==5) {
				rankLabel.setText("축하합니다! 3등 입니다!!"); 
				random.setIcon(new ImageIcon("./images/3.png"));
				random.setBorderPainted(false);
			}
			else if (nCnt==4) {
				rankLabel.setText("축하합니다! 4등 입니다!");
				random.setIcon(new ImageIcon("./images/4.5.png"));
				random.setBorderPainted(false);
			}
			else if (nCnt==3) {
				rankLabel.setText("축하합니다. 5등 입니다.");
				random.setIcon(new ImageIcon("./images/4.5.png"));
				random.setBorderPainted(false);
			}
			else {
				rankLabel.setText("아쉽습니다. 다음 기회를 노려봅시다.");
				random.setVisible(false);		//버튼을 창에 표시하지 않음. 숨김
			}
			
			//몇 회차에 대한 결과인지 출력
			turnLabel.setText(String.valueOf(jo.get("drwNoDate"))
											+" "+turnTxt.getText()+"회차 결과는...");
		}
		catch (Exception e) {
			e.printStackTrace();
			turnLabel.setText("오류가 발생했습니다.");
			return;
		}
	}
	
	//폰트 적용 메소드
	public static void setUIFont(javax.swing.plaf.FontUIResource f) {
		Enumeration<Object> keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource)
				UIManager.put(key, f);
		}
	}
	
	//틀
	Lotto(){
		super("로또 번호 조회");
		init();
		event();
		
		//실행 창 크기설정
		setSize(485,360);
		
		//(true) 창에 표시됨
		setVisible(true);
		
		//윈도우 창 종료시 프로세스까지 종료시킴
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//메인
	public static void main(String[] args) throws Exception {
		
		//Font 설정
		//파일이 삭제될 경우 Exception 발생소지가 있으므로 예외처리 필요
		String fType = "NanumPenScript-Regular.ttf";
		Font f1 = Font.createFont(Font.TRUETYPE_FONT, new File(fType));
		
		//Font 설정
		setUIFont(new FontUIResource(f1.deriveFont(25f)));		
		
		//Lotto 메소드 호출
		new Lotto();
	}
		
	
	//implements MouseListener, KeyListener 에 대한 override
	@Override
	public void keyPressed(KeyEvent arg0) {
	}


	//키보드 키 입력 이벤트
	@Override
	public void keyReleased(KeyEvent arg0) {
		// ENTER를 누르면 showResult 메소드 호출
		if(arg0.getKeyCode()==KeyEvent.VK_ENTER) {
			showResult();
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	
	//마우스 클릭 이벤트
	@Override
	public void mouseClicked(MouseEvent e) {
		//search을 클릭하면 showResult 메소드 호출
		if(e.getSource()==search) {
			showResult();
		}
		//random을 클릭하면 shuffle 메소드 호출
		if(e.getSource()==random) {
			shuffle();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}}
















