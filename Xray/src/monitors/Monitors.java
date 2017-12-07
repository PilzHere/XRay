package monitors;

import controllers.XrayController;
import javafx.scene.control.ListView;

/**
 * The "Monitors section" class of the app.
 * @author PilzHere
 *
 */
public class Monitors {

	private XrayController controller;
	
//	These strings are collected by XrayController.
	public String monitorsCount;
	public String monitorData;
	
	public Monitors(XrayController controller) {
		this.controller = controller;
	}
	
	/**
	 * Goes through the passed list.
	 * Counts the monitors and sets their number to the list.
	 * @param monitorsList
	 */
	public void setMonitorsData(ListView<String> monitorsList) {
		String monitorsCount = String.valueOf(XrayController.SysInf.getHardware().getDisplays().length);
		this.monitorsCount = monitorsCount;
		
		for (int i = 0; i < XrayController.SysInf.getHardware().getDisplays().length; i++) {
			monitorsList.getItems().add("Connected monitor " + (i + 1));
		}
	}
	
	int chosenMonitor = 0;
	int lastChosenMonitor = -1;
	
	/**
	 * The event that starts if the user clicks in the monitors list.
	 * @param monitorsList
	 */
	public void monitorsListClicked(ListView<String> monitorsList) {
		
		chosenMonitor = monitorsList.getSelectionModel().getSelectedIndex();
		
		if (chosenMonitor != lastChosenMonitor) {
			if (chosenMonitor >= 0 && chosenMonitor < monitorsList.getItems().size()) {

				updateMonitorData(chosenMonitor);
				
//				String monitorsCount = String.valueOf(monitorsList.getItems().size());
//				this.monitorsCount = monitorsCount;
			}
			lastChosenMonitor = chosenMonitor;
			
			controller.setMonitorsLabels();
		}
	}
	
	/**
	 * Updates the chosen monitor's data.
	 * @param chosenInterface
	 */
	private void updateMonitorData(int chosenMonitor) {
		monitorData = XrayController.SysInf.getHardware().getDisplays()[chosenMonitor].toString();
	}
}
