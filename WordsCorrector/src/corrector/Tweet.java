package corrector;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class Tweet {
	private List<Word> words;

	public Tweet() {
		words = new ArrayList<Word>();
	}

	public List<Word> getWords() {
		return words;
	}

	public void addWord(Word word) {
		this.words.add(word);
	}

	@Override
	public String toString() {
		return StringUtils.join(words, " ");
	}

}
