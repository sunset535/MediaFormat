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
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

import com.zxdp.util.ConvertFile;

public class MainFrame extends Frame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private TextField tf1;
	private TextField tf2;
	private Button selectOriginFileBtn;
	private Button selectGoalFilePathBtn;
	private Button startConvert;
	private boolean convertStatus;
	private Button uploader;
	private JProgressBar progressBar;
	private JLabel label1;
	private JTextArea jtextarea;

	private Panel panel1;
	private Panel panel2;
	private Panel panel3;
	private Panel panel4;

	private FileDialog openFileDialog;
	private FileDialog saveFileDialog;

	public MainFrame(String title) throws HeadlessException,
			UnsupportedEncodingException {
		// panel1 高度150
		panel1 = new Panel();
		panel1.setSize(550, 70);
		panel1.setLocation(0, 30);
		tf1 = new TextField(10);
		tf1.setSize(350, 30);
		tf1.setLocation(50, 20);
		Font font = new Font("微软雅黑", Font.LAYOUT_LEFT_TO_RIGHT, 15);
		tf1.setFont(font);
		selectOriginFileBtn = new Button("选择文件");
		selectOriginFileBtn.setSize(100, 30);
		selectOriginFileBtn.setLocation(410, 20);

		panel1.setLayout(null);
		panel1.add(tf1);
		panel1.add(selectOriginFileBtn);

		// panel2 高度200
		panel2 = new Panel();
		panel2.setSize(550, 80);
		panel2.setLocation(0, 260);
		/*
		 * HumanProgressBar p = new HumanProgressBar(); p.setValue(0);
		 * p.setBounds(15, 15, 400, 50); panel2.add(p);
		 */

		label1 = new JLabel("");
		label1.setFont(new java.awt.Font("Dialog", 0, 12));
		label1.setPreferredSize(new Dimension(450, 24));
		panel2.add(label1);
		
		jtextarea = new JTextArea(System.getProperty("user.dir"));
		jtextarea.setLineWrap(true);
		jtextarea.setFont(new java.awt.Font("Dialog", 0, 12));
		jtextarea.setPreferredSize(new Dimension(450, 100));
		jtextarea.setSize(450, 100);
		//panel2.add(jtextarea);

		progressBar = new JProgressBar();
		progressBar.setPreferredSize(new Dimension(450, 20));
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		progressBar.setValue(0);
		progressBar.setBounds(25, 35, 260, 20);
		panel2.add(progressBar);

		// panel3 高度150
		panel3 = new Panel();
		panel3.setSize(550, 80);
		panel3.setLocation(0, 100);
		tf2 = new TextField(10);
		tf2.setSize(350, 30);
		tf2.setLocation(50, 30);
		tf2.setFont(font);
		selectGoalFilePathBtn = new Button("选择保存目录");
		selectGoalFilePathBtn.setSize(100, 30);
		selectGoalFilePathBtn.setLocation(410, 30);
		panel3.setLayout(null);
		panel3.add(tf2);
		panel3.add(selectGoalFilePathBtn);

		panel4 = new Panel();
		panel4.setSize(550, 80);
		panel4.setLocation(0, 180);
		startConvert = new Button("开始转换");
		startConvert.setSize(100, 30);
		startConvert.setLocation(100, 160);
		uploader = new Button("上传");
		uploader.setSize(100, 30);
		uploader.setLocation(300, 160);
		panel4.setLayout(null);
		// panel4.add(uploader);
		panel4.add(startConvert);

		openFileDialog = new FileDialog(this, "点评宝-打开文件", FileDialog.LOAD);
		// openFileDialog.setFilenameFilter(new VideoFilter());
		openFileDialog.setVisible(false);
		openFileDialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				openFileDialog.setVisible(false);
			}
		});

		saveFileDialog = new FileDialog(this, "点评宝-保存文件", FileDialog.SAVE);
		saveFileDialog.setVisible(false);
		saveFileDialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				saveFileDialog.setVisible(false);
			}
		});

		// 添加监听器
		selectOriginFileBtn.addActionListener(this);
		selectGoalFilePathBtn.addActionListener(this);
		startConvert.addActionListener(this);

		add(panel1);
		add(panel2);
		add(panel3);
		add(panel4);
		setTitle(title);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(screenSize.width / 2 - 275, screenSize.height / 2 - 175, 550,
				350);
		setVisible(true);
		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == selectOriginFileBtn) {
			String filePath = "";
			openFileDialog.setVisible(true);
			if (openFileDialog.getFile() != null) {
				filePath = openFileDialog.getDirectory()
						+ openFileDialog.getFile();
				tf1.setText(filePath);
			}
		}

		if (e.getSource() == selectGoalFilePathBtn) {
			saveFileDialog.setVisible(true);
			String filePath = "";
			if (saveFileDialog.getFile() != null) {
				filePath = saveFileDialog.getDirectory()
						+ saveFileDialog.getFile();
				tf2.setText(filePath);
			}
		}

		if (e.getSource() == startConvert) {
			String filePath = tf1.getText();
			String savePath = tf2.getText();
			if (filePath.isEmpty()) {
				JOptionPane.showMessageDialog(null, "请选择视频文件");
			} else if (savePath.isEmpty()) {
				JOptionPane.showMessageDialog(null, "请选择保存目录");
			} else {
				List<String> command = ConvertFile.convertVideoCMD(
						tf1.getText(), tf2.getText());
				try {
					ProcessBuilder pb = new ProcessBuilder(command);
					final Process p = pb.start();
					startConvert.setEnabled(false);
					new Thread() {
						public void run() {
							Scanner sc = new Scanner(p.getErrorStream());
							Pattern durPattern = Pattern
									.compile("(?<=Duration: )[^,]*");
							String dur = sc.findWithinHorizon(durPattern, 0);
							if (dur == null)
								throw new RuntimeException(
										"Could not parse duration.");
							String[] hms = dur.split(":");
							double totalSecs = Integer.parseInt(hms[0]) * 3600
									+ Integer.parseInt(hms[1]) * 60
									+ Double.parseDouble(hms[2]);

							Pattern timePattern = Pattern
									.compile("(?<=time=)[\\d:.]*");
							String match;
							String[] matchSplit;
							while (null != (match = sc.findWithinHorizon(
									timePattern, 0))) {
								/*
								 * double progress = Double.parseDouble(match) /
								 * totalSecs;
								 */
								matchSplit = match.split(":");
								double progress = Integer
										.parseInt(matchSplit[0])
										* 3600
										+ Integer.parseInt(matchSplit[1])
										* 60
										+ Double.parseDouble(matchSplit[2])
										/ totalSecs;

								if ((int) Math.floor(progress * 100) < 100) {
									label1.setText("正在转换 "
											+ (int) Math.floor(progress * 100)
											+ "%");
								} else {
									label1.setText("视频转换完成");
									startConvert.setEnabled(true);
								}
								Rectangle labelRect = label1.getBounds();
								labelRect.x = 0;
								labelRect.y = 0;
								label1.paintImmediately(labelRect);

								progressBar.setValue((int) (Math
										.floor(progress * 100)));
								Rectangle progressRect = progressBar
										.getBounds();
								progressRect.x = 0;
								progressRect.y = 0;
								progressBar.paintImmediately(progressRect);
							}
						}
					}.start();
					
				} catch (IOException e1) {
					startConvert.setEnabled(true);
					System.out.println("发生异常，请重试!");
				} 
			}
		}
	}

	public Point getFramePoint(Frame fm) {
		Point point = fm.getLocation();
		return point;
	}

	public Point getDialogLocation(Point fmPoint) {
		Point point = new Point();
		return point;
	}

	public void DoBogusTask(int iCtr) {
		Random random = new Random(iCtr);
		for (int iValue = 0; iValue < random.nextFloat() * 10000; iValue++) {
			System.out.println("iValue=" + iValue);
		}
	}

}
