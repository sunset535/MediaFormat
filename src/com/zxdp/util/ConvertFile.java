package com.zxdp.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConvertFile {
	
	public static String executeCMD(List<String> command) {
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(command);
			builder.redirectErrorStream(true);
			Process p = builder.start();

			BufferedReader buf = null; // 保存ffmpeg的输出结果流
			String line = null;
			buf = new BufferedReader(new InputStreamReader(p.getInputStream()));
			StringBuffer sb = new StringBuffer();
			while ((line = buf.readLine()) != null) {
				System.out.println(line);
				sb.append(line);
				continue;
			}
			int ret = p.waitFor();// 这里线程阻塞，将等待外部转换进程运行成功运行结束后，才往下执行
			return sb.toString();
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
	
	public static List<String> convertVideoCMD(String savePath,
			String finalName) {
		List<String> cmdList = new ArrayList<String>();
		cmdList.add(Constant.ffmpegPath);
		cmdList.add("-i");
		cmdList.add(savePath);
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
}
