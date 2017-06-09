package WuziChess;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

public class WuziImage {
public static Date begin;//��ʼʱ��
public static Date end;//����ʱ��
public static WuziValue LineRight;//�Ҷ˵�
public static WuziValue LineLeft;//��˵�
public static boolean isGameOver;//�Ƿ����
public static int ColorBackBoard[][]={{132,92,32},{9,78,2},{2,69,189}};//������ɫ
public static int Colorwindow[][]={{123,89,59},{244,244,244},{78,78,78}};//window������ɫ
public static int level;//�Ѷȼ���
public static int match;//��ɫ����
public String BackMusic;//��������
public String winMusic;//ʤ������
public String failureMusic;//ʧ������
public static int stepCount;//��ǰ����
public static boolean isSurrender;//�Ƿ�����
public static String message;
public static Image icon;//ͼ��ͼƬ
public static Image blackChess;//����ͼƬ
public static Image whiteChess;//����ͼƬ
public static Image ChessBox;//���ͼƬ
public static Image whitePlayer;//��ɫ���ͷ��
public static Image blackPlayer;//�������ͷ��
public static Image blackBoard;//��ɫ����
public static Image whiteBoard;//��ɫ����
public static String src="C:\\Users\\DELL\\Downloads\\������\\";
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
