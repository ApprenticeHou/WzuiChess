package WuziChess;

import java.util.Comparator;
/*
 * �����Ƚ�����λ�õ�ֵ�ĸ�������
 */
public class WuziCompare implements Comparator<WuziValue>{
	@Override
	public int compare(WuziValue o1, WuziValue o2) {
		// TODO �Զ����ɵķ������
		return (o1.getweight()>o2.getweight()? 1:(o1.getweight()==o2.getweight()?0:-1));
	}

}
