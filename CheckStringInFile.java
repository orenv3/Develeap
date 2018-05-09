import java.util.ArrayList;

public class CheckStringInFile implements CheckString {

	/**
	 * Cut the String to specific piece via String's method
	 * substring(beginIndex, endIndex)
	 * 
	 * @return string witch is substring
	 */
	@Override
	public String cutTheString(String line, int beginIndex, int endIndex) {
		if (line.isEmpty())
			return "The end";
		String part = line.substring(beginIndex, endIndex);
		return part;
	}

	/**
	 * Get list of authors. This method return ArrayList of string (authors)
	 * that are between slashes
	 * 
	 * @return ArrayList of type string
	 */
	@Override
	public ArrayList<String> getBetweenSlashs(String line) {
		ArrayList<String> listOfAuthors = new ArrayList<>();
		if (line.isEmpty())
			return null;
		int beginIndex = 0;
		int endIndex = 0;
		int slashAmount = this.slashCount(line);
		int i = 0;
		boolean flag = true;
		while (flag) {
			if (line.charAt(i) == '/') {
				slashAmount--;
				String tmp = cutTheString(line, beginIndex, endIndex);
				if (!(tmp.isEmpty()))
					listOfAuthors.add(tmp);
				beginIndex = i;
				beginIndex++;
				endIndex = i;
				endIndex++;
				i++;
				if (slashAmount == 0)
					flag = false;
				if (i == line.length())
					flag = false;
			} else {
				endIndex = i;
				endIndex++;
				i++;
			}
		}

		String tmp = cutTheString(line, beginIndex, line.length());
		if (!(tmp.isEmpty()))
			listOfAuthors.add(tmp);
		return listOfAuthors;

	}

	/**
	 * This method is checking whether the line have three dots in the end or
	 * not.
	 * 
	 * @return true if the line have 3 dots in the end, otherwise false
	 */
	@Override
	public boolean findDots(String line) {
		if (line.isEmpty())
			return false;
		boolean flag = false;
		int i = line.length() - 4;
		for (; i < line.length(); i++) {
			if (line.charAt(i) == '.') {
				if (i + 2 <= line.length() - 1)
					if (line.charAt(i + 1) == '.' && line.charAt(i + 2) == '.')
						flag = true;
			}
		}
		return flag;
	}

	/**
	 * Merge two strings && ignoring the three dots
	 * 
	 * @param first
	 * @param sec
	 * @return string.
	 */
	public String margeTwoString(String first, String sec) {
		String newFirst = first.substring(0, first.length() - 3);
		return newFirst + sec;
	}

	/**
	 * Check if there are slashes in the line
	 * 
	 * @param line
	 * @return true if there are slashes, otherwise, false.
	 */
	public boolean findSlash(String line) {
		if (slashCount(line) == 0)
			return false;
		return true;
	}

	/**
	 * Get the amount of the slashes in the string.
	 * 
	 * @param string
	 * @return The amount of the slashes in the string
	 */
	private int slashCount(String line) {
		int count = 0;
		if (line.isEmpty())
			return 0;
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == '/') {
				++count;
			}
		}
		return count;
	}

}
