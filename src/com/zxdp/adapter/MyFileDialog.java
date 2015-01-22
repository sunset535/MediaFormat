package com.zxdp.adapter;

import java.awt.Component;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Point;

public class MyFileDialog extends Component {

	private static final long serialVersionUID = 1L;
	Point pLocation = new Point(100, 100);
	// Dimension dSize = new Dimension(500,400) ;
	boolean bLog = false;
	Frame fm = new Frame();

	public MyFileDialog() {
		fm.setVisible(true);
		openFile("", "", "");
	}

	/*
	 * Show an Open file dialog
	 */
	public String openFile(String title, String defDir, String fileType) {

		String sFile = "";

		log("title:" + title);
		log("defdir:" + defDir);
		log("filetype:" + fileType);

		fm.setLocation(pLocation);
		FileDialog fd = new FileDialog(fm, title, FileDialog.LOAD);
		fd.setFile(fileType);
		fd.setDirectory(defDir);
		fd.setResizable(true);
		fd.setVisible(true);
		;
		if (fd.getFile() != null)
			sFile = fd.getDirectory() + fd.getFile();
		// sFile = fd.getDirectory() + System.getProperty("file.separator") + fd.getFile();
		return sFile;
	}

	public String saveFile(String title, String defDir, String fileType) {

		String sFile = "";

		log("title:" + title);
		log("defdir:" + defDir);
		log("filetype:" + fileType);

		fm.setLocation(pLocation);
		FileDialog fd = new FileDialog(fm, title, FileDialog.SAVE);
		fd.setFile(fileType);
		fd.setDirectory(defDir);
		fd.setVisible(true);
		if (fd.getFile() != null)
			sFile = fd.getDirectory() + fd.getFile();
		// sFile = fd.getDirectory() + System.getProperty("file.separator") +
		// fd.getFile();
		return sFile;

	}

	public void setLog(boolean b) {
		bLog = b;
	}

	void log(String sMessage) {
		if (bLog)
			System.out.println(sMessage);
	}
}