package corrector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Dictionary {
	private static Dictionary instance;

	private Dictionary() {
	}

	public static Dictionary getInstance() {
		if (instance == null) {
			instance = new Dictionary();
		}

		return instance;
	}

	public String[] checkWord(String word) {
		String RAEResponse = RAEInterface.getInstance().searchWord(word);
		Document document = Jsoup.parse(RAEResponse);
		Element htmlElementL0 = document.getElementById("l0");
		Element htmlElementL1 = document.getElementById("l1");
		Element htmlElementA0 = document.getElementById("a0");
		String[] suggestedWords = null;

		if (htmlElementA0 != null) {
			String htmlElementText = htmlElementA0.text();
			htmlElementText = htmlElementText.replaceFirst("\\..*", "");
			htmlElementText = htmlElementText.replaceFirst("\\d", "");
			suggestedWords = new String[] { htmlElementText };

		} else if (htmlElementL1 != null) {
			String htmlElementText = htmlElementL1.text();
			htmlElementText = htmlElementText.replaceFirst(".*relacionada.*:", "");
			htmlElementText = htmlElementText.replaceFirst("Real Academia.*", "");
			htmlElementText = htmlElementText.trim();

			suggestedWords = htmlElementText.split("\\. ");

			for (int i = 0; i < suggestedWords.length; i++) {
				suggestedWords[i] = suggestedWords[i].replaceFirst("\\..*", "");
				suggestedWords[i] = suggestedWords[i].replaceFirst("\\d", "");
			}
		} else if (htmlElementL0 != null) {
			String htmlElementText = htmlElementL0.text();
			htmlElementText = htmlElementText.replaceFirst("Real Academia.*", "");
			htmlElementText = htmlElementText.trim();
			suggestedWords = htmlElementText.split("\\. ");

			for (int i = 0; i < suggestedWords.length; i++) {
				suggestedWords[i] = suggestedWords[i].replaceFirst("\\..*", "");
				suggestedWords[i] = suggestedWords[i].replaceFirst("\\d", "");
			}
		}

		return suggestedWords;
	}

}
