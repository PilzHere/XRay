package motherboard;

import controllers.XrayController;

/**
 * The "Motherboard section" class of the app.
 * Contains motherboard data.
 * @author PilzHere
 *
 */
public class Motherboard {

//	These strings are collected by XrayController.
	public String mbBrand;
	public String mbManufacturer;
	public String mbModel;
	public String mbSerialNumber;
	public String mbVersion;
	
	/**
	 * Sets all motherboard data.
	 */
	public void setMotherboardData() {
		mbBrand = XrayController.SysInf.getHardware().getComputerSystem().getManufacturer();
		mbBrand = XrayController.helper.stringIsEmptyOrUnknownOrNull(mbBrand);
		
		mbManufacturer = XrayController.SysInf.getHardware().getComputerSystem().getBaseboard().getManufacturer();
		mbManufacturer = XrayController.helper.stringIsEmptyOrUnknownOrNull(mbManufacturer);
		
		mbModel = XrayController.SysInf.getHardware().getComputerSystem().getBaseboard().getModel();
		mbModel = XrayController.helper.stringIsEmptyOrUnknownOrNull(mbModel);
		
		mbSerialNumber = XrayController.SysInf.getHardware().getComputerSystem().getBaseboard().getSerialNumber();
		mbSerialNumber = XrayController.helper.stringIsEmptyOrUnknownOrNull(mbSerialNumber);
		
		mbVersion = XrayController.SysInf.getHardware().getComputerSystem().getBaseboard().getVersion();
		mbVersion = XrayController.helper.stringIsEmptyOrUnknownOrNull(mbVersion);
		
//		Unused / not sure about these yet...
//		System.out.println(SysInf.getHardware().getComputerSystem().getModel());
//		System.out.println(SysInf.getHardware().getComputerSystem().getSerialNumber());
//		System.out.println(SysInf.getHardware().getComputerSystem().getFirmware().getDescription()); // Bios version.
	}
}
