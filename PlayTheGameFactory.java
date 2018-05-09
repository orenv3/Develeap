import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class PlayTheGameFactory {

	private CheckStringInFile methodsOnFile = new CheckStringInFile();
	private HashMap<String, Integer> mainList = new HashMap<>();
	private int countBooks = 0;
	private String autor1 = "";
	private String autor2 = "";
	private String autor3 = "";
	private String autorTemp = "";

	/**
	 * The main file parses. The method provide the following: 1) How many books
	 * are listed in the file (2) How many authors are listen in the file (3)
	 * list the 3 authors with the most books
	 * 
	 * @param path
	 *            the path of the file
	 * @return void method. all the output is in the console
	 */
	public void check(String path) {
		BufferedReader in = null;
		String fullLine = "";
		String firstSubLine = "";
		String secSubLine = "";

		try {
			in = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		boolean flag = true;
		while (flag) {

			try {
				fullLine = in.readLine();
				if (fullLine == null) {
					in.close();
					flag = false;
					break;
				}
				firstSubLine = fullLine.substring(0, 40);
				secSubLine = fullLine.substring(40, 70);

				if (methodsOnFile.findDots(firstSubLine) && !(methodsOnFile.findDots(secSubLine))) {
				} else
					countBooks++;
				// if in the end of the line of author 3 dots.
				// merge them to one line and ignore books count once.
				if (methodsOnFile.findDots(secSubLine)) {
					String nextLine = in.readLine();
					String sec = nextLine.substring(40, 70);
					secSubLine = methodsOnFile.margeTwoString(secSubLine, sec);
				}
				// if there are slashes then put in the Hash the names between
				// the slashes
				if (methodsOnFile.findSlash(secSubLine)) {
					ArrayList<String> secSubList = methodsOnFile.getBetweenSlashs(secSubLine);
					for (String string : secSubList) {
						if (mainList.containsKey(string)) {
							int tmp = mainList.get(string);
							mainList.replace(string, ++tmp);
						} else {
							this.fitToThirty(string);
							mainList.put(this.autorTemp, 1);
						}
					}
				}

				// if the name is in the Hash just increase the author
				// appearance count
				else if (mainList.containsKey(secSubLine)) {
					int tmp = mainList.get(secSubLine);
					mainList.replace(secSubLine, ++tmp);
				} else {
					// author name is always 30 characters - check empty one
					if (!(secSubLine.equals("                              "))) {
						this.fitToThirty(secSubLine);
						mainList.put(this.autorTemp, 1);
					}
				}

			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		ArrayList<Integer> list = this.getTheBigThree(mainList);
		System.out.println(
				"There are " + this.countBooks + " books and " + this.mainList.size() + " autors in this file.");
		System.out.println("The most popular autors are:");
		System.out.println("-" + autor1 + "( " + list.get(2) + " titles");
		System.out.println("-" + autor2 + "( " + list.get(1) + " titles");
		System.out.println("-" + autor3 + "( " + list.get(0) + " titles");
		System.out.println("\n======== all authors in the file are: ==========");
		for (String s : mainList.keySet()) {
			System.out.println("||" + s + "\t with " + mainList.get(s) + " books");
		}
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the three authors that appeared most in the file
	 * 
	 * @param theMainList
	 * @return arrayList of Integers
	 */
	private ArrayList<Integer> getTheBigThree(HashMap<String, Integer> theMainList) {
		int first = -10;
		int second = -10;
		int third = -10;
		int index = -1;
		ArrayList<Integer> list = new ArrayList<>();

		for (String key : theMainList.keySet()) {// mainList
			index = theMainList.get(key);
			if (index >= first) {
				autor3 = autor2;
				autor2 = autor1;
				autor1 = key;
				third = second;
				second = first;
				first = index;
			} else if (index >= second) {
				autor3 = autor2;
				autor2 = key;
				third = second;
				second = index;
			} else if (index > third) {
				autor3 = key;
				third = index;
			}
		}
		list.add(0, third);
		list.add(1, second);
		list.add(2, first);
		return list;
	}

	private void fitToThirty(String author) {
		final int thirty = 30;
		int nameSize = author.length();
		for (int i = nameSize; i < thirty; i++) {
			author += " ";
		}
		this.autorTemp = author;
	}
}
