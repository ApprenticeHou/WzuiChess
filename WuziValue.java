package WuziChess;

public class WuziValue {
	//��ȡ��ǰ���ӵ������ֵ
private int x,y,weight;
//x-��ǰx���꣬y-��ǰy���꣬weight-��ǰ����ֵ
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
