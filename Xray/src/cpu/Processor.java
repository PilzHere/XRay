package cpu;

import controllers.XrayController;

/**
 * The "Processor section" class of the app. Contains data about the CPU.
 * 
 * @author PilzHere
 *
 */
public class Processor {

	private String cpuBrand, cpuName, cpuFamily, cpuModel, cpuStepping, cpuFrequency, cpuArchitecture, cpuPhysicalCores,
			cpuLogicalCores, cpuID;

	/**
	 * Sets all CPU data. Brand, GHz, model, architecture and more.
	 */
	public void setProcessorData() {
		setCPUBrand();

		setCpuName(XrayController.SYS_INF.getHardware().getProcessor().getName());
		setCpuName(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getCpuName()));

		setCpuFamily(XrayController.SYS_INF.getHardware().getProcessor().getFamily());
		setCpuFamily(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getCpuFamily()));

		setCpuModel(XrayController.SYS_INF.getHardware().getProcessor().getModel());
		setCpuModel(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getCpuModel()));

		setCpuStepping(XrayController.SYS_INF.getHardware().getProcessor().getStepping());
		setCpuStepping(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getCpuStepping()));

		setCPUFreq();
		setCPUArch();

		String physCores = String
				.valueOf(XrayController.SYS_INF.getHardware().getProcessor().getPhysicalProcessorCount());
		setCpuPhysicalCores(physCores);
		setCpuPhysicalCores(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getCpuPhysicalCores()));

		String logCores = String
				.valueOf(XrayController.SYS_INF.getHardware().getProcessor().getLogicalProcessorCount());
		setCpuLogicalCores(logCores);
		setCpuLogicalCores(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getCpuLogicalCores()));

		setCpuID(XrayController.SYS_INF.getHardware().getProcessor().getProcessorID());
		setCpuID(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getCpuID()));

		// Unused / not sure about these yet...
		// System.out.println("CPU Load: " +
		// SysInf.getHardware().getProcessor().getSystemCpuLoad());
		// System.out.println("CPU Load between ticks: " +
		// SysInf.getHardware().getProcessor().getSystemCpuLoadBetweenTicks());
		// System.out.println("CPU Load average: " +
		// SysInf.getHardware().getProcessor().getSystemLoadAverage());
		// System.out.println("CPU Load average: " +
		// SysInf.getHardware().getProcessor().getSystemSerialNumber()); // Deprecated
		// System.out.println("CPU uptime: " +
		// SysInf.getHardware().getProcessor().getSystemUptime());
		// System.out.println("CPU load between ticks: " +
		// SysInf.getHardware().getProcessor().getProcessorCpuLoadBetweenTicks());
		// System.out.println("CPU loadticks: " +
		// SysInf.getHardware().getProcessor().getProcessorCpuLoadTicks());
		// System.out.println("System CPU loadticks: " +
		// SysInf.getHardware().getProcessor().getSystemCpuLoadTicks());
		// System.out.println("System load average: " +
		// SysInf.getHardware().getProcessor().getSystemLoadAverage());
	}

	/**
	 * Sets brand of CPU: Intel, AMD or "X".
	 */
	private void setCPUBrand() {
		String intelBrand = "Intel";
		String amdBrand = "AMD";
		if (XrayController.SYS_INF.getHardware().getProcessor().getVendor().contains(intelBrand)) {
			setCpuBrand(intelBrand);
		} else if (XrayController.SYS_INF.getHardware().getProcessor().getVendor().contains(amdBrand)) {
			setCpuBrand(amdBrand);
		} else {
			setCpuBrand(XrayController.SYS_INF.getHardware().getProcessor().getVendor());
			setCpuBrand(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getCpuBrand()));
		}
	}

	/**
	 * Calculates frequency and makes it look better.
	 */
	private void setCPUFreq() {
		long toGHz = XrayController.SYS_INF.getHardware().getProcessor().getVendorFreq() / 1000 / 1000;
		float toGHz2 = toGHz;
		String GHzString = String.valueOf(toGHz2);
		String GHzString2 = GHzString.substring(0, 4);
		String GHzString3 = GHzString2.substring(0, 1) + "." + GHzString2.substring(1, 4);
		setCpuFrequency(GHzString3 + " GHz");
		setCpuFrequency(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getCpuFrequency()));
	}

	/**
	 * Checks if OS arch is x86 or x64 (32bit or 64bit).
	 */
	private void setCPUArch() {
		String x86 = "32 bit";
		String x64 = "64 bit";
		if (XrayController.SYS_INF.getHardware().getProcessor().getIdentifier().contains("64")) {
			setCpuArchitecture(x64);
		} else if (XrayController.SYS_INF.getHardware().getProcessor().getIdentifier().contains("86")) {
			setCpuArchitecture(x86);
		} else {
			setCpuArchitecture(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getCpuArchitecture()));
		}
	}

	public String getCpuBrand() {
		return cpuBrand;
	}

	private void setCpuBrand(String cpuBrand) {
		this.cpuBrand = cpuBrand;
	}

	public String getCpuName() {
		return cpuName;
	}

	private void setCpuName(String cpuName) {
		this.cpuName = cpuName;
	}

	public String getCpuFamily() {
		return cpuFamily;
	}

	private void setCpuFamily(String cpuFamily) {
		this.cpuFamily = cpuFamily;
	}

	public String getCpuModel() {
		return cpuModel;
	}

	private void setCpuModel(String cpuModel) {
		this.cpuModel = cpuModel;
	}

	public String getCpuStepping() {
		return cpuStepping;
	}

	private void setCpuStepping(String cpuStepping) {
		this.cpuStepping = cpuStepping;
	}

	public String getCpuFrequency() {
		return cpuFrequency;
	}

	private void setCpuFrequency(String cpuFrequency) {
		this.cpuFrequency = cpuFrequency;
	}

	public String getCpuArchitecture() {
		return cpuArchitecture;
	}

	private void setCpuArchitecture(String cpuArchitecture) {
		this.cpuArchitecture = cpuArchitecture;
	}

	public String getCpuPhysicalCores() {
		return cpuPhysicalCores;
	}

	private void setCpuPhysicalCores(String cpuPhysicalCores) {
		this.cpuPhysicalCores = cpuPhysicalCores;
	}

	public String getCpuLogicalCores() {
		return cpuLogicalCores;
	}

	private void setCpuLogicalCores(String cpuLogicalCores) {
		this.cpuLogicalCores = cpuLogicalCores;
	}

	public String getCpuID() {
		return cpuID;
	}

	private void setCpuID(String cpuID) {
		this.cpuID = cpuID;
	}
}
