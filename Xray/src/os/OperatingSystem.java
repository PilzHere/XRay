package os;

import controllers.XrayController;

/**
 * The "Operating System section" class of the app. Contains data about OS.
 * 
 * @author PilzHere
 *
 */
public class OperatingSystem {

	private String osManufacturer, osName, osVersion, osArch, osBuild;

	/**
	 * Sets all Operating System data.
	 */
	public void setOperatingSystemData() {
		setOsManufacturer(XrayController.SYS_INF.getOperatingSystem().getManufacturer());
		setOsManufacturer(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getOsManufacturer()));

		setOsName(XrayController.SYS_INF.getOperatingSystem().getFamily());
		setOsName(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getOsName()));

		setOsVersion(XrayController.SYS_INF.getOperatingSystem().getVersion().getVersion());
		if (System.getProperty("os.arch").endsWith("64")) {
			setOsArch("64 bit");
		} else {
			setOsArch("32 bit");
		}
		setOsBuild(XrayController.SYS_INF.getOperatingSystem().getVersion().getBuildNumber());
		setOsBuild(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getOsBuild()));
	}

	public String getOsManufacturer() {
		return osManufacturer;
	}

	private void setOsManufacturer(String osManufacturer) {
		this.osManufacturer = osManufacturer;
	}

	public String getOsName() {
		return osName;
	}

	private void setOsName(String osName) {
		this.osName = osName;
	}

	public String getOsVersion() {
		return osVersion;
	}

	private void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getOsArch() {
		return osArch;
	}

	private void setOsArch(String osArch) {
		this.osArch = osArch;
	}

	public String getOsBuild() {
		return osBuild;
	}

	private void setOsBuild(String osBuild) {
		this.osBuild = osBuild;
	}
}
