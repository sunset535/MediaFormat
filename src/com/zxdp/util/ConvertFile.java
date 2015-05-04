package com.zxdp.util;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.zxdp.adapter.MainFrame;

public class ConvertFile {

	public static double progress;
	
	
	public static String executeCMD(List<String> command) {
		try {
			/*
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(command);
			//builder.redirectErrorStream(true);
			final Process p = builder.start();
			*/
			ProcessBuilder pb = new ProcessBuilder(command);
			final Process p = pb.start();
			new Thread() {
				public void run() {

					Scanner sc = new Scanner(p.getErrorStream());
					// Find duration
					Pattern durPattern = Pattern.compile("(?<=Duration: )[^,]*");
					String dur = sc.findWithinHorizon(durPattern, 0);
					if (dur == null)
						throw new RuntimeException("Could not parse duration.");
					String[] hms = dur.split(":");
					double totalSecs = Integer.parseInt(hms[0]) * 3600
							+ Integer.parseInt(hms[1]) * 60
							+ Double.parseDouble(hms[2]);
					System.out
							.println("Total duration: " + totalSecs + " seconds.");

					// Find time as long as possible.
					Pattern timePattern = Pattern.compile("(?<=time=)[\\d:.]*");
					String match;
					String[] matchSplit;
					while (null != (match = sc.findWithinHorizon(timePattern, 0))) {
						/*
						 * double progress = Double.parseDouble(match) / totalSecs;
						 */
						matchSplit = match.split(":");
						progress = Integer.parseInt(matchSplit[0]) * 3600
								+ Integer.parseInt(matchSplit[1]) * 60
								+ Double.parseDouble(matchSplit[2]) / totalSecs;
						//System.out.println(progress);
						//System.out.printf("Progress: %.2f%%%n", progress * 100);
					}
				}
			}.start();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean convertVideoToMp4(String videoPath, String finalName) {
		List<String> command = convertVideoCMD(videoPath, finalName);
		try {
			executeCMD(command);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static List<String> convertVideoCMD(String videoPath,
			String finalName) {
		List<String> cmdList = new ArrayList<String>();
		cmdList.add(Constant.ffmpegPath);
		cmdList.add("-i");
		cmdList.add(videoPath);
		cmdList.add("-y");
		cmdList.add("-ab");
		cmdList.add("32k");
		cmdList.add("-ar");
		cmdList.add("22050");
		cmdList.add("-qscale");
		cmdList.add("10");
		cmdList.add("-s");
		cmdList.add("640*352");
		cmdList.add("-r");
		cmdList.add("30");
		cmdList.add("-f");
		cmdList.add("mp4");
		cmdList.add(finalName);
		return cmdList;
	}

	// ffmpeg -i "C:/Wildlife.wmv" -y -ab 32k -ar 22050 -qscale 10 -s 640*350 -r
	// 30 -f mp4 "C:/test1.mp4"
	//旋转视频
	public static boolean rotationVideo(String videoPath, String finalName, int type) {
		List<String> command = rotationVideoCMD(videoPath, finalName, type);
		try {
			executeCMD(command);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//旋转
	//transpose=1 顺时针旋转90度
	//transpose=2 逆时针旋转90度
	public static List<String> rotationVideoCMD(String videoPath, String finalName, int type){
		String ffmpegClock = null;
		switch(type){
		case 1:
			ffmpegClock = "transpose=1";
		case 0:
			ffmpegClock = "transpose=2";
		default:
			ffmpegClock = "transpose=1";
		}
		List<String> cmdList = new ArrayList<String>();
		cmdList.add(Constant.ffmpegPath);
		cmdList.add("-i");
		cmdList.add(videoPath);
		cmdList.add("-y");
		cmdList.add("-vf");
		cmdList.add("-r");
		cmdList.add(ffmpegClock);
		cmdList.add(finalName);
		return cmdList;
	}
}
