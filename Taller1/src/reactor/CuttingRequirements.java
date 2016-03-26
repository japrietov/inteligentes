package reactor;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class CuttingRequirements {
	public static final double STEEL_SHEET_HEIGHT = 100;
	public static final double STEEL_SHEET_WIDTH = 100;
	public static final List<CuttingPiece> REQUIRED_PIECES = Arrays.asList(new CuttingPiece(80, 30),
			new CuttingPiece(50, 10), new CuttingPiece(20, 20), new CuttingPiece(10, 10),
			new CuttingPiece(90, 40), new CuttingPiece(60, 20), new CuttingPiece(40, 30),
			new CuttingPiece(50, 70), new CuttingPiece(30, 20), new CuttingPiece(40, 20));

	private CuttingRequirements() {
	}

	public static double getSteelSheetSize() {
		return STEEL_SHEET_HEIGHT * STEEL_SHEET_WIDTH;
	}

	public static double calculateHeuristics() {
		double employedArea = 0;
		double totalArea = 0;
		double wastedArea;
		double neededSteelSheets;

		for (CuttingPiece piece : REQUIRED_PIECES) {
			employedArea += piece.getHeight() * piece.getWidth();
		}

		while (totalArea < employedArea) {
			totalArea += getSteelSheetSize();
		}

		wastedArea = totalArea - employedArea;
		neededSteelSheets = totalArea / getSteelSheetSize();

		System.out.println(String.format(Locale.ENGLISH,
				"-----------------Heuristics-----------------\n" + "Needed total area: %s\n"
						+ "Wasted total area: %s\n" + "Needed steel sheets: %s\n"
						+ "--------------------------------------------\n",
				employedArea, wastedArea, neededSteelSheets));

		return wastedArea;
	}

}
