package WuziChess;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

/*
 * 编写一些五子棋的按钮
 */
public class WuziButton extends JButton{
	private static final long serialVersionUID = 1L;
public JButton Exit,Back,Suppender,New;
public ButtonGroup Garde,BackBoard;
public JLabel GardeLable,BoardLable;
public JRadioButton simple,middle,difficult,board1,board2,board3;
//public JTextField num1,num2;
public WuziButton(){
	//退出按钮
	Exit=new JButton("退出");
	Exit.setBounds((int)WuziBoard.Left/4+1150, (int)WuziBoard.Low-200, 80, 40);	
	Exit.setFont(new Font("Serif",Font.ITALIC,20));
	//悔棋按钮
	Back=new JButton("悔棋");
	Back.setBounds((int)WuziBoard.Left/4+1150,(int)WuziBoard.Low-300, 80, 40);
	Back.setFont(new Font(Font.SERIF, Font.ITALIC, 20));
	//认输按钮
	Suppender=new JButton("服了");
	Suppender.setBounds((int)WuziBoard.Left/4+1000, (int)(int)WuziBoard.Low-200, 80, 40);
	Suppender.setFont(new Font(Font.SERIF, Font.ITALIC, 20));
	//从新开始按钮
	New=new JButton("新局");
	New.setBounds((int)WuziBoard.Left/4+1000,(int)WuziBoard.Low-300,80,40);
	New.setFont(new Font(Font.SERIF, Font.ITALIC, 20));
	//添加按钮效果
	Handle h=new Handle();
	Exit.addActionListener(h);
	Back.addActionListener(h);
	Suppender.addActionListener(h);
	New.addActionListener(h);
	//设置难度等级
	
	
	Garde=new ButtonGroup();
	simple=new JRadioButton("简单",true);
	middle=new JRadioButton("一般") ;//开始设置成中的级别
	difficult=new JRadioButton("大神");
	middle.setFont(new Font(Font.SERIF, Font.ITALIC, 26));
	difficult.setFont(new Font(Font.SERIF, Font.ITALIC, 26));
	simple.setFont(new Font(Font.SERIF, Font.ITALIC, 26));
	Garde.add(middle);
	Garde.add(difficult);
	Garde.add(simple);
	difficult.setBounds((int)(WuziBoard.screenSize.width-WuziBoard.Left-800), (int)(WuziBoard.Low-100 ),100, 40);
	middle.setBounds((int)(WuziBoard.screenSize.width-WuziBoard.Left-800), (int)(WuziBoard.Low-200),100, 40);
	simple.setBounds((int)(WuziBoard.screenSize.width-WuziBoard.Left-800), (int)(WuziBoard.Low-300 ),100, 40);
	GardeHandle gh=new GardeHandle();
	simple.addItemListener(gh);
	middle.addItemListener(gh);
	difficult.addItemListener(gh);
	
	
	//添加背景颜色按钮
	BackBoard=new ButtonGroup();
	board1=new JRadioButton("背景1",true);
	board3=new JRadioButton("背景3");
	board2=new JRadioButton("背景2");
	board1.setFont(new Font(Font.SERIF, Font.ITALIC, 26));
	board2.setFont(new Font(Font.SERIF, Font.ITALIC, 26));
	board3.setFont(new Font(Font.SERIF, Font.ITALIC, 26));
	BackBoard.add(board1);
	BackBoard.add(board2);
	BackBoard.add(board3);
	board2.setBounds((int)(WuziBoard.screenSize.width-WuziBoard.Left-1000), (int)(WuziBoard.Low-200),100, 40);
	board1.setBounds((int)(WuziBoard.screenSize.width-WuziBoard.Left-1000), (int)(WuziBoard.Low-300 ),100, 40);
	board3.setBounds((int)(WuziBoard.screenSize.width-WuziBoard.Left-1000), (int)(WuziBoard.Low-100),100, 40);
	BoardHandle bh=new BoardHandle();
	board1.addItemListener(bh);
	board2.addItemListener(bh);
	board3.addItemListener(bh);
	//添加下棋数
	/*num1=new JTextField();
	num1.setText(String.valueOf(WuziImage.stepCount/2));
	num1.setBounds((int)(WuziBoard.screenSize.width-WuziBoard.Left-900), (int)(WuziBoard.Low-250),100, 40);
	num1.setFont(new Font(Font.SERIF, Font.ITALIC, 20));*/
	// 添加标签
	/*GardeLable=new JLabel("等级设置");
	GardeLable.setFont(new Font(Font.SERIF, Font.ITALIC, 15));
	GardeLable.setBounds((int)(WuziBoard.screenSize.width-WuziBoard.Left+60), (int)(WuziBoard.Low-50 ),90, 30);
	BoardLable=new JLabel("背景设置");
	BoardLable.setFont(new Font(Font.SERIF, Font.ITALIC, 15));
	BoardLable.setBounds((int)(WuziBoard.screenSize.width-WuziBoard.Left+60), (int)(WuziBoard.Low-100 ),90, 30);*/
}
/*
 * 退出，悔棋，新游戏，认输按钮的功能
 */
private class Handle implements ActionListener{

	@Override
	//退出游戏按钮
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Exit){
			WuziImage.message="退出，不玩了！";
			WuziAlgorithm wa=new WuziAlgorithm();
			wa.display();
			System.exit(0);
		}
		//悔棋的按钮
		if(WuziAlgorithm.CanGo&&e.getSource()==Back){
			if(WuziAlgorithm.MyWuziList.getSize()!=0){
				WuziAlgorithm.MyWuziList.removeLast();
				WuziAlgorithm wa=new WuziAlgorithm();
				wa.display();
			}
		}
		//认输按钮
		if(WuziAlgorithm.CanGo&&e.getSource()==Suppender){
			WuziImage.message="不行不下了，我怂了，认输！";
			WuziAlgorithm.CanGo=false;
			WuziImage.isSurrender=true;
			WuziAlgorithm wa=new WuziAlgorithm();
			wa.display();
		}
		//重新开始按钮
		if(e.getSource()==New){
			WuziImage.begin=new Date();
			int[][] i=new int[15][15];
			WuziAlgorithm.MyWuziPiece.SetAllPostion(i);
			WuziImage.isGameOver=false;
			WuziImage.isSurrender=false;
			WuziImage.message="";
			WuziAlgorithm wa=new WuziAlgorithm();
			wa.display();
			WuziAlgorithm.CanGo=true;
			WuziImage.stepCount=0;
		}
	}
	
}
/*
 * 等级设置按钮的功能
 */
private class GardeHandle implements ItemListener{

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO 自动生成的方法存根
		if(simple.isSelected()){
			WuziImage.level=0;
		}
		else if(middle.isSelected())
			WuziImage.level=1;
		else if(difficult.isSelected())
			WuziImage.level=2;
		WuziAlgorithm wa=new WuziAlgorithm();
		wa.display();
	}
}
/*
 * 背景及音乐的设置按钮
 */
private class BoardHandle implements ItemListener{

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO 自动生成的方法存根
		if(board1.isSelected()){
			WuziImage.match=0;
			MyFrame.music.stop();
			MyFrame.music=new WuziMusic("背景音乐2.wav");
		}
		if(board2.isSelected()){
			WuziImage.match=1;
			MyFrame.music.stop();
			MyFrame.music=new WuziMusic("围棋少年.wav");
		}
		if(board3.isSelected()){
			WuziImage.match=2;
			MyFrame.music.stop();
			MyFrame.music=new WuziMusic("背景音乐3.wav");
		}
		WuziAlgorithm wa=new WuziAlgorithm();
		wa.display();
	}
}
}




