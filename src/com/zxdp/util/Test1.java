package com.zxdp.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

class Test1 {
	public static void main(String[] args) throws IOException {
		List<String> cmdList = new ArrayList<String>();
		cmdList.add("C:/ffmpeg/bin/ffmpeg");
		cmdList.add("-i");
		cmdList.add("C:/Wildlife.wmv");
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
		cmdList.add("C:/test1.mp4");
		ProcessBuilder pb = new ProcessBuilder(cmdList);
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
					double progress = Integer.parseInt(matchSplit[0]) * 3600
							+ Integer.parseInt(matchSplit[1]) * 60
							+ Double.parseDouble(matchSplit[2]) / totalSecs;
					System.out.printf("Progress: %.2f%%%n", progress * 100);
				}
			}
		}.start();
	}
}