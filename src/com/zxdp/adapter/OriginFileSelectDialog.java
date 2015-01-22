package com.zxdp.adapter;

import java.awt.FileDialog;
import java.awt.Point;

import javax.swing.JFrame;

public class OriginFileSelectDialog {
	
	public OriginFileSelectDialog(JFrame fm, String title, String defDir, String fileType){
		openFile(fm, title, defDir, fileType);
	}

	public String openFile(JFrame fm, String title, String defDir, String fileType) {

		String sFile = "";
		Point pLocation = getPoint(fm);
		
		FileDialog fd = new FileDialog(fm, title, FileDialog.LOAD);
		fd.setLocation(pLocation);
		fd.setFile(fileType);
		fd.setDirectory(defDir);
		fd.setResizable(true);
		fd.setVisible(true);
		
		if (fd.getFile() != null)
			sFile = fd.getDirectory() + fd.getFile();
		// sFile = fd.getDirectory() + System.getProperty("file.separator") + fd.getFile();
		return sFile;
	}
	
	public Point getPoint(JFrame frame){
		Point point = frame.getLocation();
		return point;
	}
	
	public Point location(Point fmPoint){
		Point point = new Point();
		return point;
	}
}
