package corrector;

import java.util.Comparator;

import org.apache.commons.lang3.LocaleUtils;
import org.apache.commons.lang3.StringUtils;

public class ProbabilisticComparator implements Comparator<String> {

	public int compare(String word1, String word2) {
		boolean equal = false;
		int result = word1.compareTo(word2);

		if (result != 0) {
			equal = matchByIndexes(word1, word2);// ||
													// matchByFuzzyDistance(word1,
													// word2);
			if (equal) {
				result = 0;
			}
		}

		return result;
	}

	private static boolean matchByFuzzyDistance(String word1, String word2) {
		int fuzzyDistance = StringUtils.getFuzzyDistance(word2, word1, LocaleUtils.toLocale("es"));

		return fuzzyDistance >= word1.length() * 2;
	}

	private static boolean matchByIndexes(String word1, String word2) {
		return word1.length() == word2.length() && calcEquality(word1, word2) >= 0.9f;
	}

	private static float calcEquality(String word1, String word2) {
		float equality = 0;

		for (int i = 0; i < word1.length(); i++) {
			equality += word1.charAt(i) == word2.charAt(i) ? 1 : 0;
		}

		return equality / word1.length();
	}

}
