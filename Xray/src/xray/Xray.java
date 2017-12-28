package xray;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Main class of XRay.
 * 
 * @author PilzHere
 *
 */
public class Xray extends Application {

	private static final String APP_TITLE = "XRay";

	private static final int VERS1 = 1;
	private static final int VERS2 = 1;
	private static final int VERS3 = 1;
	private static final String VERSION = VERS1 + "." + VERS2 + "." + VERS3;
	private static final String APP_TITLE_VERSION = APP_TITLE + " " + VERSION;

	private static final String DEVELOPER = "Christian" + " " + "\"PilzHere\"" + " " + "Pilz";
	private static final String DEVELOPER_WEBSITE = "http://www.christianpilz.com/";
	private static final String DEVELOPER_MAIL = "contact@christianpilz.com";

	private static final int WINDOW_DEFAULT_WIDTH = 854;
	private static final int WINDOW_DEFUALT_HEIGHT = 480;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Xray.fxml"));

			Scene scene = new Scene(root, WINDOW_DEFAULT_WIDTH, WINDOW_DEFUALT_HEIGHT);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			Image icon = new Image(Xray.class.getResourceAsStream("/XRayIcon.png"));

			if (icon != null) {
				stage.getIcons().add(icon);
			}

			stage.setTitle(APP_TITLE_VERSION);
			stage.setResizable(false);
			stage.setFullScreen(false);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getVersion() {
		return VERSION;
	}

	public static String getDeveloper() {
		return DEVELOPER;
	}

	public static String getDeveloperWebsite() {
		return DEVELOPER_WEBSITE;
	}

	public static String getDeveloperMail() {
		return DEVELOPER_MAIL;
	}
}
