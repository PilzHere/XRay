package motherboard.bios;

import controllers.XrayController;

/**
 * The "BIOS section (inside Motherboard section)" class of the app.
 * Contains BIOS data.
 * @author PilzHere
 *
 */
public class BIOS {

//	These strings are collected by XrayController.
	public String biosName;
	public String biosManufacturer;
	public String biosReleasedate;
	public String biosVersion;
	
	/**
	 * Sets all BIOS data.
	 */
	public void setBIOSData() {
		biosManufacturer = XrayController.SysInf.getHardware().getComputerSystem().getFirmware().getManufacturer();
		biosManufacturer = XrayController.helper.stringIsEmptyOrUnknownOrNull(biosManufacturer);
		
		biosName = XrayController.SysInf.getHardware().getComputerSystem().getFirmware().getVersion();
		biosName = XrayController.helper.stringIsEmptyOrUnknownOrNull(biosName);
		
		biosVersion = XrayController.SysInf.getHardware().getComputerSystem().getFirmware().getName();
		biosVersion = XrayController.helper.stringIsEmptyOrUnknownOrNull(biosVersion);
		
		biosReleasedate = XrayController.SysInf.getHardware().getComputerSystem().getFirmware().getReleaseDate().toString();
		biosReleasedate = XrayController.helper.stringIsEmptyOrUnknownOrNull(biosReleasedate);		
		
//		Unused / not sure about these yet...
//		System.out.println(SysInf.getHardware().getComputerSystem().getModel());
//		System.out.println(SysInf.getHardware().getComputerSystem().getSerialNumber());
//		System.out.println(SysInf.getHardware().getComputerSystem().getFirmware().getDescription()); // Bios version.
	}
}
