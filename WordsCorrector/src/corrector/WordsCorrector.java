package corrector;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;

public class WordsCorrector {

	private static List<Tweet> readTweetsToFix(String fileName) {
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		String[] lines = loadWordsFromFile(fileName).split(System.lineSeparator());

		for (String tweet : lines) {
			String[] tweetWords = tweet.split(",");
			Tweet tweetObject = new Tweet();

			for (String word : tweetWords) {
				String[] splittedToken = word.trim().split(" ");

				if (splittedToken.length == 2) {
					tweetObject.addWord(new Word(splittedToken[0], splittedToken[1]));
				} else {
					Main.showMessage("Token mal formado", Arrays.toString(splittedToken));
				}
			}

			tweets.add(tweetObject);
		}

		return tweets;
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

	public static void fixTweets(String fileName) {
		File file = new File("fixedTweets.txt");
		List<Tweet> tweets = readTweetsToFix(fileName);

		writeFile(file, "", false);

		for (int i = 0; i < tweets.size(); i++) {
			Tweet tweet = tweets.get(i);

			for (Word word : tweet.getWords()) {
				boolean isNumber = word.getText().replaceFirst("\\d*\\s*", "").equals("");

				if (word.checkWord() && !word.getText().startsWith("@") && !word.getText().startsWith("#")
						&& !isNumber) {
					String fixedWord = Dictionary.getInstance().checkWord(word.getText());

					word.setText(fixedWord);
				}

				System.err.println((float) i * 100 / tweets.size() + "%");
			}

			writeFile(file, tweet.toString().concat(System.lineSeparator()), true);
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
