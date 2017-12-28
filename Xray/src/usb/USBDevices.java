package usb;

import controllers.XrayController;
import javafx.scene.control.ListView;

/**
 * The "USB Devices section" class of the app. Contains data such as usb
 * devices, their names, productID, serialNumber and more.
 * 
 * @author PilzHere
 *
 */
public class USBDevices {

	private XrayController controller;

	private boolean enabled;

	private String usbDevicesCount, usbDeviceName, usbDeviceVendor, usbDeviceVendorID, usbDeviceProductID,
			usbDeviceSerialNumber;

	public USBDevices(XrayController controller) {
		this.controller = controller;
	}

	/**
	 * Goes through the passed list. Counts the devices and sets their name to the
	 * list.
	 * 
	 * @param usbDevicesList
	 */
	public void setDevicesData(ListView<String> usbDevicesList) {
		setEnabled(controller.usbDevicesBox.isSelected());

		if (isEnabled()) {
			String usbDevicesCount = String.valueOf(XrayController.SYS_INF.getHardware().getUsbDevices(false).length);
			setUsbDevicesCount(usbDevicesCount);
			for (int i = 0; i < XrayController.SYS_INF.getHardware().getUsbDevices(false).length; i++) {
				usbDevicesList.getItems().add(XrayController.SYS_INF.getHardware().getUsbDevices(false)[i].getName());
			}
		} else {
			usbDevicesList.getItems().clear();
			String usbDevicesCount = "0";
			setUsbDevicesCount(usbDevicesCount);
			controller.clearUsbDevicesLabels();
		}
	}

	private int chosenUsbDevice = 0;
	private int lastChosenUsbDevice = -1;

	/**
	 * The event that starts if the user clicks in the USB Devices list.
	 * 
	 * @param usbDevicesList
	 */
	public void usbDevicesListClicked(ListView<String> usbDevicesList) {
		setEnabled(controller.usbDevicesBox.isSelected());

		if (isEnabled()) {
			chosenUsbDevice = usbDevicesList.getSelectionModel().getSelectedIndex();

			if (chosenUsbDevice != lastChosenUsbDevice) {
				if (chosenUsbDevice >= 0 && chosenUsbDevice < usbDevicesList.getItems().size()) {

					updateUsbDeviceData(chosenUsbDevice);

					String usbDevicesCount = String.valueOf(usbDevicesList.getItems().size());
					setUsbDevicesCount(usbDevicesCount);
				}
				lastChosenUsbDevice = chosenUsbDevice;

				controller.setUsbDevicesLabels();
			}
		}
	}

	/**
	 * Updates the chosen USB Device's data.
	 * 
	 * @param chosenUsbDevice
	 */
	private void updateUsbDeviceData(int chosenUsbDevice) {
		setUsbDeviceName(XrayController.SYS_INF.getHardware().getUsbDevices(false)[chosenUsbDevice].getName());
		setUsbDeviceName(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getUsbDeviceName()));

		setUsbDeviceVendor(XrayController.SYS_INF.getHardware().getUsbDevices(false)[chosenUsbDevice].getVendor());
		setUsbDeviceVendor(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getUsbDeviceVendor()));

		setUsbDeviceVendorID(XrayController.SYS_INF.getHardware().getUsbDevices(false)[chosenUsbDevice].getVendorId());
		setUsbDeviceVendorID(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getUsbDeviceVendorID()));

		setUsbDeviceProductID(
				XrayController.SYS_INF.getHardware().getUsbDevices(false)[chosenUsbDevice].getProductId());
		setUsbDeviceProductID(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getUsbDeviceProductID()));

		setUsbDeviceSerialNumber(
				XrayController.SYS_INF.getHardware().getUsbDevices(false)[chosenUsbDevice].getSerialNumber());
		setUsbDeviceSerialNumber(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getUsbDeviceSerialNumber()));
	}

	public boolean isEnabled() {
		return enabled;
	}

	private void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getUsbDevicesCount() {
		return usbDevicesCount;
	}

	private void setUsbDevicesCount(String usbDevicesCount) {
		this.usbDevicesCount = usbDevicesCount;
	}

	public String getUsbDeviceName() {
		return usbDeviceName;
	}

	private void setUsbDeviceName(String usbDeviceName) {
		this.usbDeviceName = usbDeviceName;
	}

	public String getUsbDeviceVendor() {
		return usbDeviceVendor;
	}

	private void setUsbDeviceVendor(String usbDeviceVendor) {
		this.usbDeviceVendor = usbDeviceVendor;
	}

	public String getUsbDeviceVendorID() {
		return usbDeviceVendorID;
	}

	private void setUsbDeviceVendorID(String usbDeviceVendorID) {
		this.usbDeviceVendorID = usbDeviceVendorID;
	}

	public String getUsbDeviceProductID() {
		return usbDeviceProductID;
	}

	private void setUsbDeviceProductID(String usbDeviceProductID) {
		this.usbDeviceProductID = usbDeviceProductID;
	}

	public String getUsbDeviceSerialNumber() {
		return usbDeviceSerialNumber;
	}

	private void setUsbDeviceSerialNumber(String usbDeviceSerialNumber) {
		this.usbDeviceSerialNumber = usbDeviceSerialNumber;
	}
}
