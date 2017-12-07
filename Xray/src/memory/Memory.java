package memory;

import controllers.XrayController;

/**
 * The "Memory section" class of the app.
 * Contains data about system memory.
 * @author PilzHere
 *
 */
public class Memory {

//	These strings are collected by XrayController.
	public String memoryTotal;
	public String memoryAvailable;
	public String memorySwapTotal;
	public String memorySwapUsed;
	
	final long toKiBLimit = 1022976L;
	final long toMiBLimit = 1047527424L;
	final long toGiBLimit = 1072668082176L;
	final long toTiBLimit = 1098412116148220L;
	final long toPiBLimit = 1124774006935781376L;
	
	public final String bytes = " B";
	public final String kibibytes = " KiB";
	public final String mebibytes = " MiB";
	public final String gibibytes = " GiB";
	public final String tebibytes = " TiB";
	public final String pebibytes = " PiB";
	
	/**
	 * Sets all memorydata from system.
	 */
	public void setMemoryData() {
		setTotalMemory();
		setAvailableMemory();
		setSwapTotalMemory();
		setSwapUsedMemory();
	}
	
	/**
	 * Sets the total amount of memory on system.
	 */
	private void setTotalMemory() {
		long totalMemoryByte = XrayController.SysInf.getHardware().getMemory().getTotal(); // Bytes
		double totalMemoryByteXB = byteToXB(totalMemoryByte);
		if (totalMemoryByte <= toKiBLimit) {
			memoryTotal = totalMemoryByteXB + kibibytes;
		}
		else if (totalMemoryByte <= toMiBLimit) {
			memoryTotal = totalMemoryByteXB + mebibytes;
		}
		else if (totalMemoryByte <= toGiBLimit) {
			memoryTotal = totalMemoryByteXB + gibibytes;
		}
		else if (totalMemoryByte <= toTiBLimit) {
			memoryTotal = totalMemoryByteXB + tebibytes;
		}
		else {
			memoryTotal = totalMemoryByte + bytes;
		}
		
		memoryTotal = XrayController.helper.stringIsEmptyOrUnknownOrNull(memoryTotal);
	}
	
	/**
	 * Sets the available memory on system.
	 */
	private void setAvailableMemory() {
		long availableMemoryByte = XrayController.SysInf.getHardware().getMemory().getAvailable(); // Bytes
		double availableMemoryByteXB = byteToXB(availableMemoryByte);
		if (availableMemoryByte <= toKiBLimit) {
			memoryAvailable = availableMemoryByteXB + kibibytes;
		}
		else if (availableMemoryByte <= toMiBLimit) {
			memoryAvailable = availableMemoryByteXB + mebibytes;
		}
		else if (availableMemoryByte <= toGiBLimit) {
			memoryAvailable = availableMemoryByteXB + gibibytes;
		}
		else if (availableMemoryByte <= toTiBLimit) {
			memoryAvailable = availableMemoryByteXB + tebibytes;
		}
		else {
			memoryAvailable = availableMemoryByte + bytes;
		}
		
		memoryAvailable = XrayController.helper.stringIsEmptyOrUnknownOrNull(memoryAvailable);
	}
	
	/**
	 * Sets the total swap memory. 
	 */
	private void setSwapTotalMemory() {
		long swapTotalMemoryByte = XrayController.SysInf.getHardware().getMemory().getSwapTotal(); // Bytes
		double swapTotalMemoryByteXB = byteToXB(swapTotalMemoryByte);
		if (swapTotalMemoryByte <= toKiBLimit) {
			memorySwapTotal = swapTotalMemoryByteXB + kibibytes;
		}
		else if (swapTotalMemoryByte <= toMiBLimit) {
			memorySwapTotal = swapTotalMemoryByteXB + mebibytes;
		}
		else if (swapTotalMemoryByte <= toGiBLimit) {
			memorySwapTotal = swapTotalMemoryByteXB + gibibytes;
		}
		else if (swapTotalMemoryByte <= toTiBLimit) {
			memorySwapTotal = swapTotalMemoryByteXB + tebibytes;
		}
		else {
			memorySwapTotal = swapTotalMemoryByte + bytes;
		}
		
		memorySwapTotal = XrayController.helper.stringIsEmptyOrUnknownOrNull(memorySwapTotal);
	}
	
	/**
	 * Sets the used swap memory.
	 */
	private void setSwapUsedMemory() {
		long swapUsedMemoryByte = XrayController.SysInf.getHardware().getMemory().getSwapUsed(); // Bytes
		double swapUsedMemoryByteXB = byteToXB(swapUsedMemoryByte);
		if (swapUsedMemoryByte <= toKiBLimit) {
			memorySwapUsed = swapUsedMemoryByteXB + kibibytes;
		}
		else if (swapUsedMemoryByte <= toMiBLimit) {
			memorySwapUsed = swapUsedMemoryByteXB + mebibytes;
		}
		else if (swapUsedMemoryByte <= toGiBLimit) {
			memorySwapUsed = swapUsedMemoryByteXB + gibibytes;
		}
		else if (swapUsedMemoryByte <= toTiBLimit) {
			memorySwapUsed = swapUsedMemoryByteXB + tebibytes;
		}
		else {
			memorySwapUsed = swapUsedMemoryByte + bytes;
		}
		
		memorySwapUsed = XrayController.helper.stringIsEmptyOrUnknownOrNull(memorySwapUsed);
	}
	
	/**
	 * Decides how to calculate depending on sise of bytes.
	 * (Decides to show as TiB, GiB, MiB, KiB or Byte).
	 * @param bytes
	 * @return
	 */
	private double byteToXB(long bytes) {
		final double toKiB = 0.0009;
		final double toMiB = 0.000000953674316406250000000000;
		final double toGiB = 0.000000000931322574615478515625;
		final double toTiB = 0.000000000000909494701772928238;

		double memoryByteXB = 0;
		if (bytes <= toKiBLimit) {
			memoryByteXB = bytes * toKiB;
		}
		else if (bytes <= toMiBLimit) {
			memoryByteXB = bytes * toMiB;
		}
		else if (bytes <= toGiBLimit) {
			memoryByteXB = bytes * toGiB;
		}
		else if (bytes <= toTiBLimit) {
			memoryByteXB = bytes * toTiB;
		}
		else {
			return bytes;
		}
		return memoryByteXB;
	}
}
