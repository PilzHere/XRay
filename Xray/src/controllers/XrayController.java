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
 * 
 * @author PilzHere
 *
 */
public class XrayController implements Initializable {

	private static final OperatingSystem OS = new OperatingSystem();
	private static final Motherboard MB = new Motherboard();
	private static final BIOS BIOS = new BIOS();
	private static final Processor CPU = new Processor();
	private static final Memory RAM = new Memory();
	private final Drives drives = new Drives(this);
	private final Networks net = new Networks(this);
	private final USBDevices usb = new USBDevices(this);
	private final Graphics gpu = new Graphics();
	private final Monitors monitors = new Monitors(this);
	private static final About ABOUT = new About();

	// Helpers
	public static final Helper HELPER = new Helper();

	// Oshi
	public static final SystemInfo SYS_INF = new SystemInfo();

	// FXML components.
	// OS
	@FXML
	public Label osManufacturer, osName, osVersion, osArch, osBuild;

	// Motherboard
	@FXML
	private Label mbBrand, mbManufacturer, mbModel, mbSerialNumber, mbVersion;

	// BIOS
	@FXML
	private Label biosManufacturer, biosName, biosVersion, biosReleasedate;

	// CPU
	@FXML
	private Label cpuBrand, cpuName, cpuFamily, cpuModel, cpuStepping, cpuFrequency, cpuArchitecture, cpuPhysicalCores,
			cpuLogicalCores, cpuID;

	// Memory
	@FXML
	private Label memoryTotal, memoryAvailable, memorySwapTotal, memorySwapUsed;

	// Drives
	@FXML
	private ListView<String> drivesList, partitionsList;
	@FXML
	private Label drivesCount, partitionsCount, driveModel, driveSize, driveName, driveSerial, partitionID,
			partitionMountpoint, partitionSize, partitionUUID, partitionType, partitionMajor, partitionMinor;

	// Graphics
	@FXML
	private Label gpuModel, gpuVendor, gpuOpenGLVersion;

	// Monitors
	@FXML
	private ListView<String> monitorsList;
	@FXML
	private TextArea monitorTextArea;
	@FXML
	private Label totalMonitors;

	// Network
	@FXML
	private ListView<String> networkInterfacesList;
	@FXML
	private Label networkInterfacesCount, interfaceName, interfaceDisplayname, interfaceIPv4, interfaceIPv6,
			interfaceMAC, interfaceMTU;

	// USB Devices
	@FXML
	public CheckBox usbDevicesBox;
	@FXML
	private ListView<String> usbDevicesList;
	@FXML
	private Label usbDevicesCount, usbDeviceName, usbDeviceVendor, usbDeviceVendorID, usbDeviceProductID,
			usbDeviceSerialNumber;

	// About
	@FXML
	private Label aboutVersion, aboutDeveloper, aboutDate, aboutLicense, aboutMail;
	@FXML
	private Hyperlink aboutWebsite;

	// Empty string
	private static final String empty = "";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		clearAllLabels();

		OS.setOperatingSystemData();
		setOSLabels();

		MB.setMotherboardData();
		setMotherboardLabels();

		BIOS.setBIOSData();
		setBIOSLabels();

		CPU.setProcessorData();
		setProcessorLabels();

		RAM.setMemoryData();
		setMemoryLabels();

		drives.setDrivesData(drivesList);
		setDrivesLabels();
		setPartitionsLabels();

		// GPU class automatically fatches data.
		setGraphicsLabels();

		monitors.setMonitorsData(monitorsList);
		setMonitorsLabels();

		net.setNetworkInterfacesData(networkInterfacesList);
		setNetworkInterfacesLabels();

		ABOUT.setAboutData();
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

		/*
		 * Opening default web browser doesn't work on Ubuntu 17.10. Workaround: don't
		 * activate event if running Linux.
		 */
		if (checkIfOsIsLinux(System.getProperty("os.name"))) {
			aboutWebsite.setOnMouseClicked(e -> goToMyWebsite());
		}
	}

	/**
	 * Checks if the Operating System is Linux.
	 * 
	 * @param osName
	 * @return
	 */
	private boolean checkIfOsIsLinux(String osName) {
		String osLinux = "Linux";
		if (osName.equals(osLinux)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * If the desktop is supported: show my website in standard web browser.
	 */
	private void goToMyWebsite() {
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(new URI(Xray.getDeveloperWebsite()));
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
		osManufacturer.setText(empty);
		osName.setText(empty);
		osVersion.setText(empty);
		osArch.setText(empty);
		osBuild.setText(empty);
	}

	/**
	 * Clears motherboard labels.
	 */
	private void clearMotherboardLabels() {
		mbBrand.setText(empty);
		mbManufacturer.setText(empty);
		mbModel.setText(empty);
		mbSerialNumber.setText(empty);
		mbVersion.setText(empty);
	}

	/**
	 * Clears BIOS Labels.
	 */
	private void clearBIOSLabels() {
		biosManufacturer.setText(empty);
		biosName.setText(empty);
		biosVersion.setText(empty);
		biosReleasedate.setText(empty);
	}

	/**
	 * Clears CPU labels.
	 */
	private void clearCPULabels() {
		cpuBrand.setText(empty);
		cpuName.setText(empty);
		cpuFamily.setText(empty);
		cpuModel.setText(empty);
		cpuStepping.setText(empty);
		cpuFrequency.setText(empty);
		cpuArchitecture.setText(empty);
		cpuPhysicalCores.setText(empty);
		cpuLogicalCores.setText(empty);
		cpuID.setText(empty);
	}

	/**
	 * Clears RAM labels.
	 */
	private void clearMemoryLabels() {
		memoryTotal.setText(empty);
		memoryAvailable.setText(empty);
		memorySwapTotal.setText(empty);
		memorySwapUsed.setText(empty);
	}

	/**
	 * Clears drives labels.
	 */
	private void clearDrivesLabels() {
		drivesCount.setText(empty);
		partitionsCount.setText(empty);
		driveModel.setText(empty);
		driveSize.setText(empty);
		driveName.setText(empty);
		driveSerial.setText(empty);
	}

	/**
	 * Clears partitions labels.
	 */
	private void clearPartitionsLabels() {
		partitionID.setText(empty);
		partitionMountpoint.setText(empty);
		partitionSize.setText(empty);
		partitionUUID.setText(empty);
		partitionType.setText(empty);
		partitionMajor.setText(empty);
		partitionMinor.setText(empty);
	}

	/**
	 * Clears GPU labels.
	 */
	private void clearGraphicsLabels() {
		gpuModel.setText(empty);
		gpuVendor.setText(empty);
		gpuOpenGLVersion.setText(empty);
	}

	private void clearMonitorsLabels() {
		totalMonitors.setText(empty);
		monitorTextArea.setText(empty);
	}

	/**
	 * Clears networks labels.
	 */
	private void clearNetworkLabels() {
		networkInterfacesCount.setText(empty);
		interfaceName.setText(empty);
		interfaceDisplayname.setText(empty);
		interfaceIPv4.setText(empty);
		interfaceIPv6.setText(empty);
		interfaceMAC.setText(empty);
		interfaceMTU.setText(empty);
	}

	/**
	 * Clears USB devices labels.
	 */
	public void clearUsbDevicesLabels() {
		usbDevicesCount.setText(empty);
		usbDeviceName.setText(empty);
		usbDeviceVendor.setText(empty);
		usbDeviceVendorID.setText(empty);
		usbDeviceProductID.setText(empty);
		usbDeviceSerialNumber.setText(empty);
	}

	/**
	 * Clears about labels.
	 */
	private void clearAboutLabels() {
		aboutVersion.setText(empty);
		aboutDeveloper.setText(empty);
		aboutDate.setText(empty);
		aboutLicense.setText(empty);
		aboutMail.setText(empty);
	}

	/**
	 * Sets data into Operating System Labels.
	 */
	private void setOSLabels() {
		osManufacturer.setText(OS.getOsManufacturer());
		osName.setText(OS.getOsName());
		osVersion.setText(OS.getOsVersion());
		osArch.setText(OS.getOsArch());
		osBuild.setText(OS.getOsBuild());
	}

	/**
	 * Sets motherboard labels.
	 */
	private void setMotherboardLabels() {
		mbBrand.setText(MB.getMbBrand());
		mbManufacturer.setText(MB.getMbManufacturer());
		mbModel.setText(MB.getMbModel());
		mbSerialNumber.setText(MB.getMbSerialNumber());
		mbVersion.setText(MB.getMbVersion());
	}

	/**
	 * Sets BIOS labels.
	 */
	private void setBIOSLabels() {
		biosManufacturer.setText(BIOS.getBiosManufacturer());
		biosName.setText(BIOS.getBiosName());
		biosVersion.setText(BIOS.getBiosVersion());
		biosReleasedate.setText(BIOS.getBiosReleasedate());
	}

	/**
	 * Sets CPU labels.
	 */
	private void setProcessorLabels() {
		cpuBrand.setText(CPU.getCpuBrand());
		cpuName.setText(CPU.getCpuName());
		cpuFamily.setText(CPU.getCpuFamily());
		cpuModel.setText(CPU.getCpuModel());
		cpuStepping.setText(CPU.getCpuStepping());
		cpuFrequency.setText(CPU.getCpuFrequency());
		cpuArchitecture.setText(CPU.getCpuArchitecture());
		cpuPhysicalCores.setText(CPU.getCpuPhysicalCores());
		cpuLogicalCores.setText(CPU.getCpuLogicalCores());
		cpuID.setText(CPU.getCpuID());
	}

	/**
	 * Sets RAM labels.
	 */
	private void setMemoryLabels() {
		memoryTotal.setText(RAM.getMemoryTotal());
		memoryAvailable.setText(RAM.getMemoryAvailable());
		memorySwapTotal.setText(RAM.getMemorySwapTotal());
		memorySwapUsed.setText(RAM.getMemorySwapUsed());
	}

	/**
	 * Sets drives labels.
	 */
	public void setDrivesLabels() {
		drivesCount.setText(drives.getDrivesCount());
		partitionsCount.setText(drives.getPartitionsCount());
		driveModel.setText(drives.getDriveModel());
		driveSize.setText(drives.getDriveSize());
		driveName.setText(drives.getDriveName());
		driveSerial.setText(drives.getDriveSerial());
	}

	/**
	 * Sets partitions labels.
	 */
	public void setPartitionsLabels() {
		partitionID.setText(drives.getPartitionID());
		partitionMountpoint.setText(drives.getPartitionMountpoint());
		partitionSize.setText(drives.getPartitionSize());
		partitionUUID.setText(drives.getPartitionUUID());
		partitionType.setText(drives.getPartitionType());
		partitionMajor.setText(drives.getPartitionMajor());
		partitionMinor.setText(drives.getPartitionMinor());
	}

	/**
	 * Sets GPU labels.
	 */
	private void setGraphicsLabels() {
		gpuModel.setText(gpu.getGpuCard());
		gpuVendor.setText(gpu.getGpuVendor());
		gpuOpenGLVersion.setText(gpu.getGpuOpenGLVersion());
	}

	public void setMonitorsLabels() {
		totalMonitors.setText(monitors.getMonitorsCount());
		monitorTextArea.setText(monitors.getMonitorData());
	}

	/**
	 * Sets network interfaces labels.
	 */
	public void setNetworkInterfacesLabels() {
		networkInterfacesCount.setText(net.getNetworkInterfacesCount());
		interfaceName.setText(net.getInterfaceName());
		interfaceDisplayname.setText(net.getInterfaceDisplayname());
		interfaceIPv4.setText(net.getInterfaceIPv4());
		interfaceIPv6.setText(net.getInterfaceIPv6());
		interfaceMAC.setText(net.getInterfaceMAC());
		interfaceMTU.setText(net.getInterfaceMTU());
	}

	/**
	 * Sets USB devices labels.
	 */
	public void setUsbDevicesLabels() {
		usbDevicesCount.setText(usb.getUsbDevicesCount());
		usbDeviceName.setText(usb.getUsbDeviceName());
		usbDeviceVendor.setText(usb.getUsbDeviceVendor());
		usbDeviceVendorID.setText(usb.getUsbDeviceVendorID());
		usbDeviceProductID.setText(usb.getUsbDeviceProductID());
		usbDeviceSerialNumber.setText(usb.getUsbDeviceSerialNumber());
	}

	/**
	 * Sets about labels.
	 */
	public void setAboutLabels() {
		aboutVersion.setText(ABOUT.getAboutVersion());
		aboutDeveloper.setText(ABOUT.getAboutDeveloper());
		aboutDate.setText(ABOUT.getAboutDate());
		aboutLicense.setText(ABOUT.getAboutLicense());
		aboutMail.setText(Xray.getDeveloperMail());
	}
}
