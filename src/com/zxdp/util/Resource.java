package com.zxdp.util;

import java.io.IOException;
import java.net.URL;

public class Resource {
	public String getResource() throws IOException {
		URL fileURL = this.getClass().getResource("/resource/ffmpeg/bin/ffmpeg.exe");
		return fileURL.getFile();
	}
}