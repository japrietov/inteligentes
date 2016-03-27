package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

import reactor.CuttingPiece;
import reactor.CuttingRequirements;
import reactor.Point;

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;
	private static Window instance;

	public Window() {
		setSize(800, 800);
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}

	@Override
	public void paint(Graphics g) {
	}

	public void drawRect(double x, double y, double width, double height, Color backgroundColor) {
		Graphics g = getGraphics();
		int offset = 50;

		g.setColor(Color.BLACK);
		g.drawRect((int) x + offset, (int) y + offset, (int) width, (int) height);
		g.setColor(backgroundColor);
		g.fillRect((int) x + offset + 1, (int) y + offset + 1, (int) width - 1, (int) height - 1);

		sleep();
	}

	public void drawCuttingPiece(CuttingPiece piece) {
		Point point = piece.getLocation().clone();

		point.translate(0, 200);
		drawRect(point.getX(), point.getY(), piece.getWidth(), piece.getHeight(), Color.YELLOW);
	}

	public void drawSteelSheet(Point point) {
		Point p = point.clone();

		p.translate(0, 200);
		drawRect(p.getX(), p.getY(), CuttingRequirements.STEEL_SHEET_WIDTH, CuttingRequirements.STEEL_SHEET_HEIGHT,
				Color.RED);
	}

	public void clear() {
		getGraphics().setColor(Color.BLACK);
		getGraphics().fillRect(0, 0, getWidth(), getHeight());
	}

	private void sleep() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static Window getInstance() {
		if (instance == null) {
			instance = new Window();
		}

		return instance;
	}

}
