package cajasMetalicas;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.SwingUtilities;

import gui.Window;

public class BoxSolution {
	private List<Piece> pieces;
	private int usedSteelSheets;
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
		List<Double> lastWidths = new ArrayList<>();

		addNewSteelSheet();

		for (Piece piece : pieces) {
			double nextY = y + piece.getHeight();
			double nextx = x + piece.getWidth();

			if (nextY > BoxRequirements.STEEL_SHEET_HEIGHT) {
				x += Collections.max(lastWidths);
				nextx = x + piece.getWidth();

				if (nextx > BoxRequirements.STEEL_SHEET_WIDTH * usedSteelSheets) {
					x = BoxRequirements.STEEL_SHEET_WIDTH * usedSteelSheets;
					y = 0;
					addNewSteelSheet();
				}

				y = drawInNewColumn(x, piece);
				lastWidths.clear();
			} else {
				if (nextx > BoxRequirements.STEEL_SHEET_WIDTH * usedSteelSheets) {
					x = BoxRequirements.STEEL_SHEET_WIDTH * usedSteelSheets;
					y = 0;
					addNewSteelSheet();
				}

				Window.getInstance().drawRect(x, y, piece.getWidth(), piece.getHeight(), Color.YELLOW);
				y = nextY;
			}

			lastWidths.add(piece.getWidth());
			employedArea += piece.getSize();
		}

	}

	private double drawInNewColumn(double x, Piece piece) {
		double y;
		Window.getInstance().drawRect(x, 0, piece.getWidth(), piece.getHeight(), Color.YELLOW);
		y = piece.getHeight();
		return y;
	}

	private void calculateWastedArea() {
		wastedArea += BoxRequirements.getSteelSheetSize() - employedArea;
		employedArea = 0;
	}

	private void addNewSteelSheet() {
		double x;

		x = BoxRequirements.STEEL_SHEET_WIDTH * usedSteelSheets;
		Window.getInstance().drawRect(x, 0, BoxRequirements.STEEL_SHEET_WIDTH, BoxRequirements.STEEL_SHEET_HEIGHT,
				Color.RED);
		usedSteelSheets++;
		
		calculateWastedArea();
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
