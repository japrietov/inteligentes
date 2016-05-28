package corrector;

public class Word {
	private String text;
	private boolean checkWord;

	public Word(String text, String checkWord) {
		this.text = text;
		this.checkWord = checkWord.trim().equals("0");
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean checkWord() {
		return checkWord;
	}

	public void setCheckWord(boolean checkWord) {
		this.checkWord = checkWord;
	}

	@Override
	public String toString() {
		return String.format("%s", text);
	}

}
