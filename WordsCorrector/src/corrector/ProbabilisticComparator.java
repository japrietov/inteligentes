package corrector;

import java.text.MessageFormat;
import java.util.Comparator;

public class ProbabilisticComparator implements Comparator<String> {

	public int compare(String o1, String o2) {
		int equal = -1;

		if (o1.length() == o2.length()) {
			float equality = calcEquality(o1, o2);

			if (equality > 0.9) {
				System.out.println(MessageFormat.format("{0} {1} {2}", o1, o2, equality));
			}
		}

		return o1.compareTo(o2);
	}

	private float calcEquality(String o1, String o2) {
		float equality = 0;

		for (int i = 0; i < o1.length(); i++) {
			equality += o1.charAt(i) == o2.charAt(i) ? 1 : 0;
		}

		return equality / o1.length();
	}

}
