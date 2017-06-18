package WuziChess;

public class WuziValue {
	//获取当前棋子的坐标和值
private int x,y,weight;
//x-当前x坐标，y-当前y坐标，weight-当前代表值
public WuziValue(int x,int y,int weight){
	this.x=x;
	this.y=y;
	this.weight=weight;
}
public int getX(){
	return x;
}
public int getY(){
	return y;
}
public int getweight(){
	return weight;
}
}
