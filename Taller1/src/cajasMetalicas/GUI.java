package cajasMetalicas;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PShape;

public class GUI extends PApplet {
	private static GUI instance;
	private static List<PShape> shapes;

	@Override
	public void settings() {
		size(640, 480, P2D);
	}

	@Override
	public void setup() {
		shapes = new ArrayList<>();
		instance = this;
	}

	@Override
	public void draw() {
		background(0);
		for (PShape shape : shapes) {
			shape(shape);
		}
	}

	public void addRectangle(double x, double y, double width, double height, Color color) {
		PShape rect = createShape(RECT, (int) x, (int) y, (int) width, (int) height);
		rect.setFill(color.getRGB());
		shapes.add(rect);
	}

	public static GUI getInstance() {
		return instance;
	}

}
