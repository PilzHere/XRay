package motherboard;

import controllers.XrayController;

/**
 * The "Motherboard section" class of the app. Contains motherboard data.
 * 
 * @author PilzHere
 *
 */
public class Motherboard {

	private String mbBrand, mbManufacturer, mbModel, mbSerialNumber, mbVersion;

	/**
	 * Sets all motherboard data.
	 */
	public void setMotherboardData() {
		setMbBrand(XrayController.SYS_INF.getHardware().getComputerSystem().getManufacturer());
		setMbBrand(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getMbBrand()));

		setMbManufacturer(XrayController.SYS_INF.getHardware().getComputerSystem().getBaseboard().getManufacturer());
		setMbManufacturer(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getMbManufacturer()));

		setMbModel(XrayController.SYS_INF.getHardware().getComputerSystem().getBaseboard().getModel());
		setMbModel(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getMbModel()));

		setMbSerialNumber(XrayController.SYS_INF.getHardware().getComputerSystem().getBaseboard().getSerialNumber());
		setMbSerialNumber(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getMbSerialNumber()));

		setMbVersion(XrayController.SYS_INF.getHardware().getComputerSystem().getBaseboard().getVersion());
		setMbVersion(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getMbVersion()));

		// Unused / not sure about these yet...
		// System.out.println(SysInf.getHardware().getComputerSystem().getModel());
		// System.out.println(SysInf.getHardware().getComputerSystem().getSerialNumber());
		// System.out.println(SysInf.getHardware().getComputerSystem().getFirmware().getDescription());
		// // Bios version.
	}

	public String getMbBrand() {
		return mbBrand;
	}

	private void setMbBrand(String mbBrand) {
		this.mbBrand = mbBrand;
	}

	public String getMbManufacturer() {
		return mbManufacturer;
	}

	private void setMbManufacturer(String mbManufacturer) {
		this.mbManufacturer = mbManufacturer;
	}

	public String getMbModel() {
		return mbModel;
	}

	private void setMbModel(String mbModel) {
		this.mbModel = mbModel;
	}

	public String getMbSerialNumber() {
		return mbSerialNumber;
	}

	private void setMbSerialNumber(String mbSerialNumber) {
		this.mbSerialNumber = mbSerialNumber;
	}

	public String getMbVersion() {
		return mbVersion;
	}

	private void setMbVersion(String mbVersion) {
		this.mbVersion = mbVersion;
	}
}
