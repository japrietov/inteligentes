package reactor;

import java.util.List;

import gui.Window;

public class CuttingSolution {
	private List<CuttingPiece> pieces;
	private int usedSteelSheets;
	private double employedArea;
	private double x;
	private double y;
	private double maxLastWidth;

	public CuttingSolution(List<CuttingPiece> pieces) {
		this.pieces = pieces;
	}

	public void calculateSolutionFitness() {
		initializeProperties();

		for (CuttingPiece piece : pieces) {
			calculatePieceLocation(piece);
			employedArea += piece.getSize();
			Window.getInstance().drawCuttingPiece(piece);
		}
	}

	private void initializeProperties() {
		x = 0;
		y = 0;
		employedArea = 0;
		usedSteelSheets = 1;
		maxLastWidth = 0;
		Window.getInstance().clear();
		Window.getInstance().drawSteelSheet(new Point(x, y));
	}

	private void calculatePieceLocation(CuttingPiece piece) {
		double steelSheetHeight = CuttingRequirements.STEEL_SHEET_HEIGHT;
		double nextY = y + piece.getHeight();
		double nextX = x + piece.getWidth();
		piece.setLocation(x, y);

		if ((nextX <= xMaxCurrentSteelSheet()) && (nextY <= steelSheetHeight)) {
			y += piece.getHeight();
			setMaxLastWidth(x + piece.getWidth());
		}

		if (nextY > steelSheetHeight) {
			x = maxLastWidth;
			y = 0;
			maxLastWidth = 0;
			calculatePieceLocation(piece);
		}

		if (nextX > xMaxCurrentSteelSheet()) {
			x = xMaxCurrentSteelSheet();
			y = 0;
			usedSteelSheets++;
			Window.getInstance().drawSteelSheet(new Point(x, y));
			calculatePieceLocation(piece);
		}
	}

	private void setMaxLastWidth(double width) {
		if (maxLastWidth < width) {
			maxLastWidth = width;
		}
	}

	private double xMaxCurrentSteelSheet() {
		return CuttingRequirements.STEEL_SHEET_WIDTH * usedSteelSheets;
	}

	public List<CuttingPiece> getPieces() {
		return pieces;
	}

	public void setPieces(List<CuttingPiece> pieces) {
		this.pieces = pieces;
	}

	public int getRequiredSteelSheets() {
		if (usedSteelSheets == 0) {
			calculateSolutionFitness();
		}

		return usedSteelSheets;
	}

	public double getWastedArea() {
		if (employedArea == 0) {
			calculateSolutionFitness();
		}

		return (CuttingRequirements.getSteelSheetSize() * usedSteelSheets) - employedArea;
	}

}
