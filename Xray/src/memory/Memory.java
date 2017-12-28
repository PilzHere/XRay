package memory;

import controllers.XrayController;

/**
 * The "Memory section" class of the app. Contains data about system memory.
 * 
 * @author PilzHere
 *
 */
public class Memory {

	private String memoryTotal, memoryAvailable, memorySwapTotal, memorySwapUsed;

	private final long toKiBLimit = 1022976L;
	private final long toMiBLimit = 1047527424L;
	private final long toGiBLimit = 1072668082176L;
	private final long toTiBLimit = 1098412116148220L;
	private final long toPiBLimit = 1124774006935781376L;

	private final String bytes = " B";
	private final String kibibytes = " KiB";
	private final String mebibytes = " MiB";
	private final String gibibytes = " GiB";
	private final String tebibytes = " TiB";
	private final String pebibytes = " PiB";

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
		long totalMemoryByte = XrayController.SYS_INF.getHardware().getMemory().getTotal(); // Bytes
		double totalMemoryByteXB = byteToXB(totalMemoryByte);
		if (totalMemoryByte <= toKiBLimit) {
			setMemoryTotal(totalMemoryByteXB + kibibytes);
		} else if (totalMemoryByte <= toMiBLimit) {
			setMemoryTotal(totalMemoryByteXB + mebibytes);
		} else if (totalMemoryByte <= toGiBLimit) {
			setMemoryTotal(totalMemoryByteXB + gibibytes);
		} else if (totalMemoryByte <= toTiBLimit) {
			setMemoryTotal(totalMemoryByteXB + tebibytes);
		} else {
			setMemoryTotal(totalMemoryByte + bytes);
		}

		setMemoryTotal(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getMemoryTotal()));
	}

	/**
	 * Sets the available memory on system.
	 */
	private void setAvailableMemory() {
		long availableMemoryByte = XrayController.SYS_INF.getHardware().getMemory().getAvailable(); // Bytes
		double availableMemoryByteXB = byteToXB(availableMemoryByte);
		if (availableMemoryByte <= toKiBLimit) {
			setMemoryAvailable(availableMemoryByteXB + kibibytes);
		} else if (availableMemoryByte <= toMiBLimit) {
			setMemoryAvailable(availableMemoryByteXB + mebibytes);
		} else if (availableMemoryByte <= toGiBLimit) {
			setMemoryAvailable(availableMemoryByteXB + gibibytes);
		} else if (availableMemoryByte <= toTiBLimit) {
			setMemoryAvailable(availableMemoryByteXB + tebibytes);
		} else {
			setMemoryAvailable(availableMemoryByte + bytes);
		}

		setMemoryAvailable(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getMemoryAvailable()));
	}

	/**
	 * Sets the total swap memory.
	 */
	private void setSwapTotalMemory() {
		long swapTotalMemoryByte = XrayController.SYS_INF.getHardware().getMemory().getSwapTotal(); // Bytes
		double swapTotalMemoryByteXB = byteToXB(swapTotalMemoryByte);
		if (swapTotalMemoryByte <= toKiBLimit) {
			setMemorySwapTotal(swapTotalMemoryByteXB + kibibytes);
		} else if (swapTotalMemoryByte <= toMiBLimit) {
			setMemorySwapTotal(swapTotalMemoryByteXB + mebibytes);
		} else if (swapTotalMemoryByte <= toGiBLimit) {
			setMemorySwapTotal(swapTotalMemoryByteXB + gibibytes);
		} else if (swapTotalMemoryByte <= toTiBLimit) {
			setMemorySwapTotal(swapTotalMemoryByteXB + tebibytes);
		} else {
			setMemorySwapTotal(swapTotalMemoryByte + bytes);
		}

		setMemorySwapTotal(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getMemorySwapTotal()));
	}

	/**
	 * Sets the used swap memory.
	 */
	private void setSwapUsedMemory() {
		long swapUsedMemoryByte = XrayController.SYS_INF.getHardware().getMemory().getSwapUsed(); // Bytes
		double swapUsedMemoryByteXB = byteToXB(swapUsedMemoryByte);
		if (swapUsedMemoryByte <= toKiBLimit) {
			setMemorySwapUsed(swapUsedMemoryByteXB + kibibytes);
		} else if (swapUsedMemoryByte <= toMiBLimit) {
			setMemorySwapUsed(swapUsedMemoryByteXB + mebibytes);
		} else if (swapUsedMemoryByte <= toGiBLimit) {
			setMemorySwapUsed(swapUsedMemoryByteXB + gibibytes);
		} else if (swapUsedMemoryByte <= toTiBLimit) {
			setMemorySwapUsed(swapUsedMemoryByteXB + tebibytes);
		} else {
			setMemorySwapUsed(swapUsedMemoryByte + bytes);
		}

		setMemorySwapUsed(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getMemorySwapUsed()));
	}

	/**
	 * Decides how to calculate depending on sise of bytes. (Decides to show as TiB,
	 * GiB, MiB, KiB or Byte).
	 * 
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
		} else if (bytes <= toMiBLimit) {
			memoryByteXB = bytes * toMiB;
		} else if (bytes <= toGiBLimit) {
			memoryByteXB = bytes * toGiB;
		} else if (bytes <= toTiBLimit) {
			memoryByteXB = bytes * toTiB;
		} else {
			return bytes;
		}
		return memoryByteXB;
	}

	public String getMemoryTotal() {
		return memoryTotal;
	}

	private void setMemoryTotal(String memoryTotal) {
		this.memoryTotal = memoryTotal;
	}

	public String getMemoryAvailable() {
		return memoryAvailable;
	}

	private void setMemoryAvailable(String memoryAvailable) {
		this.memoryAvailable = memoryAvailable;
	}

	public String getMemorySwapTotal() {
		return memorySwapTotal;
	}

	private void setMemorySwapTotal(String memorySwapTotal) {
		this.memorySwapTotal = memorySwapTotal;
	}

	public String getMemorySwapUsed() {
		return memorySwapUsed;
	}

	private void setMemorySwapUsed(String memorySwapUsed) {
		this.memorySwapUsed = memorySwapUsed;
	}
}
