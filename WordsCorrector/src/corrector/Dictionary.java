package corrector;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Dictionary {
	private static Dictionary instance;
	private List<String> localDictionary;
	private File dictionaryFile;

	private Dictionary() {
		loadLocalDictionary();
	}

	public static Dictionary getInstance() {
		if (instance == null) {
			instance = new Dictionary();
		}

		return instance;
	}

	public String checkWord(String word) {
		int wordIndex;
		String suggestedWord = word;
		ProbabilisticComparator comparator = new ProbabilisticComparator();

		wordIndex = Collections.binarySearch(localDictionary, word, null);

		if (wordIndex < 0) {
			wordIndex = Collections.binarySearch(localDictionary, word, comparator);
		}

		if (wordIndex >= 0) {
			suggestedWord = localDictionary.get(wordIndex);
		} else {
			String[] suggestedWords = checkWordInRAEWebSite(word);

			if (suggestedWords.length >= 1) {
				suggestedWord = suggestedWords[0];
			}
			/*
			 * for (String string : checkWordInRAEWebSite(word)) { if
			 * (comparator.compare(string, word) == 0) { suggestedWord = string;
			 * break; } }
			 */

			// addWordToLocalDictionary(wordIndex, word, suggestedWords);
		}

		return suggestedWord;
	}

	private void addWordToLocalDictionary(int index, String word, String[] suggestedWords) {
		if (suggestedWords != null) {
			// for (String suggestedWord : suggestedWords) {

			boolean equal = false;// ProbabilisticComparator.compare(word,
									// suggestedWords[0]);

			if (equal) {
				// break;
				localDictionary.add(Math.abs(index), suggestedWords[0]);
				// saveLocalDictionary();
			}
			// }
		}

	}

	public String[] checkWordInRAEWebSite(String word) {
		String RAEResponse = RAEInterface.getInstance().searchWord(word);
		Document document = Jsoup.parse(RAEResponse);
		Element htmlElementL0 = document.getElementById("l0");
		Element htmlElementL1 = document.getElementById("l1");
		Element htmlElementA0 = document.getElementById("a0");
		String[] suggestedWords = new String[] {};

		if (htmlElementA0 != null) {
			String htmlElementText = htmlElementA0.text();
			htmlElementText = htmlElementText.replaceFirst("\\..*", "");
			htmlElementText = htmlElementText.replaceFirst("\\d", "");
			htmlElementText = htmlElementText.replaceAll(",.*", "");
			suggestedWords = new String[] { htmlElementText };

		} else if (htmlElementL1 != null) {
			String htmlElementText = htmlElementL1.text();
			htmlElementText = htmlElementText.replaceFirst(".*relacionada.*:", "");
			htmlElementText = htmlElementText.replaceFirst("Real Academia.*", "");
			htmlElementText = htmlElementText.replaceAll(",", ".");
			htmlElementText = htmlElementText.trim();

			suggestedWords = htmlElementText.split("\\. *");

			for (int i = 0; i < suggestedWords.length; i++) {
				suggestedWords[i] = suggestedWords[i].replaceFirst("\\..*", "");
				suggestedWords[i] = suggestedWords[i].replaceFirst("\\d", "");
			}
		} else if (htmlElementL0 != null) {
			String htmlElementText = htmlElementL0.text();
			htmlElementText = htmlElementText.replaceFirst("Real Academia.*", "");
			htmlElementText = htmlElementText.replaceAll(",", ".");
			htmlElementText = htmlElementText.trim();
			suggestedWords = htmlElementText.split("\\. *");

			for (int i = 0; i < suggestedWords.length; i++) {
				suggestedWords[i] = suggestedWords[i].replaceFirst("\\..*", "");
				suggestedWords[i] = suggestedWords[i].replaceFirst("\\d", "");
			}
		} else {
			// System.out.println(RAEResponse);
		}

		return suggestedWords;
	}

	private void loadLocalDictionary() {
		localDictionary = new ArrayList<String>(Arrays.asList(readLocalDictionary().split(", ")));
		// Collections.sort(localDictionary);
	}

	private String readLocalDictionary() {
		String fileContent = null;

		try {
			fileContent = IOUtils
					.toString(Thread.currentThread().getContextClassLoader().getResourceAsStream("spanish.txt"));
		} catch (IOException e) {
			Main.showMessage("No fué posible cargar el archivo del diccionario", e.getMessage());
			System.exit(0);
		}

		return fileContent;
	}

	public void saveLocalDictionary() {
		String words = localDictionary.toString().replace("[", "").replace("]", "");

		try {
			FileUtils.writeStringToFile(dictionaryFile, words, Charsets.UTF_8, false);
		} catch (IOException e) {
			Main.showMessage("Ha ocurrido un fallo al guardar el diccionario", e.getMessage());
		}
	}

}
