package corrector;

import java.util.Arrays;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		if (args.length == 1) {
			WordsCorrector.fixWords(args[0]);
		} else {
			showMessage("Debe pasar como parámetro el nombre del archivo de entrada",
					"ejemplo: java -jar corrector.jar filename.txt");
		}
	}

	public static void showMessage(String... messages) {
		String message = Arrays.toString(messages).replace("[", "").replace("]", "").replace(", ",
				System.lineSeparator());

		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
		System.err.println(message);
	}

}
