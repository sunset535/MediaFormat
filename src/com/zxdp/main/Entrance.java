package com.zxdp.main;

import java.awt.Frame;
import java.awt.HeadlessException;
import java.io.UnsupportedEncodingException;


import com.zxdp.adapter.MainFrame;

public class Entrance {

	public static void main(String[] args) throws HeadlessException, UnsupportedEncodingException{
		//new MainFrame();
		//new MyFileDialog();
		Frame frame = new MainFrame("点评宝-视频转换器");
		/*
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(new ImageIcon("Source/image/logo.png").getImage());
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  
		frame.setLocation(screenSize.width/2-350, screenSize.height/2-250);
		frame.setLayout(null);
		frame.pack();
		*/
	}
}
