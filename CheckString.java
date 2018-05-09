import java.util.ArrayList;

public interface CheckString {

	/**
	 * Cut string to substring
	 * 
	 * @param line
	 * @param first
	 * @param last
	 * @return
	 */
	String cutTheString(String line, int first, int last);

	/**
	 * Get the strings between the slashes
	 * 
	 * @param line
	 * @return arrayList of type string
	 */
	ArrayList<String> getBetweenSlashs(String line);

	/**
	 * Check whether the string have 3 dots in the end.
	 * 
	 * @param line
	 * @return
	 */
	boolean findDots(String line);
}
