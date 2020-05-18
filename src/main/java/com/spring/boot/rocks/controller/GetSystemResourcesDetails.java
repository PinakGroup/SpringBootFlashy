package com.spring.boot.rocks.controller;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;

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
		double size = file.getFreeSpace() / (1024.0 * 1024); // .getUsableSpace()
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
		double size = file.getTotalSpace() / (1024.0 * 1024); // .getUsableSpace()
		return size;
	}

	public double getUsableDiskSpaceInMb() {
		File file;
		if (isWindows()) {
			file = new File("c:");
		} else {
			file = new File("/");
		}
		double size = file.getUsableSpace() / (1024.0 * 1024); // .getUsableSpace()
		return size;
	}

	public double getUsableDiskSpaceInGb() {
		File file;
		if (isWindows()) {
			file = new File("c:");
		} else {
			file = new File("/");
		}
		double size = file.getUsableSpace() / (1024.0 * 1024 * 1024); // .getUsableSpace()
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

	public String getSystemStats() {
		OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
		MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
		long maxMemory = Runtime.getRuntime().maxMemory();
		File[] roots = File.listRoots();
		StringBuilder sb = new StringBuilder();
		sb.append("Current JVM Load:   " + osBean.getSystemLoadAverage());
		sb.append(String.format("<br/>Initial memory:  %.2f GB",
				(double) memoryMXBean.getHeapMemoryUsage().getInit() / 1073741824));
		sb.append(String.format("<br/>Used heap memory:  %.2f GB",
				(double) memoryMXBean.getHeapMemoryUsage().getUsed() / 1073741824));
		sb.append(String.format("<br/>Max heap memory:  %.2f GB",
				(double) memoryMXBean.getHeapMemoryUsage().getMax() / 1073741824));
		sb.append(String.format("<br/>Committed memory:  %.2f GB",
				(double) memoryMXBean.getHeapMemoryUsage().getCommitted() / 1073741824));

//ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
//for(Long threadID : threadMXBean.getAllThreadIds()) {
//    ThreadInfo info = threadMXBean.getThreadInfo(threadID);
//    System.out.println("Thread name: " + info.getThreadName());
//    System.out.println("Thread State: " + info.getThreadState());
//    System.out.println(String.format("CPU time: %s ns", 
//      threadMXBean.getThreadCpuTime(threadID)));
//  }

		sb.append("<br/>Available processors (cores):  " + Runtime.getRuntime().availableProcessors());
		sb.append("<br/>Free memory (bytes):  " + Runtime.getRuntime().freeMemory());
		sb.append("<br/>Maximum memory (bytes):  " + (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));
		sb.append("<br/>Total memory (bytes):  " + Runtime.getRuntime().totalMemory());
		for (File root : roots) {
			sb.append("<br/>File system root:  " + root.getAbsolutePath());
			sb.append("<br/>Total space (bytes):  " + root.getTotalSpace());
			sb.append("<br/>Free space (bytes):  " + root.getFreeSpace());
			sb.append("<br/>Usable space (bytes):  " + root.getUsableSpace());
		}
		
		return sb.toString();
	}
}
