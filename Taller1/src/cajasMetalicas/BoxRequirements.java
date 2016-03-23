package cajasMetalicas;

import java.util.ArrayList;
import java.util.List;

public class BoxRequirements {
	public static final double STEEL_SHEET_HEIGHT = 100;
	public static final double STEEL_SHEET_WIDTH = 100;

	private static BoxRequirements instance;
	private List<Piece> requiredPieces;

	public BoxRequirements() {
		requiredPieces = new ArrayList<>();
		requiredPieces.add(new Piece(80, 30));
		requiredPieces.add(new Piece(50, 10));
		requiredPieces.add(new Piece(20, 20));
		requiredPieces.add(new Piece(10, 10));
		requiredPieces.add(new Piece(90, 40));
		requiredPieces.add(new Piece(60, 20));
		requiredPieces.add(new Piece(40, 30));
		requiredPieces.add(new Piece(50, 70));
		requiredPieces.add(new Piece(30, 20));
		requiredPieces.add(new Piece(40, 20));
	}

	public static double getSteelSheetSize() {
		return STEEL_SHEET_HEIGHT * STEEL_SHEET_WIDTH;
	}

	public static BoxRequirements getInstance() {
		if (instance == null) {
			instance = new BoxRequirements();
		}

		return instance;
	}

	public List<Piece> getRequiredPieces() {
		return requiredPieces;
	}

	public double calculateIdealWastedArea() {
		double employedArea = 0;
		double totalArea = 0;
		double wastedArea;
		double neededSteelSheets;

		for (Piece piece : requiredPieces) {
			employedArea += piece.getHeight() * piece.getWidth();
		}

		while (totalArea < employedArea) {
			totalArea += getSteelSheetSize();
		}

		wastedArea = totalArea - employedArea;
		neededSteelSheets = totalArea / getSteelSheetSize();

		System.out.println(String.format(
				"-----------------Heuristics-----------------\n" + "Needed total area: %f\n"
						+ "Wasted total area: %f\n" + "Needed steel sheets: %f\n"
						+ "--------------------------------------------\n",
				employedArea, wastedArea, neededSteelSheets));

		return wastedArea;
	}

	@Override
	public String toString() {
		return String.format("BoxRequirements [requiredPieces=%s]", requiredPieces);
	}

}
