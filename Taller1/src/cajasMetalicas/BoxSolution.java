package cajasMetalicas;

import java.util.List;

public class BoxSolution {
	private List<Piece> pieces;
	private int requiredSteelSheets;
	private double availableHeight;
	private double availableWidth;
	private double wastedArea;
	private double employedArea;

	public BoxSolution(List<Piece> pieces) {
		this.pieces = pieces;
	}

	public void calculateSolutionProperties() {
		employedArea = 0;
		requiredSteelSheets = 0;
		wastedArea = 0;

		for (Piece piece : pieces) {
			double remainingWidth = availableWidth - piece.getWidth();
			double remainingHeight = availableHeight - piece.getHeight();

			if ((remainingHeight < 0) || (remainingWidth < 0)) {
				calculateWastedArea();
				addPieceToNewSteelSheet(piece.getWidth(), piece.getHeight());
			} else {
				employedArea += piece.getSize();
				availableWidth = remainingWidth;
				availableHeight = remainingHeight;
			}
		}
	}

	private void calculateWastedArea() {
		wastedArea += BoxRequirements.getSteelSheetSize() - employedArea;
		employedArea = 0;
	}

	private void addPieceToNewSteelSheet(double width, double height) {
		requiredSteelSheets++;

		availableWidth = BoxRequirements.STEEL_SHEET_WIDTH - width;
		availableHeight = BoxRequirements.STEEL_SHEET_HEIGHT - height;
	}

	public List<Piece> getPieces() {
		return pieces;
	}

	public void setPieces(List<Piece> pieces) {
		this.pieces = pieces;
	}

	public int getRequiredSteelSheets() {
		return requiredSteelSheets;
	}

	public double getWastedArea() {
		return wastedArea;
	}

}
