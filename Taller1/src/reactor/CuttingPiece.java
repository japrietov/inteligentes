package reactor;

public class CuttingPiece {
	private double height;
	private double width;
	
	public CuttingPiece(double height, double width) {
		this.height = height;
		this.width = width;
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
	
	public double getSize() {
		return width * height;
	}
	
	@Override
	public String toString() {
		return String.format("[height=%s, width=%s]", height, width);
	}
	
}
