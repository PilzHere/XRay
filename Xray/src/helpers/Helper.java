package helpers;

/**
 * Contains functions to help and minimize code in other classes.
 * @author PilzHere
 *
 */
public class Helper {

//	Strings
	public static final String UNKNOWN_UGLY = "unknown";
	public static final String UNKNOWN_NICE = "Unknown";
	public static final String NONE = "None";
	public static final String NONE_OR_UNKNOWN = "None or unknown";
	
	/**
	 * If the passed string is empty (length = 0), "unknown" or null or contains "unknown"
	 * the returned string is set to "Unknown".
	 * @param string
	 * @return
	 */
	public String stringIsEmptyOrUnknownOrNull(String string) {
//		System.out.println(string);
		if (string.isEmpty() || string.equals(UNKNOWN_UGLY) || string == null || string.contains(UNKNOWN_UGLY)) {
			string = UNKNOWN_NICE;
//			System.out.println(string);
		}
		return string;
	}
}
