package motherboard.bios;

import controllers.XrayController;

/**
 * The "BIOS section (inside Motherboard section)" class of the app. Contains
 * BIOS data.
 * 
 * @author PilzHere
 *
 */
public class BIOS {

	private String biosName, biosManufacturer, biosReleasedate, biosVersion;

	/**
	 * Sets all BIOS data.
	 */
	public void setBIOSData() {
		setBiosManufacturer(XrayController.SYS_INF.getHardware().getComputerSystem().getFirmware().getManufacturer());
		setBiosManufacturer(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getBiosManufacturer()));

		setBiosName(XrayController.SYS_INF.getHardware().getComputerSystem().getFirmware().getVersion());
		setBiosName(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getBiosName()));

		setBiosVersion(XrayController.SYS_INF.getHardware().getComputerSystem().getFirmware().getName());
		setBiosVersion(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getBiosVersion()));

		setBiosReleasedate(
				XrayController.SYS_INF.getHardware().getComputerSystem().getFirmware().getReleaseDate().toString());
		setBiosReleasedate(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getBiosReleasedate()));
	}

	public String getBiosName() {
		return biosName;
	}

	private void setBiosName(String biosName) {
		this.biosName = biosName;
	}

	public String getBiosManufacturer() {
		return biosManufacturer;
	}

	private void setBiosManufacturer(String biosManufacturer) {
		this.biosManufacturer = biosManufacturer;
	}

	public String getBiosReleasedate() {
		return biosReleasedate;
	}

	private void setBiosReleasedate(String biosReleasedate) {
		this.biosReleasedate = biosReleasedate;
	}

	public String getBiosVersion() {
		return biosVersion;
	}

	private void setBiosVersion(String biosVersion) {
		this.biosVersion = biosVersion;
	}
}
