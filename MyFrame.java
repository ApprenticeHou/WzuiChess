package WuziChess;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;

/*
 * ����ͼ�ο��
 */
public class MyFrame extends JFrame{
public static WuziMusic music;
/*
 * ��ʼ����
 */
private static final long serialVersionUID = 1L;
public MyFrame(){
	WuziBoard myWuziBoard=new WuziBoard();
	WuziButton myWuziButton=new WuziButton();
	WuziPiece myChessPiece=new WuziPiece();
	//���ñ�����ɫ
	setBackground(new Color(WuziImage.ColorBackBoard[WuziImage.match][0], 
			WuziImage.ColorBackBoard[WuziImage.match][1],
			WuziImage.ColorBackBoard[WuziImage.match][2]));
	//����Ĭ�ϴ��ڹر�
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//���ô��ڴ�С
	setSize(WuziBoard.screenSize.width-10, WuziBoard.screenSize.height-15);
	//���ñ���
	setIconImage(WuziImage.icon);
	//���ñ���
	setTitle("��  ��  ʼ  ��  ��  ��  ��");
	//��Ӱ�ť����ǩ
	WuziPanel myWuzipanel=new WuziPanel(myWuziBoard, myChessPiece);
	myWuzipanel.setLayout(null);
	myWuzipanel.add(myWuziButton.Exit);
	myWuzipanel.add(myWuziButton.New);
	myWuzipanel.add(myWuziButton.Suppender);
	myWuzipanel.add(myWuziButton.Back);
	myWuzipanel.add(myWuziButton.board1);
	myWuzipanel.add(myWuziButton.board2);
	myWuzipanel.add(myWuziButton.board3);
	myWuzipanel.add(myWuziButton.simple);
	myWuzipanel.add(myWuziButton.middle);
	myWuzipanel.add(myWuziButton.difficult);
//    myWuzipanel.add(myWuziButton.num1);
	
    add(myWuzipanel);

//	add(myWuziButton.board1);add(myWuziButton.New);add(myWuziButton.Exit);add(myWuziButton.board2);
	WuziAlgorithm myWuziAi=new WuziAlgorithm(myChessPiece, myWuziBoard, myWuzipanel);
	addMouseListener(myWuziAi);
	//��������
	 music=new WuziMusic("��������2.wav");
	 setVisible(true);
	 setResizable(false);
} 
public static void main(String[] args){	
	new MyFrame();
	 }
}
