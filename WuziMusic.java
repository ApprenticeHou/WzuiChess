package WuziChess;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class WuziMusic {
private Clip clip;//可以编辑音频，也可以循环播放音乐
/*
 * 开始播放音乐传入一个音乐的名字进行播放
 * 可以进行循环播放
 */
public WuziMusic(String music){
	try {
		AudioInputStream as=AudioSystem.getAudioInputStream(new File("C:\\Users\\DELL\\Downloads\\五子棋\\"+music));
		clip= AudioSystem.getClip();
		clip.open(as);
//		if(music=="背景音乐2.wav")clip.start();
//		if(music=="背景音乐3.wav")clip.start();
//		if(music=="围棋少年.wav")clip.start();
		if(music=="win.wav")
			clip.start();
		else if(music=="下棋.wav")
		clip.start();
		else
			clip.loop(100);
	} catch (UnsupportedAudioFileException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	} catch (IOException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	} catch (LineUnavailableException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
}

 //关闭音乐
 
public void stop(){
	clip.stop();
	clip.close();
}
}
