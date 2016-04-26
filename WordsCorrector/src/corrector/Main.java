package corrector;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Main {

	public static void main(String[] args) {
		List<String[]> malformedWords = WordsCorrector.getMalformedWords();

		for (String[] word : malformedWords) {
			String fixWord = word[1];
			String wordText = word[0];

			if (!Boolean.valueOf(fixWord) && !wordText.startsWith("@") && !wordText.startsWith("#")) {
				fixWord(wordText);
			}
		}
	}

	private static void fixWord(String malformedWord) {
		List<String> dictionary = WordsCorrector.getDictionary();

		Collections.sort(dictionary);

		int index = Collections.binarySearch(dictionary, malformedWord, null);// new
																				// ProbabilisticComparator());

		if (index > 0) {
			System.out.println(malformedWord + " " + dictionary.get(index));
		} else {
			System.err.println("\"" + malformedWord + "\"" + " no se encontró, buscando en la RAE");
			String[] suggestedWords = Dictionary.getInstance().checkWord(malformedWord);
			System.err.println("\"" + malformedWord + "\"" + Arrays.toString(suggestedWords));
		}

	}

}
