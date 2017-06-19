package WuziChess;

import java.awt.Graphics;

/*
 * 储存当前棋面，用一个二维数组来储存位置，并且能够表明状态,建立一个15*15的数组
 */
public class WuziPiece {
private int[][] postionFlag=new int[15][15];
public void setPostion(int x,int y,int flag)
{
	postionFlag[x][y]=flag;
}
public void SetAllPostion(int[][] NewFlag){
	postionFlag=NewFlag;
}
public int getPostionFlag(int x,int y){
	return postionFlag[x][y];
}
public int[][] getAllFlag()
{
	return postionFlag;
}
public void drawChessPiece(Graphics g){
	for(int i=0;i<15;i++)
		for(int j=0;j<15;j++){
			int x=(int)(WuziBoard.Left+i*(int)(WuziBoard.Inc)-15);
			int y=(int)(25+j*(int)WuziBoard.Inc-15);
			if(getPostionFlag(i, j)==1)
				g.drawImage(WuziImage.whiteChess, x, y, null);
			else if(getPostionFlag(i, j)==2)
				g.drawImage(WuziImage.blackChess, x, y, null);
		}
}
}
