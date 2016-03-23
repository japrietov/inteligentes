package cajasMetalicas;

import java.util.List;

public class SteelSheet {
	private double height;
	private double width;
	private double availableHeight;
	private double availableWidth;
	private List<Piece> pieces;

	public SteelSheet(double height, double width) {
		this.height = height;
		this.width = width;
	}

	public double calculateWastedArea() {
		double wastedArea = height * width;

		for (Piece piece : pieces) {
			wastedArea -= (piece.getHeight() * piece.getWidth());
		}

		return wastedArea;
	}

	public void addPiece(Piece piece) {
		availableWidth -= piece.getWidth();
		availableHeight -= piece.getHeight();

		if ((availableWidth <= 0) || (availableHeight <= 0)) {
		}
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public List<Piece> getPieces() {
		return pieces;
	}

	public double getAvailableHeight() {
		return availableHeight;
	}

	public double getAvailableWidth() {
		return availableWidth;
	}

}
