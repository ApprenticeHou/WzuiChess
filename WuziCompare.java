package WuziChess;

import java.util.Comparator;
/*
 * 用来比较两个位置的值哪个更合理
 */
public class WuziCompare implements Comparator<WuziValue>{
	@Override
	public int compare(WuziValue o1, WuziValue o2) {
		// TODO 自动生成的方法存根
		return (o1.getweight()>o2.getweight()? 1:(o1.getweight()==o2.getweight()?0:-1));
	}

}
