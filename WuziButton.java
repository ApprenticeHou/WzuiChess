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
 * ��дһЩ������İ�ť
 */
public class WuziButton extends JButton{
	private static final long serialVersionUID = 1L;
public JButton Exit,Back,Suppender,New;
public ButtonGroup Garde,BackBoard;
public JLabel GardeLable,BoardLable;
public JRadioButton simple,middle,difficult,board1,board2,board3;
//public JTextField num1,num2;
public WuziButton(){
	//�˳���ť
	Exit=new JButton("�˳�");
	Exit.setBounds((int)WuziBoard.Left/4+1150, (int)WuziBoard.Low-200, 80, 40);	
	Exit.setFont(new Font("Serif",Font.ITALIC,20));
	//���尴ť
	Back=new JButton("����");
	Back.setBounds((int)WuziBoard.Left/4+1150,(int)WuziBoard.Low-300, 80, 40);
	Back.setFont(new Font(Font.SERIF, Font.ITALIC, 20));
	//���䰴ť
	Suppender=new JButton("����");
	Suppender.setBounds((int)WuziBoard.Left/4+1000, (int)(int)WuziBoard.Low-200, 80, 40);
	Suppender.setFont(new Font(Font.SERIF, Font.ITALIC, 20));
	//���¿�ʼ��ť
	New=new JButton("�¾�");
	New.setBounds((int)WuziBoard.Left/4+1000,(int)WuziBoard.Low-300,80,40);
	New.setFont(new Font(Font.SERIF, Font.ITALIC, 20));
	//��Ӱ�ťЧ��
	Handle h=new Handle();
	Exit.addActionListener(h);
	Back.addActionListener(h);
	Suppender.addActionListener(h);
	New.addActionListener(h);
	//�����Ѷȵȼ�
	
	
	Garde=new ButtonGroup();
	simple=new JRadioButton("��",true);
	middle=new JRadioButton("һ��") ;//��ʼ���ó��еļ���
	difficult=new JRadioButton("����");
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
	
	
	//��ӱ�����ɫ��ť
	BackBoard=new ButtonGroup();
	board1=new JRadioButton("����1",true);
	board3=new JRadioButton("����3");
	board2=new JRadioButton("����2");
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
	//���������
	/*num1=new JTextField();
	num1.setText(String.valueOf(WuziImage.stepCount/2));
	num1.setBounds((int)(WuziBoard.screenSize.width-WuziBoard.Left-900), (int)(WuziBoard.Low-250),100, 40);
	num1.setFont(new Font(Font.SERIF, Font.ITALIC, 20));*/
	// ��ӱ�ǩ
	/*GardeLable=new JLabel("�ȼ�����");
	GardeLable.setFont(new Font(Font.SERIF, Font.ITALIC, 15));
	GardeLable.setBounds((int)(WuziBoard.screenSize.width-WuziBoard.Left+60), (int)(WuziBoard.Low-50 ),90, 30);
	BoardLable=new JLabel("��������");
	BoardLable.setFont(new Font(Font.SERIF, Font.ITALIC, 15));
	BoardLable.setBounds((int)(WuziBoard.screenSize.width-WuziBoard.Left+60), (int)(WuziBoard.Low-100 ),90, 30);*/
}
/*
 * �˳������壬����Ϸ�����䰴ť�Ĺ���
 */
private class Handle implements ActionListener{

	@Override
	//�˳���Ϸ��ť
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Exit){
			WuziImage.message="�˳��������ˣ�";
			WuziAlgorithm wa=new WuziAlgorithm();
			wa.display();
			System.exit(0);
		}
		//����İ�ť
		if(WuziAlgorithm.CanGo&&e.getSource()==Back){
			if(WuziAlgorithm.MyWuziList.getSize()!=0){
				WuziAlgorithm.MyWuziList.removeLast();
				WuziAlgorithm wa=new WuziAlgorithm();
				wa.display();
			}
		}
		//���䰴ť
		if(WuziAlgorithm.CanGo&&e.getSource()==Suppender){
			WuziImage.message="���в����ˣ������ˣ����䣡";
			WuziAlgorithm.CanGo=false;
			WuziImage.isSurrender=true;
			WuziAlgorithm wa=new WuziAlgorithm();
			wa.display();
		}
		//���¿�ʼ��ť
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
 * �ȼ����ð�ť�Ĺ���
 */
private class GardeHandle implements ItemListener{

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO �Զ����ɵķ������
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
 * ���������ֵ����ð�ť
 */
private class BoardHandle implements ItemListener{

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO �Զ����ɵķ������
		if(board1.isSelected()){
			WuziImage.match=0;
			MyFrame.music.stop();
			MyFrame.music=new WuziMusic("��������2.wav");
		}
		if(board2.isSelected()){
			WuziImage.match=1;
			MyFrame.music.stop();
			MyFrame.music=new WuziMusic("Χ������.wav");
		}
		if(board3.isSelected()){
			WuziImage.match=2;
			MyFrame.music.stop();
			MyFrame.music=new WuziMusic("��������3.wav");
		}
		WuziAlgorithm wa=new WuziAlgorithm();
		wa.display();
	}
}
}




