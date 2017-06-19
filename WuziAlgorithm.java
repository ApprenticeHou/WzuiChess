package WuziChess;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

public class WuziAlgorithm extends MouseAdapter{
public static WuziPiece MyWuziPiece;
private static WuziBoard MyWuziBoard;
private static WuziPanel MyWuziPanel;
public static WuziList MyWuziList=new WuziList();
private final static int MAX=1<<20;
public static boolean CanGo;
public WuziAlgorithm(WuziPiece MyWuziPiece,WuziBoard MyWuziBoard,WuziPanel MyWuziPanel){
	WuziAlgorithm.MyWuziBoard=MyWuziBoard;
	WuziAlgorithm.MyWuziPiece=MyWuziPiece;
	WuziAlgorithm.MyWuziPanel=MyWuziPanel;
	CanGo=true;
}
/*
 * ���췽��
 */
public WuziAlgorithm(){}
/*
 * ���� Javadoc��
 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
 * ����������Ч��
 */
public void mouseClicked(MouseEvent m){
	if(!CanGo)
		return;
	int chessmax=(int)(14*WuziBoard.Inc+39);
	int x=(int)(m.getX()-WuziBoard.Left+10);
	int y=m.getY()-20;
	if(x*y<0||y>chessmax||x>chessmax)
		return;
	int Inc=(int)WuziBoard.Inc;
	int xNum=x/Inc;
	int yNum=y/Inc;
	if(MyWuziPiece.getPostionFlag(xNum, yNum)!=0){
		JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "�����˻��£��᲻���氡������");
		return;
	}
	WuziValue wv=new WuziValue(xNum, yNum, 0);
	MyWuziList.addStep(wv);
	MyWuziPiece.setPostion(xNum, yNum, 1);
	WuziImage.stepCount++;
	if(WuziImage.stepCount==225)
	{
		WuziImage.message="ƽ�֣�����ѽ����ɱһ�֣�";
		CanGo=false;
	}	
	if(isOver(MyWuziPiece.getAllFlag(),xNum,yNum))
		new WuziMusic("win.wav");
	display();
	if(WuziImage.level==0)
		getNext();
	else if(WuziImage.level==1)
		getSearchNext(1);
	else if(WuziImage.level==2)
		getMaxMinSearch(4);
}
/*
 * ��һ���˹������㷨
 * ѡȡ���м�ֵ��һ����
 */
public void getNext(){
	if(!CanGo)
		return;
	int idx=-1,idy=-1,idMax=-MAX;
	for(int x=0;x<15;x++)
		for(int y=0;y<15;y++)
	if(MyWuziPiece.getPostionFlag(x, y)==0){
		MyWuziPiece.setPostion(x, y, 2);
		int numAl=Evaluate(MyWuziPiece.getAllFlag(), x, y);
		MyWuziPiece.setPostion(x, y, 1);
		int numPeople=Evaluate(MyWuziPiece.getAllFlag(), x, y);
		if((numPeople+numAl)>idMax){
			idx=x;
			idy=y;
			idMax=numPeople+numAl;
		}
		MyWuziPiece.setPostion(x, y, 0);
	}
	if(idx==-1&&idy==-1){
		WuziImage.message="ƽ�֣�����ѽ����ɱһ�֣�";
		CanGo=false;
		return;	
	}
	new WuziMusic("����.wav");
	WuziValue wv=new WuziValue(idx, idy, idMax);
	MyWuziList.addStep(wv);
	WuziImage.stepCount++;
	MyWuziPiece.setPostion(idx, idy, 2);
	if(isOver(MyWuziPiece.getAllFlag(), idx, idy)){
		new WuziMusic("win.wav");
		CanGo=false;
	}
	display();
}
/*
 * ������ʾ����
 * ����Ϊ��һЩ���׵�
 */
public void display(){
	MyWuziPanel.display(MyWuziBoard, MyWuziPiece);
}
/*
 * �ж���Ϸ�Ƿ����
 * @return �����Ƿ����
 * ͨ���жϸõ��ˮƽ����ֱ��б45�ȷ����Ƿ����������ߵ�����
 */
public boolean isOver(int[][] i,int x,int y){
	boolean isover=false;
	//ˮƽ����
	int k=1,num=1;
	while(x-k>=0&&i[x][y]==i[x-k][y]){
		k++;num++;
	}
	WuziImage.LineLeft=new WuziValue(x-k+1, y, 0);
	k=1;
	while(x+k<15&&i[x][y]==i[x+k][y]){
		k++;
		num++;
	}
	WuziImage.LineRight=new WuziValue(x+k-1, y, 0);
	if(num>=5)
		isover=true;
	//��ֱ����
	if(!isover){
		k=1;
		num=1;
		while(y-k>=0&&i[x][y]==i[x][y-k]){
			k++;num++;
		}
		WuziImage.LineLeft=new WuziValue(x, y-k+1, 0);
		k=1;
		while(y+k<15&&i[x][y]==i[x][y+k]){
			k++;
			num++;
		}
		WuziImage.LineRight=new WuziValue(x, y+k-1, 0);
		if(num>=5)
			isover=true;
	}
	//45�ȷ���
	if(!isover){
		k=1;
		num=1;
		while(y-k>=0&&x-k>=0&&i[x][y]==i[x-k][y-k]){
			k++;num++;
		}
		WuziImage.LineLeft=new WuziValue(x-k+1, y-k+1, 0);
		k=1;
		while(y+k<15&&x+k<15&&i[x][y]==i[x+k][y+k]){
			k++;
			num++;
		}
		WuziImage.LineRight=new WuziValue(x+k-1, y+k-1, 0);
		if(num>=5)
			isover=true;
	}
	//135�ȷ���
	if(!isover){
		k=1;
		num=1;
		while(y-k>=0&&x+k<15&&i[x][y]==i[x+k][y-k]){
			k++;num++;
		}
		WuziImage.LineLeft=new WuziValue(x+k-1, y-k+1, 0);
		k=1;
		while(y+k<15&&x-k>=0&&i[x][y]==i[x][y+k]){
			k++;
			num++;
		}
		WuziImage.LineRight=new WuziValue(x-k+1, y+k-1, 0);
		if(num>=5)
			isover=true;
	}
	//�ж�һ����˭Ӯ��
	if(isover)
	{
		if(i[x][y]==1)
			WuziImage.message="С���ӣ��µĿ���ѽ,���Ѷȵ��������սһ�֣�";
		else
			WuziImage.message="���˰ɣ����е�һ���Ѷ�";
		CanGo=false;
		WuziImage.isGameOver=true;
	}
	return isover;
}
/*
 * ��ȡ�����е����ֵ
 */
public int getMax(int a,int b){
	return a>b?a:b;
}
/*
 * ���Ѿ��µ���ֽ��м�ֵ�ж�
 * point�������˵Ŀ�����
 * num�����Ѿ����ɵ���Ŀ
 * @return ����ö��ӵļ�ֵ
 */
public int getVaule(int point,int num){
	if(num==1)
		return 1;
	else if(num==2)
	{
		if(point==0)
			return 3;
		else if(point==1)
			return 50;
		return 100;
	}
	else if(num==3)
	{
		if(point==0)
			return 10;
		else if(point==1)
			return 350;
		return 5000;
	}
	else if(num==4)
	{
		if(point==0)
			return 10;
		else if(point==1)
			return 8000;
		return 500000;
	}
	else if(num>=5)
		return 10000000;
	return 0;
}
/*
 * ���Ѿ����˵�Ϊ����һ�𣬵�������һ�¾��кܴ��ֵ������м�ֵ����
 * @point �������˵Ŀ�����
 * num �л������ɵ�����
 * @return ��ļ�ֵ
 */
public int  getPotentialVaule(int point,int num){
	if(point<=0||num<=2)
		return 0;
	else if(point==1){
		if(num==3)
			return 10;
		if(num==4)
			return 100;
		else return 200;
	}
	else if(point==2){
		if(num<=3)
			return 20;
		if(num==4)
			return 1000;
		else 
			return 2000;
	}
	return 50;
}
/*
 * �����µ���Ϊ���ĵ㣬���������ļ�ֵ��̰���㷨 
 * iΪ�������飬x��y�ֱ�Ϊ����
 * @return ���ؼ�ֵ
 */
public int Evaluate(int[][] i,int x,int y){
	int value=0,num,k,point;//vaule��ֵ��num��������k������point�˵���
	boolean left=false,right=false;
	//ˮƽ�����ֵ������
	k=1;num=1;
	while(x-k>=0&&i[x][y]==i[x-k][y])
	{
		k++;
		num++;
	}
	if(x-k>=0&&i[x-k][y]==0)
		left=true;
	k=1;
	while(x+k<15&&i[x][y]==i[x+k][y])
	{
		k++;
		num++;
	}
	if(x+k<15&&i[x+k][y]==0)
		right=true;
	num=num>5?5:num;
	point=((left&&right)?2:(left||right)?1:0);
	value+=getVaule(point, num);
	
	
	//��ֱ�����ֵ�ü���
	k=1;num=1;
	left=false;right=false;
	while(y-k>=0&&i[x][y]==i[x][y-k])
	{
		k++;
		num++;
	}
	if(y-k>=0&&i[x][y-k]==0)
		left=true;
	k=1;
	while(y+k<15&&i[x][y]==i[x][y+k])
	{
		k++;
		num++;
	}
	if(y+k<15&&i[x][y+k]==0)
		right=true;
	num=num>5?5:num;
	point=((left&&right)?2:(left||right)?1:0);
	value+=getVaule(point, num);
	
	
	//45�ȷ���ļ���
	k=1;num=1;
	left=false;right=false;
	while(y-k>=0&&x-k>=0&&i[x][y]==i[x-k][y-k])
	{
		k++;
		num++;
	}
	if(y-k>=0&&x-k>=0&&i[x-k][y-k]==0)
		left=true;
	k=1;
	while(x+k<15&&y+k<15&&i[x][y]==i[x+k][y+k])
	{
		k++;
		num++;
	}
	if(x+k<15&&y+k<15&&i[x+k][y+k]==0)
		right=true;
	num=num>5?5:num;
	point=((left&&right)?2:(left||right)?1:0);
	value+=getVaule(point, num);
	
	
	//135�ȷ���ֵ�ü���
	k=1;num=1;
	left=false;right=false;
	while(x+k<15&&y-k>=0&&i[x][y]==i[x+k][y-k])
	{
		k++;
		num++;
	}
	if(y-k>=0&&x+k<15&&i[x+k][y-k]==0)
		left=true;
	k=1;
	while(x-k>=0&&y+k<15&&i[x][y]==i[x-k][y+k])
	{
		k++;
		num++;
	}
	if(x-k>=0&&y+k<15&&i[x-k][y+k]==0)
		right=true;
	num=num>5?5:num;
	point=((left&&right)?2:(left||right)?1:0);
	value+=getVaule(point, num);
	
	
	//����Ǳ�ڵ��ܹ�ͨ������һ�����ܿ���������ֵ,ˮƽ����
	int add,leftadd=0,rightadd=0;//addΪ�ɼӵ���Ŀ
	int rvalue=0,lvalue=0;//���ҵ�ֵѡȡ���ֵ
	boolean left2=false;
	boolean right2=false;
	left=false;right=false;k=1;num=1;
	//ˮƽ����ļ���,��߼���
	while(x-k>=0&&i[x][y]==i[x-k][y]){
		k++;
		num++;
	}
	if(x-k>=0&&i[x-k][y]==0){
		left=true;
		add=k+1;
		while(x-add>=0&&i[x][y]==i[x-add][y]){
			add++;
			leftadd++;
		}
		if(x-add>=0&&i[x-add][y]==0)
			left2=true;
	}
	//�ұߵļ���
	k=1;
	while(x+k<15&&i[x][y]==i[x+k][y]){
		k++;
		num++;
	}
	if(x+k<15&&i[x+k][y]==0){
		right=true;
		add=k+1;
		while(x+add<15&&i[x][y]==i[x+add][y]){
			add++;
			rightadd++;
		}
		if(x+add<15&&i[x+add][y]==0)
			right2=true;
	}
	if(rightadd>0||leftadd>0){
	point=((right2&&left)?2:(right2||left?1:0));
	rvalue=getPotentialVaule(point, num+leftadd+1);
	point=((right&&left2)?2:(right||left2?1:0));
	lvalue=getPotentialVaule(point, num+rightadd+1);
	value+=getMax(rvalue, lvalue);
	}
	
	//Ǳ�ڵĿ����ɵ�������ֱ����
	k=1;
	num=1;
	rvalue=0;
	lvalue=0;
	left2=false;
	right2=false;
	left=false;
	right=false;
	//��ֱ����ļ���,�±߼���
		while(y-k>=0&&i[x][y]==i[x][y-k]){
			k++;
			num++;
		}
		if(y-k>=0&&i[x][y-k]==0){
			left=true;
			add=k+1;
			while(y-add>=0&&i[x][y]==i[x][y-add]){
				add++;
				leftadd++;
			}
			if(y-add>=0&&i[x][y-add]==0)
				left2=true;
		}
		//�ϱߵļ���
		k=1;
		while(y+k<15&&i[x][y]==i[x][y+k]){
			k++;
			num++;
		}
		if(y+k<15&&i[x][y+k]==0){
			right=true;
			add=k+1;
			while(y+add<15&&i[x][y]==i[x][y+add]){
				add++;
				rightadd++;
			}
			if(y+add<15&&i[x][y+add]==0)
				right2=true;
		}
		if(rightadd>0||leftadd>0){
		point=((right2&&left)?2:(right2||left?1:0));
		rvalue=getPotentialVaule(point, num+leftadd+1);
		point=((right&&left2)?2:(right||left2?1:0));
		lvalue=getPotentialVaule(point, num+rightadd+1);
		value+=getMax(rvalue, lvalue);
		}
	
		
		//Ǳ�ڵĿ����ɵ�����45�ȷ���
		k=1;
		num=1;
		rvalue=0;
		lvalue=0;
		left2=false;
		right2=false;
		left=false;
		right=false;
		//45�ȷ���ļ���,б�±߼���
			while(x-k>=0&&y-k>=0&&i[x][y]==i[x-k][y-k]){
				k++;
				num++;
			}
			if(x-k>=0&&y-k>=0&&i[x-k][y-k]==0){
				left=true;
				add=k+1;
				while(x-add>=0&&y-add>=0&&i[x][y]==i[x-add][y-add]){
					add++;
					leftadd++;
				}
				if(x-add>=0&&y-add>=0&&i[x-add][y-add]==0)
					left2=true;
			}
			//б�Ϸ���ļ���
			k=1;
			while(x+k<15&&y+k<15&&i[x][y]==i[x+k][y+k]){
				k++;
				num++;
			}
			if(x+k<15&&y+k<15&&i[x+k][y+k]==0){
				right=true;
				add=k+1;
				while(y+add<15&&x+add<15&&i[x][y]==i[x+add][y+add]){
					add++;
					rightadd++;
				}
				if(y+add<15&&x+add<15&&i[x+add][y+add]==0)
					right2=true;
			}
			if(rightadd>0||leftadd>0){
			point=((right2&&left)?2:(right2||left?1:0));
			rvalue=getPotentialVaule(point, num+leftadd+1);
			point=((right&&left2)?2:(right||left2?1:0));
			lvalue=getPotentialVaule(point, num+rightadd+1);
			value+=getMax(rvalue, lvalue);
			}
			
			
			
			//Ǳ�ڵĿ����ɵ�����135�ȷ���
			k=1;
			num=1;
			rvalue=0;
			lvalue=0;
			left2=false;
			right2=false;
			left=false;
			right=false;
			//135�ȷ���ļ���,б�Ϸ������
				while(x-k>=0&&y+k<15&&i[x][y]==i[x-k][y+k]){
					k++;
					num++;
				}
				if(x-k>=0&&y+k<15&&i[x-k][y+k]==0){
					left=true;
					add=k+1;
					while(x-add>=0&&y+add<15&&i[x][y]==i[x-add][y+add]){
						add++;
						leftadd++;
					}
					if(x-add>=0&&y+add<15&&i[x-add][y+add]==0)
						left2=true;
				}
				//б�·���ļ���
				k=1;
				while(x+k<15&&y-k>=0&&i[x][y]==i[x+k][y-k]){
					k++;
					num++;
				}
				if(x+k<15&&y-k>=0&&i[x+k][y-k]==0){
					right=true;
					add=k+1;
					while(x+add<15&&y-add>=0&&i[x][y]==i[x+add][y-add]){
						add++;
						rightadd++;
					}
					if(y-add>=0&&x+add<15&&i[x+add][y-add]==0)
						right2=true;
				}
				if(rightadd>0||leftadd>0){
				point=((right2&&left)?2:(right2||left?1:0));
				rvalue=getPotentialVaule(point, num+leftadd+1);
				point=((right&&left2)?2:(right||left2?1:0));
				lvalue=getPotentialVaule(point, num+rightadd+1);
				value+=getMax(rvalue, lvalue);
				}
			
			return value;
}

/*
 * �ڶ����˹����ܵ��㷨��ֱ������������stepLengthΪ���������ĳ���
 */
public void getSearchNext(int stepLength){
	if(!CanGo)
		return;
	WuziValue Next=derectSearch(MyWuziPiece.getAllFlag(), true, stepLength);
	int nextX=Next.getX();
	int nextY=Next.getY();
	new WuziMusic("����.wav");
	if(nextX==-1&&nextY==-1){
		if(WuziImage.stepCount==225)
		{
			WuziImage.message="ƽ�֣�����ѽ����ɱһ�֣�";
			CanGo=false;
		}
		return;
	}
	MyWuziList.addStep(Next);//��������
	WuziImage.stepCount++;
	MyWuziPiece.setPostion(nextX, nextY, 2);//������
	if(isOver(MyWuziPiece.getAllFlag(), nextX, nextY))
	new WuziMusic("win.wav");
	display();
	}
	//ֱ�ӱ��ѣ�ÿ���㶼����
private	WuziValue derectSearch(int[][] i,boolean who,int deepth){
	if(deepth==0){
          int idx=-1,idy=-1,idMax=-MAX;
         for(int x=0;x<15;x++)//�����ҳ����м�ֵ��һ�����������
           for(int y=0;y<15;y++){
            if(i[x][y]==0){//������δ�¹��ĵ�
			i[x][y]=1;
			int numPoeple=Evaluate(i, x, y);//���ڴ˴��µĵõ���ֵ
			i[x][y]=2;
			int numAI=Evaluate(i, x, y);//�˹������ڴ˴��µ�ֵ
			if(numAI+numPoeple>idMax){
				idx=x;
				idy=y;
				idMax=numAI+numPoeple;
			}
			i[x][y]=0;
            }
           }
		return new WuziValue(idx, idy, idMax);
		}
	//Steplength����������Ĳ��������whoΪtrue��stepLength��Ϊż�������õݹ鷽�����ҳ����ŵ������
	//���ص�����������ĵ�������꼰ֵ
	int idx=-1;int idy=-1;int idMax=-MAX;
	WuziValue wv=new WuziValue(idx, idy, idMax);
	for(int x=0;x<15;x++)
		for(int y=0;y<15;y++){
			if(i[x][y]==0){
				if(who)
					i[x][y]=2;
				else
					i[x][y]=1;
				WuziValue tem=derectSearch(i, !who, deepth-1);
				i[x][y]=0;
				if(tem.getweight()>wv.getweight())
				wv=tem;
		}
		}
	return wv;
}
	
	


/*
 * �������˹����ܷ���
 * �˹������м���С����������ǰ��stepLength��
 * �Ƚ�δ�µ������������ֵ����
 * ���ҽ������������У���������
 * ֮����������չ��ȣ��Ĳ������Ϊ����������
 */
public void getMaxMinSearch(int deepth){
	if(!CanGo)
		return;
	WuziValue Next=MaxMinSearch(MyWuziPiece.getAllFlag(), true, deepth);
	int nextX=Next.getX();
	int nextY=Next.getY();
	new WuziMusic("����.wav");
	if(nextX==-1&&nextY==-1){
		if(WuziImage.stepCount==225)
		{
			WuziImage.message="ƽ�֣�����ѽ����ɱһ�֣�";
			CanGo=false;
		}
		return;
	}
	MyWuziList.addStep(Next);//��������
	WuziImage.stepCount++;
	MyWuziPiece.setPostion(nextX, nextY, 2);//������
	
	if(isOver(MyWuziPiece.getAllFlag(), nextX, nextY))
		new WuziMusic("win.wav");
	display();
}
/*
 * ����С��������ʵ��
 */
private WuziValue MaxMinSearch(int[][] i,boolean who,int deepth){
	if(WuziImage.stepCount<=1)
		return derectSearch(i,true,0);
	else {
		if(deepth==0){
	//�����ҳ����м�ֵ��һ����
	int idx=-1,idy=-1,idMax=-MAX;
	for(int x=0;x<15;x++){
		for(int y=0;y<15;y++){
			if(i[x][y]==0){
				//����Ai���м�ֵ��һ����
				i[x][y]=2;
				int numAI=Evaluate(i, x, y);
				i[x][y]=1;
				int numPeople=Evaluate(i, x, y);
				if(numPeople+numAI>=idMax)
				{
					idx=x;
					idy=y;
					idMax=numPeople+numAI;
				}
				i[x][y]=0;
			}
		}
	}	
	return new WuziValue(idx, idy, idMax);
	}
		
		WuziValue last=new WuziValue(-1, -1, -MAX);
		int numAl,numPeople;
		ArrayList<WuziValue> list=new ArrayList<>();
		for(int x=0;x<15;x++)
			for(int y=0;y<15;y++){
				if(i[x][y]==0){
					i[x][y]=2;
					numAl=Evaluate(i, x, y);
					i[x][y]=1;
					numPeople=Evaluate(i, x, y);
					list.add(new WuziValue(x, y, numAl+numPeople));
					i[x][y]=0;
				}
			}
		Collections.sort(list, new WuziCompare());
		int num=list.size()>=5?5:list.size();
				for(int x=0;x<num;x++){
			WuziValue wv=list.get(x);
			if(who)
			i[wv.getX()][wv.getY()]=2;
			else
				i[wv.getX()][wv.getY()]=1;
			WuziValue tem=MaxMinSearch(i, !who, deepth-1);
			i[wv.getX()][wv.getY()]=0;
			if(last.getweight()<tem.getweight()){
				last=tem;
			}
		}
		return last;
	
}
}
}







