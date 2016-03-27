package reactor;

public class CuttingPiece {
	private double height;
	private double width;
	private Point location;

	public CuttingPiece(double height, double width) {
		this.height = height;
		this.width = width;
		location = new Point();
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

	public void setLocation(double x, double y) {
		location.setX(x);
		location.setY(y);
	}

	public Point getLocation() {
		return location;
	}

	@Override
	public String toString() {
		return String.format("[height=%s, width=%s]", height, width);
	}

}
