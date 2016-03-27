package reactor;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gui.Window;

public class CuttingSolution {
	private List<CuttingPiece> pieces;
	private int usedSteelSheets;
	private double employedArea;
	private double x;
	private double y;

	public CuttingSolution(List<CuttingPiece> pieces) {
		this.pieces = pieces;
	}

	private void calculateSolutionProperties() {
		boolean drawSteelSheet = true;
		double steelSheetWidth = CuttingRequirements.STEEL_SHEET_WIDTH;
		double steelSheetHeight = CuttingRequirements.STEEL_SHEET_HEIGHT;
		List<Double> lastColumnWidths = new ArrayList<>();
		x = 0;
		y = 0;
		employedArea = 0;
		usedSteelSheets = 1;

		Window.getInstance().clear();

		for (CuttingPiece piece : pieces) {
			Point drawingPoint = new Point((int) x, (int) y);
			double nextY = y + piece.getHeight();
			double nextX = x + piece.getWidth();

			lastColumnWidths.add(piece.getWidth());

			if ((nextX <= nextSteelSheetOrigin()) && (nextY <= steelSheetHeight)) {
				y += piece.getHeight();
			} else if ((nextX <= nextSteelSheetOrigin()) && (nextY > steelSheetHeight)) {
				double lastColumnWidth = Collections.max(lastColumnWidths);

				nextX = lastColumnWidth + piece.getWidth();
				y = 0;

				if (nextX > nextSteelSheetOrigin()) {
					x = nextSteelSheetOrigin();
					drawSteelSheet = true;
					usedSteelSheets++;
				} else {
					x += lastColumnWidth;
				}

				drawingPoint.setLocation(x, y);
				y += piece.getHeight();
				lastColumnWidths.clear();
			} else {
				x = nextSteelSheetOrigin();
				y = 0;
				drawingPoint.setLocation(x, y);
				y = piece.getHeight();
				drawSteelSheet = true;
				usedSteelSheets++;
				lastColumnWidths.clear();
			}

			employedArea += piece.getSize();

			if (drawSteelSheet) {
				Window.getInstance().drawSteelSheet(drawingPoint);
				drawSteelSheet = false;
			}

			Window.getInstance().drawCuttingPiece(drawingPoint, piece);
		}

	}

	private double nextSteelSheetOrigin() {
		return CuttingRequirements.STEEL_SHEET_WIDTH * usedSteelSheets;
	}

	public List<CuttingPiece> getPieces() {
		return pieces;
	}

	public void setPieces(List<CuttingPiece> pieces) {
		this.pieces = pieces;
	}

	public int getRequiredSteelSheets() {
		return usedSteelSheets;
	}

	public double getWastedArea() {
		calculateSolutionProperties();

		return (CuttingRequirements.getSteelSheetSize() * usedSteelSheets) - employedArea;
	}

}
