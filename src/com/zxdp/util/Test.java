package com.zxdp.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

class Test {
  public static void main(String[] args) throws IOException, InterruptedException{
	  //convert();
	  List<String> list = ConvertFile.convertVideoCMD("C:/Wildlife.wmv","C:/test.mp4");
	  ConvertFile.executeCMD(list);
  }
  
  public static String convert() throws IOException, InterruptedException{
	  ProcessBuilder pb = new ProcessBuilder("c://ffmpeg/bin/ffmpeg","-i","C:/Wildlife.wmv","C:/test.mp4");
	   Process p = pb.start();
	    /*
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
		                         + Integer.parseInt(hms[1]) *   60
		                         + Double.parseDouble(hms[2]);
		        System.out.println("Total duration: " + totalSecs + " seconds.");

		        // Find time as long as possible.
		        Pattern timePattern = Pattern.compile("/time=(.*?)");
		        String match;
		        while (null != (match = sc.findWithinHorizon(timePattern, 0))) {
		          double progress = Double.parseDouble(match) / totalSecs;
		          System.out.printf("Progress: %.2f%%%n", progress * 100);
		        }
		      }
		    }.start();
	    */
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
  }
}