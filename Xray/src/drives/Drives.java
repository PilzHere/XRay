package drives;

import controllers.XrayController;
import javafx.scene.control.ListView;

/**
 * The "Drives section" class of the app. Contains data about drives and
 * partitions.
 * 
 * @author PilzHere
 *
 */
public class Drives {

	private XrayController controller;

	private String drivesCount, partitionsCount;
	private String driveModel, driveSize, driveName, driveSerial;
	private String partitionID, partitionMountpoint, partitionSize, partitionUUID, partitionType, partitionMajor,
			partitionMinor;

	public Drives(XrayController controller) {
		this.controller = controller;
	}

	/**
	 * Sets every found drive from passed list and updates count.
	 * 
	 * @param drivesList
	 */
	public void setDrivesData(ListView<String> drivesList) {
		String drivesCount = String.valueOf(XrayController.SYS_INF.getHardware().getDiskStores().length);
		setDrivesCount(drivesCount);

		for (int i = 0; i < XrayController.SYS_INF.getHardware().getDiskStores().length; i++) {
			drivesList.getItems().add(XrayController.SYS_INF.getHardware().getDiskStores()[i].getModel());

			// Unused / not sure about these yet...
			// System.out.println("Reads: " +
			// SysInf.getHardware().getDiskStores()[i].getReads());
			// System.out.println("ReadBytes: " +
			// SysInf.getHardware().getDiskStores()[i].getReadBytes());
			// System.out.println("Writes: " +
			// SysInf.getHardware().getDiskStores()[i].getWrites());
			// System.out.println("WriteBytes: " +
			// SysInf.getHardware().getDiskStores()[i].getWriteBytes());
			// System.out.println("Timestamp: " +
			// SysInf.getHardware().getDiskStores()[i].getTimeStamp());
			// System.out.println("Transfer time: " +
			// SysInf.getHardware().getDiskStores()[i].getTransferTime());
		}
	}

	private int chosenDrive = 0;
	private int lastChosenDrive = -1;

	/**
	 * The event that starts if clicking inside the DrivesList.
	 * 
	 * @param drivesList
	 * @param partitionsList
	 */
	public void drivesListClicked(ListView<String> drivesList, ListView<String> partitionsList) {
		chosenDrive = drivesList.getSelectionModel().getSelectedIndex();

		if (chosenDrive != lastChosenDrive) {
			if (chosenDrive >= 0 && chosenDrive < drivesList.getItems().size()) {

				updateDriveData(chosenDrive);

				partitionsList.getItems().clear();
				for (int i = 0; i < XrayController.SYS_INF.getHardware().getDiskStores()[chosenDrive]
						.getPartitions().length; i++) {
					partitionsList.getItems()
							.add(XrayController.SYS_INF.getHardware().getDiskStores()[chosenDrive].getPartitions()[i]
									.getIdentification());
				}

				String partitionsCount = String.valueOf(partitionsList.getItems().size());
				setPartitionsCount(partitionsCount);
			}
			lastChosenDrive = chosenDrive;
			lastChosenPartition = -1;

			controller.setDrivesLabels();
		}
	}

	/**
	 * Updates data from the chosen drive.
	 * 
	 * @param chosenDrive
	 */
	private void updateDriveData(int chosenDrive) {
		setDriveModel(XrayController.SYS_INF.getHardware().getDiskStores()[chosenDrive].getModel());
		setDriveModel(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getDriveModel()));

		String chosenDriveSize = String
				.valueOf(XrayController.SYS_INF.getHardware().getDiskStores()[chosenDrive].getSize());
		setDriveSize(chosenDriveSize + " bytes");
		setDriveSize(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getDriveSize()));

		setDriveName(XrayController.SYS_INF.getHardware().getDiskStores()[chosenDrive].getName());
		setDriveName(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getDriveName()));

		setDriveSerial(XrayController.SYS_INF.getHardware().getDiskStores()[chosenDrive].getSerial());
		setDriveSerial(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getDriveSerial()));
	}

	private int chosenPartition = 0;
	private int lastChosenPartition = -1;

	/**
	 * The event that starts if clicking inside the PartitionsList.
	 * 
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
	 * 
	 * @param chosenPartition
	 */
	private void updatePartitionData(int chosenPartition) {
		setPartitionID(
				XrayController.SYS_INF.getHardware().getDiskStores()[chosenDrive].getPartitions()[chosenPartition]
						.getIdentification());
		setPartitionID(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getPartitionID()));

		setPartitionMountpoint(
				XrayController.SYS_INF.getHardware().getDiskStores()[chosenDrive].getPartitions()[chosenPartition]
						.getMountPoint());
		setPartitionMountpoint(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getPartitionMountpoint()));

		String chosenPartitionSize = String.valueOf(
				XrayController.SYS_INF.getHardware().getDiskStores()[chosenDrive].getPartitions()[chosenPartition]
						.getSize());
		setPartitionSize(chosenPartitionSize);
		setPartitionSize(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getPartitionSize()));

		setPartitionUUID(
				XrayController.SYS_INF.getHardware().getDiskStores()[chosenDrive].getPartitions()[chosenPartition]
						.getUuid());
		setPartitionUUID(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getPartitionUUID()));

		setPartitionType(
				XrayController.SYS_INF.getHardware().getDiskStores()[chosenDrive].getPartitions()[chosenPartition]
						.getType());
		setPartitionType(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getPartitionType()));

		String chosenPartitionMajor = String.valueOf(
				XrayController.SYS_INF.getHardware().getDiskStores()[chosenDrive].getPartitions()[chosenPartition]
						.getMajor());
		setPartitionMajor(chosenPartitionMajor);
		setPartitionMajor(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getPartitionMajor()));

		String chosenPartitionMinor = String.valueOf(
				XrayController.SYS_INF.getHardware().getDiskStores()[chosenDrive].getPartitions()[chosenPartition]
						.getMinor());
		setPartitionMinor(chosenPartitionMinor);
		setPartitionMinor(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getPartitionMinor()));
	}

	public String getDrivesCount() {
		return drivesCount;
	}

	private void setDrivesCount(String drivesCount) {
		this.drivesCount = drivesCount;
	}

	public String getPartitionsCount() {
		return partitionsCount;
	}

	private void setPartitionsCount(String partitionsCount) {
		this.partitionsCount = partitionsCount;
	}

	public String getDriveModel() {
		return driveModel;
	}

	private void setDriveModel(String driveModel) {
		this.driveModel = driveModel;
	}

	public String getDriveSize() {
		return driveSize;
	}

	private void setDriveSize(String driveSize) {
		this.driveSize = driveSize;
	}

	public String getDriveName() {
		return driveName;
	}

	private void setDriveName(String driveName) {
		this.driveName = driveName;
	}

	public String getDriveSerial() {
		return driveSerial;
	}

	private void setDriveSerial(String driveSerial) {
		this.driveSerial = driveSerial;
	}

	public String getPartitionID() {
		return partitionID;
	}

	private void setPartitionID(String partitionID) {
		this.partitionID = partitionID;
	}

	public String getPartitionMountpoint() {
		return partitionMountpoint;
	}

	private void setPartitionMountpoint(String partitionMountpoint) {
		this.partitionMountpoint = partitionMountpoint;
	}

	public String getPartitionSize() {
		return partitionSize;
	}

	private void setPartitionSize(String partitionSize) {
		this.partitionSize = partitionSize;
	}

	public String getPartitionUUID() {
		return partitionUUID;
	}

	private void setPartitionUUID(String partitionUUID) {
		this.partitionUUID = partitionUUID;
	}

	public String getPartitionType() {
		return partitionType;
	}

	private void setPartitionType(String partitionType) {
		this.partitionType = partitionType;
	}

	public String getPartitionMajor() {
		return partitionMajor;
	}

	private void setPartitionMajor(String partitionMajor) {
		this.partitionMajor = partitionMajor;
	}

	public String getPartitionMinor() {
		return partitionMinor;
	}

	private void setPartitionMinor(String partitionMinor) {
		this.partitionMinor = partitionMinor;
	}
}
