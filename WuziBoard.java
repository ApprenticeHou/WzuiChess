package WuziChess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
/*
 * ��������ͻ滭���߿�͸������ֵĴ�С����������
 */
public class WuziBoard {
	public static double Inc,Left,Low;
	//IncΪ���Ӽ�࣬LeftΪ�߿���룬LowΪ���¾���
	public static Dimension screenSize;
	//dimΪͼ��ߴ�
public WuziBoard(){
	screenSize=Toolkit.getDefaultToolkit().getScreenSize();
	Inc=(screenSize.height-100)/14;
	Low=screenSize.height-100;
	Left=(screenSize.width-screenSize.height+100)/2.0;
}
public void GrawChessBoard(Graphics g){
	//���ñ߿���ɫ
	g.setColor(new Color(WuziImage.ColorBackBoard[WuziImage.match][1], WuziImage.ColorBackBoard[WuziImage.match][0], WuziImage.ColorBackBoard[WuziImage.match][2]));
	g.fillRect(0,0, screenSize.width-15, 15);//�ϱ߿�
	g.fillRect(0,(int)(14*Inc+25),(int)screenSize.width, 10);//�±߿�
	g.fillRect(0, 0, 15, screenSize.height-5);//��߿�
	g.fillRect(screenSize.width-35, 0, 15,screenSize.height-5);//�ұ߿�
	//������
	g.fillRect((int)Left-24, 0, 15, (int)(25+14*Inc+2));//��߿�
	g.fillRect((int)(Left+14*Inc+10), 0, 15, (int)(25+14*Inc+2));//�ұ߿�
	//����
	g.fillRect(10, (int)(10+14*Inc), (int)(Left-45), 20);//���½� 
	g.fillRect((int) (Left +14 *Inc+20),(int)(10+14*Inc),(int)(Left-45) ,20);//�ұ��˵��½�
//	g.setColor(Color.red);
//	g.fillRect(0,(int)(25+14*Inc+11), (int)screenSize.width, 10);//ģ��������
	//����
	g.setColor(Color.BLACK);
	//���Ƹ���
	for(int num=0;num<15;num++){
		//��������
		g.drawLine((int)(Left+num*Inc), 25, (int)(Left+num*Inc), screenSize.height-83);
		g.drawLine((int)(Left+num*Inc+1), 25, (int)(Left+num*Inc+1), screenSize.height-83);
		//���ƺ���
		g.drawLine((int)Left, (int)(25+num*Inc), (int)(screenSize.width-Left-7), (int)(25+num*Inc));
		g.drawLine((int)Left, (int)(25+num*Inc+1), (int)(screenSize.width-Left-7), (int)(25+num*Inc+1));
	}
	//���Ʊ߿�������˫��
	g.drawLine((int)(Left-2), 23, (int)(Left-2), screenSize.height-81);//��һ��
	g.drawLine((int)(Left+14*Inc+3), 23, (int)(Left+14*Inc+3), screenSize.height-81);//������
	g.drawLine((int)(Left-1), 22,(int) (screenSize.width - Left-6), 22);//��һ��
	g.drawLine((int)(Left-1), (int) (25 + 14 * Inc+3),(int) (screenSize.width - Left-6), (int) (25 + 14 * Inc+3));//�����
	//���������
	 g.fillOval((int)(Left+3*Inc-5),(int)(25 + 3* Inc-5),10,10);
	 g.drawOval((int)(Left+3*Inc-7),(int)(25 + 3* Inc-7),15,15); 
	 g.drawOval((int)(Left+3*Inc-8),(int)(25 + 3* Inc-8),16,16); 
	 		
	 g.fillOval((int)(Left+11*Inc-5),(int)(25 + 3* Inc-5),10,10); 
	 g.drawOval((int)(Left+11*Inc-7),(int)(25 + 3* Inc-7),15,15); 
	 g.drawOval((int)(Left+11*Inc-8),(int)(25 + 3* Inc-8),16,16); 
	 		
	 g.fillOval((int)(Left+3*Inc-5),(int)(25 + 11* Inc-5),10,10); 
	 g.drawOval((int)(Left+3*Inc-7),(int)(25 + 11* Inc-7),15,15); 
	 g.drawOval((int)(Left+3*Inc-8),(int)(25 + 11* Inc-8),16,16); 
			
	g.fillOval((int)(Left+11*Inc-5),(int)(25 + 11* Inc-5),10,10); 
	g.drawOval((int)(Left+11*Inc-7),(int)(25 + 11* Inc-7),15,15); 
	g.drawOval((int)(Left+11*Inc-8),(int)(25 + 11* Inc-8),16,16); 
			
	 g.fillOval((int)(Left+7*Inc-5),(int)(25 + 7* Inc-5),10,10); 
	g.drawOval((int)(Left+7*Inc-7),(int)(25 + 7* Inc-7),15,15); 
	 g.drawOval((int)(Left+7*Inc-8),(int)(25 + 7* Inc-8),16,16); 
}


}
























