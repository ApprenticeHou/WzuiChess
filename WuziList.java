package WuziChess;

import java.util.ArrayList;
import java.util.List;

public class WuziList {
private List<WuziValue> list=new ArrayList<WuziValue>();
public void addStep(WuziValue w){
	list.add(w);
}
public int getSize(){
	return list.size();
}
public void AllClear(){
	list.clear();
}
public void removeLast(){
	if(getSize()<=2){
		System.out.println("��û���أ��ٸ�ë����");
	}
	//ɾ���������µ�����
	WuziValue lastStep=list.remove(getSize()-1);
	int x=lastStep.getX();
	int y=lastStep.getY();
	WuziAlgorithm.MyWuziPiece.setPostion(x, y, 0);
//	WuziMain.������һ��ǵ����
	WuziImage.stepCount--;
	//ɾ���û����µ�����
	if(getSize()/2!=0){
		lastStep=list.remove(getSize()-1);
		x=lastStep.getX();
		y=lastStep.getY();
		WuziAlgorithm.MyWuziPiece.setPostion(x, y, 0);
//		WuziMain.������һ��ǵ����
		WuziImage.stepCount--;
		}
}
}
