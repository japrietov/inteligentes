package reactor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gui.Window;

public class CuttingSolution {
	private List<CuttingPiece> pieces;
	private int usedSteelSheets;
	private double wastedArea;
	private double employedArea;
	
	public CuttingSolution(List<CuttingPiece> pieces) {
		this.pieces = pieces;
	}
	
	public void calculateSolutionProperties() {
		double x = 0;
		double y = 0;
		employedArea = 0;
		usedSteelSheets = 0;
		wastedArea = 0;
		List<Double> lastWidths = new ArrayList<>();
		
		Window.getInstance().setBackground(Color.BLACK);
		addNewSteelSheet();
		
		for (CuttingPiece piece : pieces) {
			double nextY = y + piece.getHeight();
			double nextx = x + piece.getWidth();
			
			if (nextY > CuttingRequirements.STEEL_SHEET_HEIGHT) {
				x += Collections.max(lastWidths);
				nextx = x + piece.getWidth();
				
				if (nextx > (CuttingRequirements.STEEL_SHEET_WIDTH * usedSteelSheets)) {
					x = CuttingRequirements.STEEL_SHEET_WIDTH * usedSteelSheets;
					y = 0;
					addNewSteelSheet();
				}
				
				y = drawInNewColumn(x, piece);
				lastWidths.clear();
			} else {
				if (nextx > (CuttingRequirements.STEEL_SHEET_WIDTH * usedSteelSheets)) {
					x = CuttingRequirements.STEEL_SHEET_WIDTH * usedSteelSheets;
					y = 0;
					addNewSteelSheet();
				}
				
				Window.getInstance().drawRect(x, y, piece.getWidth(), piece.getHeight(),
						Color.YELLOW);
				y = nextY;
			}
			
			lastWidths.add(piece.getWidth());
			employedArea += piece.getSize();
		}
		
	}
	
	private double drawInNewColumn(double x, CuttingPiece piece) {
		double y;
		Window.getInstance().drawRect(x, 0, piece.getWidth(), piece.getHeight(), Color.YELLOW);
		y = piece.getHeight();
		return y;
	}
	
	private void calculateWastedArea() {
		wastedArea += CuttingRequirements.getSteelSheetSize() - employedArea;
		employedArea = 0;
	}
	
	private void addNewSteelSheet() {
		double x;
		
		x = CuttingRequirements.STEEL_SHEET_WIDTH * usedSteelSheets;
		Window.getInstance().drawRect(x, 0, CuttingRequirements.STEEL_SHEET_WIDTH,
				CuttingRequirements.STEEL_SHEET_HEIGHT, Color.RED);
		usedSteelSheets++;
		calculateWastedArea();
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
		return wastedArea;
	}
	
}
