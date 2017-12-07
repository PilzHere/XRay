package controllers;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import about.About;
import cpu.Processor;
import drives.Drives;
import gpu.Graphics;
import helpers.Helper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import memory.Memory;
import monitors.Monitors;
import motherboard.Motherboard;
import motherboard.bios.BIOS;
import networks.Networks;
import os.OperatingSystem;
import oshi.SystemInfo;
import usb.USBDevices;
import xray.Xray;

/**
 * Controller for XRay.
 * @author PilzHere
 *
 */
public class XrayController implements Initializable{
	
	private static OperatingSystem os = new OperatingSystem();
	private static Motherboard mb = new Motherboard();
	private static BIOS bios = new BIOS();
	private static Processor cpu = new Processor();
	private static Memory ram = new Memory();
	private Drives drives = new Drives(this);
	private Networks net = new Networks(this);
	private USBDevices usb = new USBDevices(this);
	private Graphics gpu = new Graphics();
	private Monitors monitors = new Monitors(this);
	private static About about = new About();
	
//	Helpers
	public static Helper helper = new Helper();
	
//	Oshi
	public static SystemInfo SysInf;
	
//	FXML components.
//	OS
	@FXML
	public Label osManufacturer, osName, osVersion, osArch, osBuild;

//	Motherboard
	@FXML
	private Label mbBrand, mbManufacturer, mbModel, mbSerialNumber, mbVersion;
	
//	BIOS
	@FXML
	private Label biosManufacturer, biosName, biosVersion, biosReleasedate;
	
//	CPU
	@FXML
	private Label cpuBrand, cpuName, cpuFamily, cpuModel, cpuStepping, cpuFrequency, cpuArchitecture, cpuPhysicalCores, cpuLogicalCores, cpuID;
	
//	Memory
	@FXML
	private Label memoryTotal, memoryAvailable, memorySwapTotal, memorySwapUsed;
	
//	Drives
	@FXML
	private ListView<String> drivesList, partitionsList;
	@FXML
	private Label drivesCount, partitionsCount, driveModel, driveSize, driveName, driveSerial, partitionID, partitionMountpoint, partitionSize,
				  partitionUUID, partitionType, partitionMajor, partitionMinor;
	
//	Graphics
	@FXML
	private Label gpuModel, gpuVendor, gpuOpenGLVersion;

//	Monitors
	@FXML
	private ListView<String> monitorsList;
	@FXML
	private TextArea monitorTextArea;
	@FXML
	private Label totalMonitors;
	
//	Network
	@FXML
	private ListView<String> networkInterfacesList;
	@FXML
	private Label networkInterfacesCount, interfaceName, interfaceDisplayname, interfaceIPv4, interfaceIPv6, interfaceMAC, interfaceMTU;
	
//	USB Devices
	@FXML
	public CheckBox usbDevicesBox;
	@FXML
	private ListView<String> usbDevicesList;
	@FXML
	private Label usbDevicesCount, usbDeviceName, usbDeviceVendor, usbDeviceVendorID, usbDeviceProductID, usbDeviceSerialNumber;
	
//	About
	@FXML
	private Label aboutVersion, aboutDeveloper, aboutDate, aboutLicense, aboutMail;
	@FXML
	private Hyperlink aboutWebsite;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		SysInf = new SystemInfo();
		
		clearAllLabels();
		
		os.setOperatingSystemData();
		setOSLabels();
		
		mb.setMotherboardData();
		setMotherboardLabels();
		
		bios.setBIOSData();
		setBIOSLabels();
		
		cpu.setProcessorData();
		setProcessorLabels();
		
		ram.setMemoryData();
		setMemoryLabels();
		
		drives.setDrivesData(drivesList);
		setDrivesLabels();
		setPartitionsLabels();
		
//		GPU class automatically fatches data.
		setGraphicsLabels();
		
		monitors.setMonitorsData(monitorsList);
		setMonitorsLabels();
		
		net.setNetworkInterfacesData(networkInterfacesList);
		setNetworkInterfacesLabels();
		
		about.setAboutData();
		setAboutLabels();
		
		setFXMLEvents();
		
	}
	
	/**
	 * Sets FXML Events.
	 */
	private void setFXMLEvents() {
		drivesList.setOnMouseClicked(e -> drives.drivesListClicked(drivesList, partitionsList));
		partitionsList.setOnMouseClicked(e -> drives.partitionsListClicked(partitionsList));
		
		networkInterfacesList.setOnMouseClicked(e -> net.networksInterfacesListClicked(networkInterfacesList));
		
		usbDevicesBox.setOnMouseClicked(e -> usb.setDevicesData(usbDevicesList));
		usbDevicesList.setOnMouseClicked(e -> usb.usbDevicesListClicked(usbDevicesList));
		
		monitorsList.setOnMouseClicked(e -> monitors.monitorsListClicked(monitorsList));
		
		/* Opening default web browser doesn't work on Ubuntu 17.10.
		 * Workaround: don't activate event if running Linux.
		 */
		 if (checkIfOsIsLinux(System.getProperty("os.name"))) {
			 aboutWebsite.setOnMouseClicked(e -> goToMyWebsite()); 
		 }
	}
	
	/**
	 * Checks if the Operating System is Linux.
	 * @param osName
	 * @return
	 */
	private boolean checkIfOsIsLinux(String osName) {
		String osLinux = "Linux";
		if (osName.equals(osLinux)) {
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * If the desktop is supported: show my website in standard web browser.
	 */
	private void goToMyWebsite() {
		if(Desktop.isDesktopSupported())
	    {
	        try {
	            Desktop.getDesktop().browse(new URI(Xray.DEVELOPER_WEBSITE));
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        } catch (URISyntaxException e1) {
	            e1.printStackTrace();
	        }
	    }
	}
	
	/**
	 * Clears ALL labels.
	 */
	private void clearAllLabels() {
		clearOSLabels();
		clearMotherboardLabels();
		clearBIOSLabels();
		clearCPULabels();
		clearMemoryLabels();
		clearDrivesLabels();
		clearPartitionsLabels();
		clearGraphicsLabels();
		clearMonitorsLabels();
		clearNetworkLabels();
		clearUsbDevicesLabels();
		clearAboutLabels();
	}
	
	/**
	 * Clears Opearting System labels.
	 */
	private void clearOSLabels() {
		osManufacturer.setText("");
		osName.setText("");
		osVersion.setText("");
		osArch.setText("");
		osBuild.setText("");
	}
	
	/**
	 * Clears motherboard labels.
	 */
	private void clearMotherboardLabels() {
		mbBrand.setText("");
		mbManufacturer.setText("");
		mbModel.setText("");
		mbSerialNumber.setText("");
		mbVersion.setText("");
	}
	
	/**
	 * Clears BIOS Labels.
	 */
	private void clearBIOSLabels() {
		biosManufacturer.setText("");
		biosName.setText("");
		biosVersion.setText("");
		biosReleasedate.setText("");
	}
	
	/**
	 * Clears CPU labels.
	 */
	private void clearCPULabels() {
		cpuBrand.setText("");
		cpuName.setText("");
		cpuFamily.setText("");
		cpuModel.setText("");
		cpuStepping.setText("");
		cpuFrequency.setText("");
		cpuArchitecture.setText("");
		cpuPhysicalCores.setText("");
		cpuLogicalCores.setText("");
		cpuID.setText("");
	}
	
	/**
	 * Clears RAM labels.
	 */
	private void clearMemoryLabels() {
		memoryTotal.setText("");
		memoryAvailable.setText("");
		memorySwapTotal.setText("");
		memorySwapUsed.setText("");
	}
	
	/**
	 * Clears drives labels.
	 */
	private void clearDrivesLabels() {
		drivesCount.setText("");
		partitionsCount.setText("");
		driveModel.setText("");
		driveSize.setText("");
		driveName.setText("");
		driveSerial.setText("");
	}
	
	/**
	 * Clears partitions labels.
	 */
	private void clearPartitionsLabels() {
		partitionID.setText("");
		partitionMountpoint.setText("");
		partitionSize.setText("");
		partitionUUID.setText("");
		partitionType.setText("");
		partitionMajor.setText("");
		partitionMinor.setText("");
	}
	
	/**
	 * Clears GPU labels.
	 */
	private void clearGraphicsLabels() {
		gpuModel.setText("");
		gpuVendor.setText("");
		gpuOpenGLVersion.setText("");
	}
	
	private void clearMonitorsLabels() {
		totalMonitors.setText("");
		monitorTextArea.setText("");
	}
	
	/**
	 * Clears networks labels.
	 */
	private void clearNetworkLabels() {
		networkInterfacesCount.setText("");
		interfaceName.setText("");
		interfaceDisplayname.setText("");
		interfaceIPv4.setText("");
		interfaceIPv6.setText("");
		interfaceMAC.setText("");
		interfaceMTU.setText("");
	}
	
	/**
	 * Clears USB devices labels.
	 */
	public void clearUsbDevicesLabels() {
		usbDevicesCount.setText("");
		usbDeviceName.setText("");
		usbDeviceVendor.setText("");
		usbDeviceVendorID.setText("");
		usbDeviceProductID.setText("");
		usbDeviceSerialNumber.setText("");
	}
	
	/**
	 * Clears about labels.
	 */
	private void clearAboutLabels() {
		aboutVersion.setText("");
		aboutDeveloper.setText("");
		aboutDate.setText("");
		aboutLicense.setText("");
		aboutMail.setText("");
	}
	
	/**
	 * Sets data into Operating System Labels.
	 */
	private void setOSLabels() {
		osManufacturer.setText(os.osManufacturer);
		osName.setText(os.osName);
		osVersion.setText(os.osVersion);
		osArch.setText(os.osArch);
		osBuild.setText(os.osBuild);
	}
	
	/**
	 * Sets motherboard labels.
	 */
	private void setMotherboardLabels() {
		mbBrand.setText(mb.mbBrand);
		mbManufacturer.setText(mb.mbManufacturer);
		mbModel.setText(mb.mbModel);
		mbSerialNumber.setText(mb.mbSerialNumber);
		mbVersion.setText(mb.mbVersion);
	}
	
	/**
	 * Sets BIOS labels.
	 */
	private void setBIOSLabels() {
		biosManufacturer.setText(bios.biosManufacturer);
		biosName.setText(bios.biosName);
		biosVersion.setText(bios.biosVersion);
		biosReleasedate.setText(bios.biosReleasedate);
	}
	
	/**
	 * Sets CPU labels.
	 */
	private void setProcessorLabels() {
		cpuBrand.setText(cpu.cpuBrand);
		cpuName.setText(cpu.cpuName);
		cpuFamily.setText(cpu.cpuFamily);
		cpuModel.setText(cpu.cpuModel);
		cpuStepping.setText(cpu.cpuStepping);
		cpuFrequency.setText(cpu.cpuFrequency);
		cpuArchitecture.setText(cpu.cpuArchitecture);
		cpuPhysicalCores.setText(cpu.cpuPhysicalCores);
		cpuLogicalCores.setText(cpu.cpuLogicalCores);
		cpuID.setText(cpu.cpuID);
	}
	
	/**
	 * Sets RAM labels.
	 */
	private void setMemoryLabels() {	
		memoryTotal.setText(ram.memoryTotal);
		memoryAvailable.setText(ram.memoryAvailable);
		memorySwapTotal.setText(ram.memorySwapTotal);
		memorySwapUsed.setText(ram.memorySwapUsed);
	}
	
	/**
	 * Sets drives labels.
	 */
	public void setDrivesLabels() {
		drivesCount.setText(drives.drivesCount);
		partitionsCount.setText(drives.partitionsCount);
		driveModel.setText(drives.driveModel);
		driveSize.setText(drives.driveSize);
		driveName.setText(drives.driveName);
		driveSerial.setText(drives.driveSerial);
	}
	
	/**
	 * Sets partitions labels.
	 */
	public void setPartitionsLabels() {
		partitionID.setText(drives.partitionID);
		partitionMountpoint.setText(drives.partitionMountpoint);
		partitionSize.setText(drives.partitionSize);
		partitionUUID.setText(drives.partitionUUID);
		partitionType.setText(drives.partitionType);
		partitionMajor.setText(drives.partitionMajor);
		partitionMinor.setText(drives.partitionMinor);
	}
	
	/**
	 * Sets GPU labels.
	 */
	private void setGraphicsLabels() {
		gpuModel.setText(gpu.gpuCard);
		gpuVendor.setText(gpu.gpuVendor);
		gpuOpenGLVersion.setText(gpu.gpuOpenGLVersion);
	}
	
	public void setMonitorsLabels() {
		totalMonitors.setText(monitors.monitorsCount);
		monitorTextArea.setText(monitors.monitorData);
	}
	
	/**
	 * Sets network interfaces labels.
	 */
	public void setNetworkInterfacesLabels() {
		networkInterfacesCount.setText(net.networkInterfacesCount);
		interfaceName.setText(net.interfaceName);
		interfaceDisplayname.setText(net.interfaceDisplayname);
		interfaceIPv4.setText(net.interfaceIPv4);
		interfaceIPv6.setText(net.interfaceIPv6);
		interfaceMAC.setText(net.interfaceMAC);
		interfaceMTU.setText(net.interfaceMTU);
	}
	
	/**
	 * Sets USB devices labels.
	 */
	public void setUsbDevicesLabels() {
		usbDevicesCount.setText(usb.usbDevicesCount);
		usbDeviceName.setText(usb.usbDeviceName);
		usbDeviceVendor.setText(usb.usbDeviceVendor);
		usbDeviceVendorID.setText(usb.usbDeviceVendorID);
		usbDeviceProductID.setText(usb.usbDeviceProductID);
		usbDeviceSerialNumber.setText(usb.usbDeviceSerialNumber);
	}
	
	/**
	 * Sets about labels.
	 */
	public void setAboutLabels() {
		aboutVersion.setText(about.aboutVersion);
		aboutDeveloper.setText(about.aboutDeveloper);
		aboutDate.setText(about.aboutDate);
		aboutLicense.setText(about.aboutLicense);
		aboutMail.setText(Xray.DEVELOPER_MAIL);
	}
}
