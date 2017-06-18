package WuziChess;

import java.awt.Color;

import javax.swing.JFrame;

/*
 * 构建图形框架
 */
public class MyFrame extends JFrame{
public static WuziMusic music;
/*
 * 初始构建
 */
private static final long serialVersionUID = 1L;
public MyFrame(){
	WuziBoard myWuziBoard=new WuziBoard();
	WuziButton myWuziButton=new WuziButton();
	WuziPiece myChessPiece=new WuziPiece();
	//设置背景颜色
	setBackground(new Color(WuziImage.ColorBackBoard[WuziImage.match][0], 
			WuziImage.ColorBackBoard[WuziImage.match][1],
			WuziImage.ColorBackBoard[WuziImage.match][2]));
	//设置默认窗口关闭
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//设置窗口大小
	setSize(WuziBoard.screenSize.width-10, WuziBoard.screenSize.height-15);
	//设置背景
	setIconImage(WuziImage.icon);
	//设置标题
	setTitle("请  开  始  你  的  表  演");
	//添加按钮及标签
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
	//设置音乐
	 music=new WuziMusic("背景音乐2.wav");
	 setVisible(true);
	 setResizable(false);
} 
public static void main(String[] args){	
	new MyFrame();
	 }
}
