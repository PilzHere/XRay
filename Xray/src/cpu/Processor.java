package cpu;

import controllers.XrayController;

/**
 * The "Processor section" class of the app.
 * Contains data about the CPU.
 * @author PilzHere
 *
 */
public class Processor {

//	These strings are collected by XrayController.
	public String cpuBrand;
	public String cpuName;
	public String cpuFamily;
	public String cpuModel;
	public String cpuStepping;
	public String cpuFrequency;
	public String cpuArchitecture;
	public String cpuPhysicalCores;
	public String cpuLogicalCores;
	public String cpuID;
	
	/**
	 * Sets all CPU data.
	 * Brand, GHz, model, architecture and more.
	 */
	public void setProcessorData() {
		setCPUBrand();
		
		cpuName = XrayController.SysInf.getHardware().getProcessor().getName();
		cpuName = XrayController.helper.stringIsEmptyOrUnknownOrNull(cpuName);
		
		cpuFamily = XrayController.SysInf.getHardware().getProcessor().getFamily();
		cpuFamily = XrayController.helper.stringIsEmptyOrUnknownOrNull(cpuFamily);
		
		cpuModel = XrayController.SysInf.getHardware().getProcessor().getModel();
		cpuModel = XrayController.helper.stringIsEmptyOrUnknownOrNull(cpuModel);
		
		cpuStepping = XrayController.SysInf.getHardware().getProcessor().getStepping();
		cpuStepping = XrayController.helper.stringIsEmptyOrUnknownOrNull(cpuStepping);
		
		setCPUFreq();
		setCPUArch();
		
		String physCores = String.valueOf(XrayController.SysInf.getHardware().getProcessor().getPhysicalProcessorCount());
		cpuPhysicalCores = physCores;
		cpuPhysicalCores = XrayController.helper.stringIsEmptyOrUnknownOrNull(cpuPhysicalCores);
		
		String logCores = String.valueOf(XrayController.SysInf.getHardware().getProcessor().getLogicalProcessorCount());
		cpuLogicalCores = logCores;
		cpuLogicalCores = XrayController.helper.stringIsEmptyOrUnknownOrNull(cpuLogicalCores);
		
		cpuID = XrayController.SysInf.getHardware().getProcessor().getProcessorID();
		cpuID = XrayController.helper.stringIsEmptyOrUnknownOrNull(cpuID);
		
//		Unused / not sure about these yet...
//		System.out.println("CPU Load: " + SysInf.getHardware().getProcessor().getSystemCpuLoad());
//		System.out.println("CPU Load between ticks: " + SysInf.getHardware().getProcessor().getSystemCpuLoadBetweenTicks());
//		System.out.println("CPU Load average: " + SysInf.getHardware().getProcessor().getSystemLoadAverage());
//		System.out.println("CPU Load average: " + SysInf.getHardware().getProcessor().getSystemSerialNumber()); // Deprecated
//		System.out.println("CPU uptime: " + SysInf.getHardware().getProcessor().getSystemUptime());
//		System.out.println("CPU load between ticks: " + SysInf.getHardware().getProcessor().getProcessorCpuLoadBetweenTicks());
//		System.out.println("CPU loadticks: " + SysInf.getHardware().getProcessor().getProcessorCpuLoadTicks());
//		System.out.println("System CPU loadticks: " + SysInf.getHardware().getProcessor().getSystemCpuLoadTicks());
//		System.out.println("System load average: " + SysInf.getHardware().getProcessor().getSystemLoadAverage());
	}
	
	/**
	 * Sets brand of CPU: Intel, AMD or "X".
	 */
	private void setCPUBrand() {
		String intelBrand = "Intel";
		String amdBrand = "AMD";
		if (XrayController.SysInf.getHardware().getProcessor().getVendor().contains(intelBrand)) {
			cpuBrand = intelBrand;
		} else if (XrayController.SysInf.getHardware().getProcessor().getVendor().contains(amdBrand)) {
			cpuBrand = amdBrand;
		} else {
			cpuBrand = XrayController.SysInf.getHardware().getProcessor().getVendor();
			cpuBrand = XrayController.helper.stringIsEmptyOrUnknownOrNull(cpuBrand);
		}
	}
	
	/**
	 * Calculates frequency and makes it look better.
	 */
	private void setCPUFreq() {
		long toGHz = XrayController.SysInf.getHardware().getProcessor().getVendorFreq() / 1000 / 1000;
		float toGHz2 = toGHz;
		String GHzString = String.valueOf(toGHz2);
		String GHzString2 = GHzString.substring(0, 4);
		String GHzString3 = GHzString2.substring(0, 1) + "." + GHzString2.substring(1, 4);
		cpuFrequency = GHzString3 + " GHz";
		cpuFrequency = XrayController.helper.stringIsEmptyOrUnknownOrNull(cpuFrequency);
	}
	
	/**
	 * Checks if OS arch is x86 or x64 (32bit or 64bit).
	 */
	private void setCPUArch() {
		String x86 = "32 bit";
		String x64 = "64 bit";
		if (XrayController.SysInf.getHardware().getProcessor().getIdentifier().contains("64")) {
			cpuArchitecture = x64;
		} else if (XrayController.SysInf.getHardware().getProcessor().getIdentifier().contains("86")) {
			cpuArchitecture = x86;
		} else {
			cpuArchitecture = XrayController.helper.stringIsEmptyOrUnknownOrNull(cpuArchitecture);
		}
	}
}
