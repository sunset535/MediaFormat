package com.zxdp.util;

import java.io.IOException;
import java.net.URL;

public class Constant {

	public static String systemIconUrl = System.getProperty("user.dir") + "/Source/image/logo.png";
	public static int goalVideoWidth = 700;
	public static int goalVideoHeight = 400;
	//public static String ffmpegPath = Constant.class.getResource("/").getPath()+ "resource/ffmpeg/bin/ffmpeg";
	public static String ffmpegPath = System.getProperty("user.dir") +"/config/ffmpeg/bin/ffmpeg";

}
