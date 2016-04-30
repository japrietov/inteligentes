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

	private static List<String[]> readWordsToFix(String fileName) {
		ArrayList<String[]> words = new ArrayList<String[]>();
		String[] tokens;

		tokens = loadWordsFromFile(fileName).replace(System.lineSeparator(), ",").split(",");

		for (String string : tokens) {
			String[] splittedToken = StringUtils.split(string.trim());

			if (splittedToken.length == 2) {
				words.add(splittedToken);
			} else {
				Main.showMessage("Token mal formado", Arrays.toString(splittedToken));
			}
		}

		return words;
	}

	private static String loadWordsFromFile(String fileName) {
		String fileContent = null;
		File file = FileUtils.getFile(fileName);

		try {
			fileContent = FileUtils.readFileToString(file, Charsets.UTF_8);
		} catch (IOException e) {
			Main.showMessage("No fué posible cargar el archivo de palabras para corregir", e.getMessage());
			System.exit(0);
		}

		return fileContent;
	}

	public static void fixWords(String fileName) {
		File file = new File("fixedWords.txt");
		List<String[]> requests = readWordsToFix(fileName);
		String result = "";

		writeFile(file, result, false);

		for (int i = 0; i < requests.size(); i++) {
			String[] request = requests.get(i);

			if (request.length == 2) {
				String fixWord = request[1];
				String word = request[0];

				boolean isNumber = word.replaceFirst("\\d*\\s*", "").equals("");

				if (!Boolean.valueOf(fixWord) && !word.startsWith("@") && !word.startsWith("#") && !isNumber) {
					String fixedWord = Dictionary.getInstance().checkWord(word);
					result = word + ":" + fixedWord + ",";// System.lineSeparator();

					writeFile(file, result, true);
				}

				System.err.println((float) i * 100 / requests.size() + "%");
			}
		}

	}

	private static void writeFile(File file, String result, boolean append) {
		try {
			FileUtils.writeStringToFile(file, result, append);
		} catch (IOException e) {
			Main.showMessage("No fué posible guardar el resultado", e.getMessage());
		}
	}

}
