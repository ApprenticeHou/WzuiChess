package WuziChess;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class WuziMusic {
private Clip clip;//���Ա༭��Ƶ��Ҳ����ѭ����������
/*
 * ��ʼ�������ִ���һ�����ֵ����ֽ��в���
 * ���Խ���ѭ������
 */
public WuziMusic(String music){
	try {
		AudioInputStream as=AudioSystem.getAudioInputStream(new File("C:\\Users\\DELL\\Downloads\\������\\"+music));
		clip= AudioSystem.getClip();
		clip.open(as);
//		if(music=="��������2.wav")clip.start();
//		if(music=="��������3.wav")clip.start();
//		if(music=="Χ������.wav")clip.start();
		if(music=="win.wav")
			clip.start();
		else if(music=="����.wav")
		clip.start();
		else
			clip.loop(100);
	} catch (UnsupportedAudioFileException e) {
		// TODO �Զ����ɵ� catch ��
		e.printStackTrace();
	} catch (IOException e) {
		// TODO �Զ����ɵ� catch ��
		e.printStackTrace();
	} catch (LineUnavailableException e) {
		// TODO �Զ����ɵ� catch ��
		e.printStackTrace();
	}
}

 //�ر�����
 
public void stop(){
	clip.stop();
	clip.close();
}
}
