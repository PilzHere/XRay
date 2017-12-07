package drives;

import controllers.XrayController;
import javafx.scene.control.ListView;

/**
 * The "Drives section" class of the app.
 * Contains data about drives and partitions.
 * @author PilzHere
 *
 */
public class Drives {
	
	private XrayController controller;
	
//	These Strings are collected by XrayController.
	public String drivesCount, partitionsCount;
	
	public String driveModel, driveSize, driveName, driveSerial;
	
	public String partitionID, partitionMountpoint, partitionSize, partitionUUID, partitionType, partitionMajor, partitionMinor;
	
	public Drives(XrayController controller) {
		this.controller = controller;
	}
	
	/**
	 * Sets every found drive from passed list and updates count.
	 * @param drivesList
	 */
	public void setDrivesData(ListView<String> drivesList) {
		String drivesCount = String.valueOf(XrayController.SysInf.getHardware().getDiskStores().length);
		this.drivesCount = drivesCount;
		
		for (int i = 0; i < XrayController.SysInf.getHardware().getDiskStores().length; i++) {
			drivesList.getItems().add(XrayController.SysInf.getHardware().getDiskStores()[i].getModel());
			
//			Unused / not sure about these yet...
//			System.out.println("Reads: " + SysInf.getHardware().getDiskStores()[i].getReads());
//			System.out.println("ReadBytes: " + SysInf.getHardware().getDiskStores()[i].getReadBytes());
//			System.out.println("Writes: " + SysInf.getHardware().getDiskStores()[i].getWrites());
//			System.out.println("WriteBytes: " + SysInf.getHardware().getDiskStores()[i].getWriteBytes());
//			System.out.println("Timestamp: " + SysInf.getHardware().getDiskStores()[i].getTimeStamp());
//			System.out.println("Transfer time: " + SysInf.getHardware().getDiskStores()[i].getTransferTime());
		}
	}
	
	int chosenDrive = 0;
	int lastChosenDrive = -1;
	
	/**
	 * The event that starts if clicking inside the DrivesList.
	 * @param drivesList
	 * @param partitionsList
	 */
	public void drivesListClicked(ListView<String> drivesList, ListView<String> partitionsList) {
		chosenDrive = drivesList.getSelectionModel().getSelectedIndex();
		
		if (chosenDrive != lastChosenDrive) {
			if (chosenDrive >= 0 && chosenDrive < drivesList.getItems().size()) {

				updateDriveData(chosenDrive);
				
				partitionsList.getItems().clear();
				for (int i = 0; i < XrayController.SysInf.getHardware().getDiskStores()[chosenDrive].getPartitions().length; i++) {
					partitionsList.getItems().add(XrayController.SysInf.getHardware().getDiskStores()[chosenDrive].getPartitions()[i].getIdentification());
				}
				
				String partitionsCount = String.valueOf(partitionsList.getItems().size());
				this.partitionsCount = partitionsCount;
			}
			lastChosenDrive = chosenDrive;
			lastChosenPartition = -1;
			
			controller.setDrivesLabels();
		}
	}
	
	/**
	 * Updates data from the chosen drive.
	 * @param chosenDrive
	 */
	private void updateDriveData(int chosenDrive) {
		driveModel = XrayController.SysInf.getHardware().getDiskStores()[chosenDrive].getModel();
		driveModel = XrayController.helper.stringIsEmptyOrUnknownOrNull(driveModel);
		
		String chosenDriveSize = String.valueOf(XrayController.SysInf.getHardware().getDiskStores()[chosenDrive].getSize());
		driveSize = chosenDriveSize + " bytes";
		driveSize = XrayController.helper.stringIsEmptyOrUnknownOrNull(driveSize);
		
		driveName = XrayController.SysInf.getHardware().getDiskStores()[chosenDrive].getName();
		driveName = XrayController.helper.stringIsEmptyOrUnknownOrNull(driveName);
		
		driveSerial = XrayController.SysInf.getHardware().getDiskStores()[chosenDrive].getSerial();
		driveSerial = XrayController.helper.stringIsEmptyOrUnknownOrNull(driveSerial);
	}
	
	int chosenPartition = 0;
	int lastChosenPartition = -1;
	
	/**
	 * The event that starts if clicking inside the PartitionsList.
	 * @param list
	 */
	public void partitionsListClicked(ListView<String> list) {
		chosenPartition = list.getSelectionModel().getSelectedIndex();

		if (chosenPartition != lastChosenPartition) {
			if (chosenPartition >= 0 && chosenPartition < list.getItems().size()) {
				updatePartitionData(chosenPartition);
			}
			lastChosenPartition = chosenPartition;
			
			controller.setPartitionsLabels();
		}
	}
	
	/**
	 * Updates data from the chosen partition.
	 * @param chosenPartition
	 */
	private void updatePartitionData(int chosenPartition) {
		partitionID = XrayController.SysInf.getHardware().getDiskStores()[chosenDrive].getPartitions()[chosenPartition].getIdentification();
		partitionID = XrayController.helper.stringIsEmptyOrUnknownOrNull(partitionID);
		
		partitionMountpoint = XrayController.SysInf.getHardware().getDiskStores()[chosenDrive].getPartitions()[chosenPartition].getMountPoint();
		partitionMountpoint = XrayController.helper.stringIsEmptyOrUnknownOrNull(partitionMountpoint);
		
		String chosenPartitionSize = String.valueOf(XrayController.SysInf.getHardware().getDiskStores()[chosenDrive].getPartitions()[chosenPartition].getSize());
		partitionSize = chosenPartitionSize;
		partitionSize = XrayController.helper.stringIsEmptyOrUnknownOrNull(partitionSize);
		
		partitionUUID = XrayController.SysInf.getHardware().getDiskStores()[chosenDrive].getPartitions()[chosenPartition].getUuid();
		partitionUUID = XrayController.helper.stringIsEmptyOrUnknownOrNull(partitionUUID);
		
		partitionType = XrayController.SysInf.getHardware().getDiskStores()[chosenDrive].getPartitions()[chosenPartition].getType();
		partitionType = XrayController.helper.stringIsEmptyOrUnknownOrNull(partitionType);
		
		String chosenPartitionMajor = String.valueOf(XrayController.SysInf.getHardware().getDiskStores()[chosenDrive].getPartitions()[chosenPartition].getMajor());
		partitionMajor = chosenPartitionMajor;
		partitionMajor = XrayController.helper.stringIsEmptyOrUnknownOrNull(partitionMajor);
		
		String chosenPartitionMinor = String.valueOf(XrayController.SysInf.getHardware().getDiskStores()[chosenDrive].getPartitions()[chosenPartition].getMinor());
		partitionMinor = chosenPartitionMinor;
		partitionMinor = XrayController.helper.stringIsEmptyOrUnknownOrNull(partitionMinor);
	}
}
