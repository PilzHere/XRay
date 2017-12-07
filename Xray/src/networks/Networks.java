package networks;

import controllers.XrayController;
import javafx.scene.control.ListView;

/**
 * The "Network section" class of the app.
 * Contains data such as interfaceName, ipv4, ipv6, MAC, MTU and more.
 * @author PilzHere
 *
 */
public class Networks {

	private XrayController controller;
	
//	These strings are collected by XrayController.
	public String networkInterfacesCount;
	public String interfaceName;
	public String interfaceDisplayname;
	public String interfaceIPv4;
	public String interfaceIPv6;
	public String interfaceMAC;
	public String interfaceMTU;
	
	public Networks(XrayController controller) {
		this.controller = controller;
	}
	
	/**
	 * Goes through the passed list.
	 * Counts the interfaces and sets their name to the list.
	 * @param interfacesList
	 */
	public void setNetworkInterfacesData(ListView<String> interfacesList) {
		String interfacesCount = String.valueOf(XrayController.SysInf.getHardware().getNetworkIFs().length);
		this.networkInterfacesCount = interfacesCount;
		
		for (int i = 0; i < XrayController.SysInf.getHardware().getNetworkIFs().length; i++) {
			interfacesList.getItems().add(XrayController.SysInf.getHardware().getNetworkIFs()[i].getDisplayName());
		}
	}
	
	int chosenInterface = 0;
	int lastChosenInterface = -1;
	
	/**
	 * The event that starts if the user clicks in the Network Interfaces list.
	 * @param interfacesList
	 */
	public void networksInterfacesListClicked(ListView<String> interfacesList) {
		
		chosenInterface = interfacesList.getSelectionModel().getSelectedIndex();
		
		if (chosenInterface != lastChosenInterface) {
			if (chosenInterface >= 0 && chosenInterface < interfacesList.getItems().size()) {

				updateInterfaceData(chosenInterface);
				
				String interfacesCount = String.valueOf(interfacesList.getItems().size());
				this.networkInterfacesCount = interfacesCount;
			}
			lastChosenInterface = chosenInterface;
			
			controller.setNetworkInterfacesLabels();
		}
	}
	
	/**
	 * Updates the chosen Network Interface's data.
	 * @param chosenInterface
	 */
	private void updateInterfaceData(int chosenInterface) {
		interfaceName = XrayController.SysInf.getHardware().getNetworkIFs()[chosenInterface].getName();
		interfaceName = XrayController.helper.stringIsEmptyOrUnknownOrNull(interfaceName);
		
		interfaceDisplayname = XrayController.SysInf.getHardware().getNetworkIFs()[chosenInterface].getDisplayName();
		interfaceDisplayname = XrayController.helper.stringIsEmptyOrUnknownOrNull(interfaceDisplayname);
		
		interfaceIPv4 = "";
		interfaceIPv6 = "";
		
		String[] bytes = XrayController.SysInf.getHardware().getNetworkIFs()[chosenInterface].getIPv4addr();
		for (String s : bytes) {
			interfaceIPv4 = s;
		}
		interfaceIPv4 = XrayController.helper.stringIsEmptyOrUnknownOrNull(interfaceIPv4);
		
		String[] bytes2 = XrayController.SysInf.getHardware().getNetworkIFs()[chosenInterface].getIPv6addr();
		for (String s : bytes2) {
			interfaceIPv6 = s;
		}
		interfaceIPv6 = XrayController.helper.stringIsEmptyOrUnknownOrNull(interfaceIPv6);
		
		interfaceMAC = XrayController.SysInf.getHardware().getNetworkIFs()[chosenInterface].getMacaddr();
		interfaceMAC = XrayController.helper.stringIsEmptyOrUnknownOrNull(interfaceMAC);
		
		int mtu = XrayController.SysInf.getHardware().getNetworkIFs()[chosenInterface].getMTU();
		String mTU = String.valueOf(mtu);
		interfaceMTU = mTU;
		interfaceMTU = XrayController.helper.stringIsEmptyOrUnknownOrNull(interfaceMTU);
	}
}
