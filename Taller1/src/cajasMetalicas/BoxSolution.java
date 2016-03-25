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
		List<Double> lastWidths = new ArrayList<>();

		addNewSteelSheet();
		// Window.getInstance().drawRect(0, 0, 20, 30, Color.YELLOW);
		// Window.getInstance().drawRect(20, 30, 20, 40, Color.YELLOW);

		for (Piece piece : pieces) {
			double nextY = y + piece.getHeight();

			if (nextY > BoxRequirements.STEEL_SHEET_HEIGHT) {
				x += Collections.max(lastWidths);
				System.out.println(x);
				y = drawInNewColumn(x, piece);
				lastWidths.clear();
			} else {
				Window.getInstance().drawRect(x, y, piece.getWidth(), piece.getHeight(), Color.YELLOW);
				y = nextY;
			}

			//x += piece.getWidth();
			lastWidths.add(piece.getWidth());
			// y += piece.getHeight();
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
		usedSteelSheets++;
		Window.getInstance().drawRect(0, 0, BoxRequirements.STEEL_SHEET_WIDTH * usedSteelSheets,
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
