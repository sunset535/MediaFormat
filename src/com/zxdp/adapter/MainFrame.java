package com.zxdp.adapter;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Panel;
import java.awt.Point;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.UnsupportedEncodingException;

public class MainFrame extends Frame implements ActionListener{

	private static final long serialVersionUID = 1L;

	private TextField tf1;
	private TextField tf2;
	private Button selectOriginFileBtn;
	private Button selectGoalFilePathBtn;
	private Button startConvert;
	private boolean convertStatus;
	
	private Panel panel1;
	private Panel panel2;
	private Panel panel3;
	
	private FileDialog openFileDialog;
	private FileDialog saveFileDialog;
	
	public MainFrame(String title) throws HeadlessException, UnsupportedEncodingException {
		//panel1 高度150 
		panel1 = new Panel();
		panel1.setSize(700, 150);
		panel1.setLocation(0, 40);
		tf1 = new TextField(10);
		tf1.setSize(500, 30);
		tf1.setLocation(50, 20);
		Font font=new Font("微软雅黑",Font.LAYOUT_LEFT_TO_RIGHT,15);
		tf1.setFont(font);
		selectOriginFileBtn = new Button("选择文件");
		selectOriginFileBtn.setSize(100, 30);
		selectOriginFileBtn.setLocation(570, 20);
		
		panel1.setLayout(null);
		panel1.add(tf1);
		panel1.add(selectOriginFileBtn);

		//panel2 高度200
		panel2 = new Panel();
		panel2.setSize(700, 200);
		panel2.setLocation(0, 150);
		panel2.setBackground(Color.black);
		
		//panel3 高度150
		panel3 = new Panel();
		panel3.setSize(700, 150);
		panel3.setLocation(0, 350);
		tf2 = new TextField(10);
		tf2.setSize(400, 30);
		tf2.setLocation(20, 410);
		selectGoalFilePathBtn = new Button("选择保存目录");
		selectGoalFilePathBtn.setSize(100, 30);
		selectGoalFilePathBtn.setLocation(440, 410);
		startConvert = new Button("开始转换");
		startConvert.setSize(100, 30);
		startConvert.setLocation(560, 410);
		panel3.setLayout(null);
		panel3.add(tf2);
		panel3.add(selectGoalFilePathBtn);
		panel3.add(startConvert);
		
		
		openFileDialog = new FileDialog(this, "点评宝-打开文件", FileDialog.LOAD);
		//openFileDialog.setFilenameFilter(new VideoFilter());
		openFileDialog.setVisible(false);
		openFileDialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				openFileDialog.setVisible(false);
			}
		});
		
		saveFileDialog = new FileDialog(this, "点评宝-保存文件", FileDialog.SAVE);
		saveFileDialog.setVisible(false);
		saveFileDialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				saveFileDialog.setVisible(false);
			}
		});
		
		//添加监听器
		selectOriginFileBtn.addActionListener(this);
		selectGoalFilePathBtn.addActionListener(this);
		startConvert.addActionListener(this);
		
		
		add(panel1);
		add(panel2);
		add(panel3);
		setTitle(title);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  
		setBounds(screenSize.width/2-350, screenSize.height/2-250,700,500);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==selectOriginFileBtn){
			String filePath = "";
			openFileDialog.setVisible(true);
			if (openFileDialog.getFile() != null){
				filePath = openFileDialog.getDirectory() + openFileDialog.getFile();
				tf1.setText(filePath);
			}
		}
		
		if(e.getSource()==selectGoalFilePathBtn){
			saveFileDialog.setVisible(true);
		}
		
	}
	
	public Point getFramePoint(Frame fm){
		Point point = fm.getLocation();
		return point;
	}
	
	public Point getDialogLocation(Point fmPoint){
		Point point = new Point();
		return point;
	}
	
	
}
