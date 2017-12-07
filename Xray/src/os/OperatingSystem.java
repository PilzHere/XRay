package os;

import controllers.XrayController;

/**
 * The "Operating System section" class of the app.
 * Contains data about OS.
 * @author PilzHere
 *
 */
public class OperatingSystem {

//	These strings are collected by XrayController.
	public String osManufacturer, osName, osVersion, osArch, osBuild;
	
	/**
	 * Sets all Operating System data.
	 */
	public void setOperatingSystemData() {
		osManufacturer = (XrayController.SysInf.getOperatingSystem().getManufacturer());
		osManufacturer = XrayController.helper.stringIsEmptyOrUnknownOrNull(osManufacturer);
		
		osName = XrayController.SysInf.getOperatingSystem().getFamily();
		osName = XrayController.helper.stringIsEmptyOrUnknownOrNull(osName);
		
		osVersion = XrayController.SysInf.getOperatingSystem().getVersion().getVersion();
		if (System.getProperty("os.arch").endsWith("64")) {
			osArch = "64 bit";
		} else {
			osArch = "32 bit";
		}
		osBuild = XrayController.SysInf.getOperatingSystem().getVersion().getBuildNumber();
		osBuild = XrayController.helper.stringIsEmptyOrUnknownOrNull(osBuild);
	}
}
