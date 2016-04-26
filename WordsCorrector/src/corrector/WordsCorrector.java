package corrector;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public class WordsCorrector {

	public static List<String[]> getMalformedWords() {
		ArrayList<String[]> words = new ArrayList<String[]>();
		String[] tokens;

		tokens = readMalformedWordsFromFile().replace(System.lineSeparator(), ",").split(",");

		for (String string : tokens) {
			String[] splittedToken = StringUtils.split(string.trim());

			if (splittedToken.length <= 2) {
				words.add(splittedToken);
			} else {
				System.err.println("Malformed token " + Arrays.toString(splittedToken));
			}
		}

		return words;
	}

	public static List<String> getDictionary() {
		String[] words = readDictionary().split(System.lineSeparator());

		return Arrays.asList(words);
	}

	private static String readMalformedWordsFromFile() {
		String fileContent = null;
		File file = FileUtils.getFile("source/output.txt");

		try {
			fileContent = FileUtils.readFileToString(file, Charsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}

		return fileContent;
	}

	private static String readDictionary() {
		String fileContent = null;
		File file = FileUtils.getFile("source/spanish.txt");

		try {
			fileContent = FileUtils.readFileToString(file, Charsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}

		return fileContent;
	}

}
