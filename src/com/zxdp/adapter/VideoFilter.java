package com.zxdp.adapter;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VideoFilter implements FilenameFilter {
	private String[] videoTypes = { "3gp", "wmv", "mp4", "avi", "mpeg", "mov",
			"rmvb", "rmv", "asf" };
	List<String> videoTypesList = new ArrayList<String>();
	
	public boolean isVideo(String filename) {
		Collections.addAll(videoTypesList, videoTypes);
		if(filename.lastIndexOf(".")!=0){
			String fileSuffix = filename.toLowerCase().substring(filename.lastIndexOf(".")+1, filename.length());
			if (videoTypesList.contains(fileSuffix)) {
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	@Override
	public boolean accept(File arg0, String arg1) {
		return isVideo(arg1);
	}
}
