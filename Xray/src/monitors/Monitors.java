package monitors;

import controllers.XrayController;
import javafx.scene.control.ListView;

/**
 * The "Monitors section" class of the app.
 * 
 * @author PilzHere
 *
 */
public class Monitors {

	private XrayController controller;

	private String monitorsCount, monitorData;

	public Monitors(XrayController controller) {
		this.controller = controller;
	}

	/**
	 * Goes through the passed list. Counts the monitors and sets their number to
	 * the list.
	 * 
	 * @param monitorsList
	 */
	public void setMonitorsData(ListView<String> monitorsList) {
		String monitorsCount = String.valueOf(XrayController.SYS_INF.getHardware().getDisplays().length);
		setMonitorsCount(monitorsCount);

		for (int i = 0; i < XrayController.SYS_INF.getHardware().getDisplays().length; i++) {
			monitorsList.getItems().add("Connected monitor #" + (i + 1));
		}
	}

	private int chosenMonitor = 0;
	private int lastChosenMonitor = -1;

	/**
	 * The event that starts if the user clicks in the monitors list.
	 * 
	 * @param monitorsList
	 */
	public void monitorsListClicked(ListView<String> monitorsList) {

		chosenMonitor = monitorsList.getSelectionModel().getSelectedIndex();

		if (chosenMonitor != lastChosenMonitor) {
			if (chosenMonitor >= 0 && chosenMonitor < monitorsList.getItems().size()) {

				updateMonitorData(chosenMonitor);
			}
			lastChosenMonitor = chosenMonitor;

			controller.setMonitorsLabels();
		}
	}

	/**
	 * Updates the chosen monitor's data.
	 * 
	 * @param chosenInterface
	 */
	private void updateMonitorData(int chosenMonitor) {
		setMonitorData(XrayController.SYS_INF.getHardware().getDisplays()[chosenMonitor].toString());
	}

	public String getMonitorsCount() {
		return monitorsCount;
	}

	private void setMonitorsCount(String monitorsCount) {
		this.monitorsCount = monitorsCount;
	}

	public String getMonitorData() {
		return monitorData;
	}

	private void setMonitorData(String monitorData) {
		this.monitorData = monitorData;
	}
}
