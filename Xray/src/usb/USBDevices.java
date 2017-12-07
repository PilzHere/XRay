package usb;

import controllers.XrayController;
import javafx.scene.control.ListView;

/**
 * The "USB Devices section" class of the app.
 * Contains data such as usb devices, their names, productID, serialNumber and more.
 * @author PilzHere
 *
 */
public class USBDevices {

	private XrayController controller;
	
	private boolean enabled;
	
//	These strings are collected by XrayController.
	public String usbDevicesCount, usbDeviceName, usbDeviceVendor, usbDeviceVendorID, usbDeviceProductID, usbDeviceSerialNumber;
	
	public USBDevices (XrayController controller) {
		this.controller = controller;
	}
	
	/**
	 * Goes through the passed list.
	 * Counts the devices and sets their name to the list.
	 * @param usbDevicesList
	 */
	public void setDevicesData(ListView<String> usbDevicesList) {
		enabled = controller.usbDevicesBox.isSelected();
		
		if (enabled) {
			String usbDevicesCount = String.valueOf(XrayController.SysInf.getHardware().getUsbDevices(false).length);
			this.usbDevicesCount = usbDevicesCount;
			for (int i = 0; i < XrayController.SysInf.getHardware().getUsbDevices(false).length; i++) {
				usbDevicesList.getItems().add(XrayController.SysInf.getHardware().getUsbDevices(false)[i].getName());
			}
		} else {
			usbDevicesList.getItems().clear();
			String usbDevicesCount = "0";
			this.usbDevicesCount = usbDevicesCount;
			controller.clearUsbDevicesLabels();
		}
	}
	
	int chosenUsbDevice = 0;
	int lastChosenUsbDevice = -1;
	
	/**
	 * The event that starts if the user clicks in the USB Devices list.
	 * @param usbDevicesList
	 */
	public void usbDevicesListClicked(ListView<String> usbDevicesList) {
		enabled = controller.usbDevicesBox.isSelected();
		
		if (enabled) {
			chosenUsbDevice = usbDevicesList.getSelectionModel().getSelectedIndex();
			
			if (chosenUsbDevice != lastChosenUsbDevice) {
				if (chosenUsbDevice >= 0 && chosenUsbDevice < usbDevicesList.getItems().size()) {

					updateUsbDeviceData(chosenUsbDevice);
					
					String usbDevicesCount = String.valueOf(usbDevicesList.getItems().size());
					this.usbDevicesCount = usbDevicesCount;
				}
				lastChosenUsbDevice = chosenUsbDevice;
				
				controller.setUsbDevicesLabels();
			}
		}
	}
	
	/**
	 * Updates the chosen USB Device's data.
	 * @param chosenUsbDevice
	 */
	private void updateUsbDeviceData(int chosenUsbDevice) {
		usbDeviceName = XrayController.SysInf.getHardware().getUsbDevices(false)[chosenUsbDevice].getName();
		usbDeviceName = XrayController.helper.stringIsEmptyOrUnknownOrNull(usbDeviceName);
		
		usbDeviceVendor = XrayController.SysInf.getHardware().getUsbDevices(false)[chosenUsbDevice].getVendor();
		usbDeviceVendor = XrayController.helper.stringIsEmptyOrUnknownOrNull(usbDeviceVendor);
		
		usbDeviceVendorID = XrayController.SysInf.getHardware().getUsbDevices(false)[chosenUsbDevice].getVendorId();
		usbDeviceVendorID = XrayController.helper.stringIsEmptyOrUnknownOrNull(usbDeviceVendorID);
		
		usbDeviceProductID = XrayController.SysInf.getHardware().getUsbDevices(false)[chosenUsbDevice].getProductId();
		usbDeviceProductID = XrayController.helper.stringIsEmptyOrUnknownOrNull(usbDeviceProductID);
		
		usbDeviceSerialNumber = XrayController.SysInf.getHardware().getUsbDevices(false)[chosenUsbDevice].getSerialNumber();
		usbDeviceSerialNumber = XrayController.helper.stringIsEmptyOrUnknownOrNull(usbDeviceSerialNumber);
	}
}
