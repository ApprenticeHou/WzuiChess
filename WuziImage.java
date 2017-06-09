package WuziChess;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

public class WuziImage {
public static Date begin;//开始时间
public static Date end;//结束时间
public static WuziValue LineRight;//右端点
public static WuziValue LineLeft;//左端点
public static boolean isGameOver;//是否结束
public static int ColorBackBoard[][]={{132,92,32},{9,78,2},{2,69,189}};//背景颜色
public static int Colorwindow[][]={{123,89,59},{244,244,244},{78,78,78}};//window背景颜色
public static int level;//难度级别
public static int match;//颜色搭配
public String BackMusic;//背景音乐
public String winMusic;//胜利音乐
public String failureMusic;//失败音乐
public static int stepCount;//当前步数
public static boolean isSurrender;//是否认输
public static String message;
public static Image icon;//图标图片
public static Image blackChess;//黑棋图片
public static Image whiteChess;//白棋图片
public static Image ChessBox;//棋盒图片
public static Image whitePlayer;//白色玩家头像
public static Image blackPlayer;//黑棋玩家头像
public static Image blackBoard;//黑色棋盘
public static Image whiteBoard;//白色棋盘
public static String src="C:\\Users\\DELL\\Downloads\\五子棋\\";
static{
	try{
		isGameOver=false;
		begin=new Date();
		stepCount=0;
		level=0;
		isSurrender=false;
		match=0;
		message="";
		blackBoard=ImageIO.read(new File(src+"blackBoard.png"));
		whiteBoard=ImageIO.read(new File(src+"whiteBoard.png"));
		icon=ImageIO.read(new File(src+"icon.jpg"));
		whiteChess=ImageIO.read(new File(src+"whiteChess.png"));
		blackChess=ImageIO.read(new File(src+"blackChess.png"));
		whitePlayer=ImageIO.read(new File(src+"ai.jpg"));
		blackPlayer=ImageIO.read(new File(src+"people.jpg"));
}catch (IOException e) {
	 	e.printStackTrace();
	 }
}
}
