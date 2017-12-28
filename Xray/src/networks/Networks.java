package networks;

import controllers.XrayController;
import javafx.scene.control.ListView;

/**
 * The "Network section" class of the app. Contains data such as interfaceName,
 * ipv4, ipv6, MAC, MTU and more.
 * 
 * @author PilzHere
 *
 */
public class Networks {

	private XrayController controller;

	private String networkInterfacesCount, interfaceName, interfaceDisplayname, interfaceIPv4, interfaceIPv6,
			interfaceMAC, interfaceMTU;

	public Networks(XrayController controller) {
		this.controller = controller;
	}

	/**
	 * Goes through the passed list. Counts the interfaces and sets their name to
	 * the list.
	 * 
	 * @param interfacesList
	 */
	public void setNetworkInterfacesData(ListView<String> interfacesList) {
		String interfacesCount = String.valueOf(XrayController.SYS_INF.getHardware().getNetworkIFs().length);
		setNetworkInterfacesCount(interfacesCount);

		for (int i = 0; i < XrayController.SYS_INF.getHardware().getNetworkIFs().length; i++) {
			interfacesList.getItems().add(XrayController.SYS_INF.getHardware().getNetworkIFs()[i].getDisplayName());
		}
	}

	private int chosenInterface = 0;
	private int lastChosenInterface = -1;

	/**
	 * The event that starts if the user clicks in the Network Interfaces list.
	 * 
	 * @param interfacesList
	 */
	public void networksInterfacesListClicked(ListView<String> interfacesList) {

		chosenInterface = interfacesList.getSelectionModel().getSelectedIndex();

		if (chosenInterface != lastChosenInterface) {
			if (chosenInterface >= 0 && chosenInterface < interfacesList.getItems().size()) {

				updateInterfaceData(chosenInterface);

				String interfacesCount = String.valueOf(interfacesList.getItems().size());
				setNetworkInterfacesCount(interfacesCount);
			}
			lastChosenInterface = chosenInterface;

			controller.setNetworkInterfacesLabels();
		}
	}

	private final String empty = "";

	/**
	 * Updates the chosen Network Interface's data.
	 * 
	 * @param chosenInterface
	 */
	private void updateInterfaceData(int chosenInterface) {
		setInterfaceName(XrayController.SYS_INF.getHardware().getNetworkIFs()[chosenInterface].getName());
		setInterfaceName(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getInterfaceName()));

		setInterfaceDisplayname(XrayController.SYS_INF.getHardware().getNetworkIFs()[chosenInterface].getDisplayName());
		setInterfaceDisplayname(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getInterfaceDisplayname()));

		setInterfaceIPv4(empty);
		setInterfaceIPv6(empty);

		String[] bytes = XrayController.SYS_INF.getHardware().getNetworkIFs()[chosenInterface].getIPv4addr();
		for (String s : bytes) {
			setInterfaceIPv4(s);
		}
		setInterfaceIPv4(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getInterfaceIPv4()));

		String[] bytes2 = XrayController.SYS_INF.getHardware().getNetworkIFs()[chosenInterface].getIPv6addr();
		for (String s : bytes2) {
			setInterfaceIPv6(s);
		}
		setInterfaceIPv6(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getInterfaceIPv6()));

		setInterfaceMAC(XrayController.SYS_INF.getHardware().getNetworkIFs()[chosenInterface].getMacaddr());
		setInterfaceMAC(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getInterfaceMAC()));

		int mtu = XrayController.SYS_INF.getHardware().getNetworkIFs()[chosenInterface].getMTU();
		String mTU = String.valueOf(mtu);
		setInterfaceMTU(mTU);
		setInterfaceMTU(XrayController.HELPER.stringIsEmptyOrUnknownOrNull(getInterfaceMTU()));
	}

	public String getNetworkInterfacesCount() {
		return networkInterfacesCount;
	}

	private void setNetworkInterfacesCount(String networkInterfacesCount) {
		this.networkInterfacesCount = networkInterfacesCount;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	private void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getInterfaceDisplayname() {
		return interfaceDisplayname;
	}

	private void setInterfaceDisplayname(String interfaceDisplayname) {
		this.interfaceDisplayname = interfaceDisplayname;
	}

	public String getInterfaceIPv4() {
		return interfaceIPv4;
	}

	private void setInterfaceIPv4(String interfaceIPv4) {
		this.interfaceIPv4 = interfaceIPv4;
	}

	public String getInterfaceIPv6() {
		return interfaceIPv6;
	}

	private void setInterfaceIPv6(String interfaceIPv6) {
		this.interfaceIPv6 = interfaceIPv6;
	}

	public String getInterfaceMAC() {
		return interfaceMAC;
	}

	private void setInterfaceMAC(String interfaceMAC) {
		this.interfaceMAC = interfaceMAC;
	}

	public String getInterfaceMTU() {
		return interfaceMTU;
	}

	private void setInterfaceMTU(String interfaceMTU) {
		this.interfaceMTU = interfaceMTU;
	}
}
