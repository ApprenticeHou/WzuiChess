package WuziChess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
/*
 * 用来计算和绘画出边框和各个布局的大小，画出棋盘
 */
public class WuziBoard {
	public static double Inc,Left,Low;
	//Inc为格子间距，Left为边框距离，Low为上下距离
	public static Dimension screenSize;
	//dim为图距尺寸
public WuziBoard(){
	screenSize=Toolkit.getDefaultToolkit().getScreenSize();
	Inc=(screenSize.height-100)/14;
	Low=screenSize.height-100;
	Left=(screenSize.width-screenSize.height+100)/2.0;
}
public void GrawChessBoard(Graphics g){
	//设置边框颜色
	g.setColor(new Color(WuziImage.ColorBackBoard[WuziImage.match][1], WuziImage.ColorBackBoard[WuziImage.match][0], WuziImage.ColorBackBoard[WuziImage.match][2]));
	g.fillRect(0,0, screenSize.width-15, 15);//上边框
	g.fillRect(0,(int)(14*Inc+25),(int)screenSize.width, 10);//下边框
	g.fillRect(0, 0, 15, screenSize.height-5);//左边框
	g.fillRect(screenSize.width-35, 0, 15,screenSize.height-5);//右边框
	//画棋盘
	g.fillRect((int)Left-24, 0, 15, (int)(25+14*Inc+2));//左边框
	g.fillRect((int)(Left+14*Inc+10), 0, 15, (int)(25+14*Inc+2));//右边框
	//棋手
	g.fillRect(10, (int)(10+14*Inc), (int)(Left-45), 20);//左下角 
	g.fillRect((int) (Left +14 *Inc+20),(int)(10+14*Inc),(int)(Left-45) ,20);//右边人的下角
//	g.setColor(Color.red);
//	g.fillRect(0,(int)(25+14*Inc+11), (int)screenSize.width, 10);//模拟任务栏
	//划线
	g.setColor(Color.BLACK);
	//绘制格线
	for(int num=0;num<15;num++){
		//绘制竖线
		g.drawLine((int)(Left+num*Inc), 25, (int)(Left+num*Inc), screenSize.height-83);
		g.drawLine((int)(Left+num*Inc+1), 25, (int)(Left+num*Inc+1), screenSize.height-83);
		//绘制横线
		g.drawLine((int)Left, (int)(25+num*Inc), (int)(screenSize.width-Left-7), (int)(25+num*Inc));
		g.drawLine((int)Left, (int)(25+num*Inc+1), (int)(screenSize.width-Left-7), (int)(25+num*Inc+1));
	}
	//绘制边框网格线双线
	g.drawLine((int)(Left-2), 23, (int)(Left-2), screenSize.height-81);//竖一线
	g.drawLine((int)(Left+14*Inc+3), 23, (int)(Left+14*Inc+3), screenSize.height-81);//竖二线
	g.drawLine((int)(Left-1), 22,(int) (screenSize.width - Left-6), 22);//横一线
	g.drawLine((int)(Left-1), (int) (25 + 14 * Inc+3),(int) (screenSize.width - Left-6), (int) (25 + 14 * Inc+3));//衡二线
	//绘制五个点
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
























