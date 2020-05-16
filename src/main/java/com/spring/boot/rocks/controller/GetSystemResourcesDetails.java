package com.spring.boot.rocks.controller;

import java.io.File;

public class GetSystemResourcesDetails {

	private static String OS = System.getProperty("os.name").toLowerCase();
	private static String OSVersion = System.getProperty("os.version");

	public String getOSname() {
		return System.getProperty("os.name");
	}
	
	public String getOSVersion() {
		return OSVersion;
	}

	public double getFreeDiskSpaceInGb() {
		File file;
		if (isWindows()) {
		file = new File("c:");
		} else {
			file = new File("/");
		}
		double size = file.getFreeSpace() / (1024.0 * 1024 * 1024);
		return size;
	}

	public double getFreeDiskSpaceInMb() {
		File file;
		if (isWindows()) {
		file = new File("c:");
		} else {
			file = new File("/");
		}
		double size = file.getFreeSpace() / (1024.0 * 1024);  //.getUsableSpace()
		return size;
	}
	
	public double getTotalDiskSpaceInGb() {
		File file;
		if (isWindows()) {
		file = new File("c:");
		} else {
			file = new File("/");
		}
		double size = file.getTotalSpace() / (1024.0 * 1024 * 1024);
		return size;
	}

	public double getTotalDiskSpaceInMb() {
		File file;
		if (isWindows()) {
		file = new File("c:");
		} else {
			file = new File("/");
		}
		double size = file.getTotalSpace() / (1024.0 * 1024);  //.getUsableSpace()
		return size;
	}
	
	public double getUsableDiskSpaceInMb() {
		File file;
		if (isWindows()) {
		file = new File("c:");
		} else {
			file = new File("/");
		}
		double size = file.getUsableSpace() / (1024.0 * 1024);  //.getUsableSpace()
		return size;
	}
	
	public double getUsableDiskSpaceInGb() {
		File file;
		if (isWindows()) {
		file = new File("c:");
		} else {
			file = new File("/");
		}
		double size = file.getUsableSpace() / (1024.0 * 1024 * 1024);  //.getUsableSpace()
		return size;
	}

	public boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}

	public boolean isMac() {
		return (OS.indexOf("mac") >= 0);
	}

	public boolean isUnix() {
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);
	}

	public boolean isSolaris() {
		return (OS.indexOf("sunos") >= 0);
	}
}
