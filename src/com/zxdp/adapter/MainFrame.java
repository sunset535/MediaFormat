package com.zxdp.adapter;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1L;

	private static JButton selectOriginFileBtn;
	private static JTable table;
	private static JButton selectGoalFilePathBtn;
	private static JTextField goalFilePath;
	
	public MainFrame(){
		init();
	}
	
	public void init(){
		selectOriginFileBtn = new JButton("选择文件");
		table = new JTable();
		selectGoalFilePathBtn = new JButton("选择目录");
		goalFilePath = new JTextField();
		
		JFrame frame = new JFrame("点评宝-格式转换器");
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(selectOriginFileBtn, BorderLayout.CENTER);
		frame.setIconImage(new ImageIcon("Source/image/logo.png").getImage());
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  
		frame.setLocation(screenSize.width/2-350, screenSize.height/2-250);
		frame.setLayout(null);
		frame.pack();
		frame.setSize(700, 500);
		frame.setVisible(true);
	}
	
	
}
