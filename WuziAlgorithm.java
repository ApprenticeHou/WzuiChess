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
 * 构造方法
 */
public WuziAlgorithm(){}
/*
 * （非 Javadoc）
 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
 * 下棋的鼠标点击效果
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
		JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "有棋了还下，会不会玩啊！！！");
		return;
	}
	WuziValue wv=new WuziValue(xNum, yNum, 0);
	MyWuziList.addStep(wv);
	MyWuziPiece.setPostion(xNum, yNum, 1);
	WuziImage.stepCount++;
	if(WuziImage.stepCount==225)
	{
		WuziImage.message="平局，厉害呀！再杀一局！";
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
 * 第一种人工智能算法
 * 选取最有价值的一步棋
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
		WuziImage.message="平局，厉害呀！再杀一局！";
		CanGo=false;
		return;	
	}
	new WuziMusic("下棋.wav");
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
 * 重新显示界面
 * 自认为有一些不妥当
 */
public void display(){
	MyWuziPanel.display(MyWuziBoard, MyWuziPiece);
}
/*
 * 判断游戏是否结束
 * @return 返回是否结束
 * 通过判断该点的水平，竖直，斜45度方向是否有五子连线的棋子
 */
public boolean isOver(int[][] i,int x,int y){
	boolean isover=false;
	//水平方向
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
	//竖直方向
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
	//45度方向
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
	//135度方向
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
	//判断一下是谁赢了
	if(isover)
	{
		if(i[x][y]==1)
			WuziImage.message="小伙子，下的可以呀,把难度调掉最大再战一局！";
		else
			WuziImage.message="输了吧！不行调一调难度";
		CanGo=false;
		WuziImage.isGameOver=true;
	}
	return isover;
}
/*
 * 获取两个中的最大值
 */
public int getMax(int a,int b){
	return a>b?a:b;
}
/*
 * 对已经下的棋局进行价值判断
 * point代表两端的开口数
 * num代表已经连成的数目
 * @return 代表该段子的价值
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
 * 对已经下了的为连成一起，但是再下一下就有很大价值的棋进行价值评估
 * @point 代表两端的开口数
 * num 有机会连成的棋数
 * @return 棋的价值
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
 * 以已下的棋为中心点，计算给区域的价值，贪心算法 
 * i为输入数组，x，y分别为坐标
 * @return 返回价值
 */
public int Evaluate(int[][] i,int x,int y){
	int value=0,num,k,point;//vaule价值，num棋子数，k计数，point端点数
	boolean left=false,right=false;
	//水平方向的值得评估
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
	
	
	//竖直方向的值得计算
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
	
	
	//45度方向的计算
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
	
	
	//135度方向值得计算
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
	
	
	//计算潜在的能够通过再下一个就能看连起来的值,水平方向
	int add,leftadd=0,rightadd=0;//add为可加的数目
	int rvalue=0,lvalue=0;//左右的值选取最大值
	boolean left2=false;
	boolean right2=false;
	left=false;right=false;k=1;num=1;
	//水平方向的计算,左边计算
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
	//右边的计算
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
	
	//潜在的可连成的区域，竖直方向
	k=1;
	num=1;
	rvalue=0;
	lvalue=0;
	left2=false;
	right2=false;
	left=false;
	right=false;
	//竖直方向的计算,下边计算
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
		//上边的计算
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
	
		
		//潜在的可连成的区域，45度方向
		k=1;
		num=1;
		rvalue=0;
		lvalue=0;
		left2=false;
		right2=false;
		left=false;
		right=false;
		//45度方向的计算,斜下边计算
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
			//斜上方向的计算
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
			
			
			
			//潜在的可连成的区域，135度方向
			k=1;
			num=1;
			rvalue=0;
			lvalue=0;
			left2=false;
			right2=false;
			left=false;
			right=false;
			//135度方向的计算,斜上方向计算
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
				//斜下方向的计算
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
 * 第二种人工智能的算法，直接向下搜索，stepLength为向下搜索的长度
 */
public void getSearchNext(int stepLength){
	if(!CanGo)
		return;
	WuziValue Next=derectSearch(MyWuziPiece.getAllFlag(), true, stepLength);
	int nextX=Next.getX();
	int nextY=Next.getY();
	new WuziMusic("下棋.wav");
	if(nextX==-1&&nextY==-1){
		if(WuziImage.stepCount==225)
		{
			WuziImage.message="平局，厉害呀！再杀一局！";
			CanGo=false;
		}
		return;
	}
	MyWuziList.addStep(Next);//保存棋子
	WuziImage.stepCount++;
	MyWuziPiece.setPostion(nextX, nextY, 2);//画棋子
	if(isOver(MyWuziPiece.getAllFlag(), nextX, nextY))
	new WuziMusic("win.wav");
	display();
	}
	//直接爆搜，每个点都遍历
private	WuziValue derectSearch(int[][] i,boolean who,int deepth){
	if(deepth==0){
          int idx=-1,idy=-1,idMax=-MAX;
         for(int x=0;x<15;x++)//遍历找出最有价值的一个点进行下棋
           for(int y=0;y<15;y++){
            if(i[x][y]==0){//必须是未下过的点
			i[x][y]=1;
			int numPoeple=Evaluate(i, x, y);//人在此处下的得到的值
			i[x][y]=2;
			int numAI=Evaluate(i, x, y);//人工智能在此处下的值
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
	//Steplength是向下联想的步数，如果who为true则stepLength必为偶数，运用递归方法，找出最优的下棋点
	//返回的是最优下棋的点包括坐标及值
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
 * 第三种人工智能方法
 * 人工智能中极大极小法搜索，向前看stepLength步
 * 先将未下的棋进行评估价值排序
 * 并且将其存入数组表中，进行排序
 * 之后来进行扩展深度，四层最好因为是五子连棋
 */
public void getMaxMinSearch(int deepth){
	if(!CanGo)
		return;
	WuziValue Next=MaxMinSearch(MyWuziPiece.getAllFlag(), true, deepth);
	int nextX=Next.getX();
	int nextY=Next.getY();
	new WuziMusic("下棋.wav");
	if(nextX==-1&&nextY==-1){
		if(WuziImage.stepCount==225)
		{
			WuziImage.message="平局，厉害呀！再杀一局！";
			CanGo=false;
		}
		return;
	}
	MyWuziList.addStep(Next);//保存棋子
	WuziImage.stepCount++;
	MyWuziPiece.setPostion(nextX, nextY, 2);//画棋子
	
	if(isOver(MyWuziPiece.getAllFlag(), nextX, nextY))
		new WuziMusic("win.wav");
	display();
}
/*
 * 极大极小博弈树的实现
 */
private WuziValue MaxMinSearch(int[][] i,boolean who,int deepth){
	if(WuziImage.stepCount<=1)
		return derectSearch(i,true,0);
	else {
		if(deepth==0){
	//遍历找出最有价值的一个点
	int idx=-1,idy=-1,idMax=-MAX;
	for(int x=0;x<15;x++){
		for(int y=0;y<15;y++){
			if(i[x][y]==0){
				//对于Ai最有价值的一个点
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







