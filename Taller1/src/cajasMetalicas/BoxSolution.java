package cajasMetalicas;

import java.awt.Color;
import java.util.List;

public class BoxSolution {
	private List<Piece> pieces;
	private int usedSteelSheets;
	private double availableHeight;
	private double availableWidth;
	private double wastedArea;
	private double employedArea;

	public BoxSolution(List<Piece> pieces) {
		this.pieces = pieces;
	}

	public void calculateSolutionProperties() {
		double x = 0;
		double y = 0;
		employedArea = 0;
		usedSteelSheets = 0;
		wastedArea = 0;

		addNewSteelSheet();

		for (Piece piece : pieces) {
			GUI.getInstance().addRectangle(x, y, piece.getWidth(), piece.getHeight(), Color.YELLOW);

			x = piece.getWidth();
			y = piece.getHeight();
		}
	}

	private void calculateWastedArea() {
		wastedArea += BoxRequirements.getSteelSheetSize() - employedArea;
		employedArea = 0;
	}

	private void addNewSteelSheet() {
		usedSteelSheets++;
		GUI.getInstance().addRectangle(0, 0, BoxRequirements.STEEL_SHEET_WIDTH * usedSteelSheets,
				BoxRequirements.STEEL_SHEET_HEIGHT, Color.RED);
	}

	public List<Piece> getPieces() {
		return pieces;
	}

	public void setPieces(List<Piece> pieces) {
		this.pieces = pieces;
	}

	public int getRequiredSteelSheets() {
		return usedSteelSheets;
	}

	public double getWastedArea() {
		return wastedArea;
	}

}
