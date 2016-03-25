package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JFrame;

public class Intersection extends JFrame {
	Rectangle r = new Rectangle(50, 50, 100, 100);
	Rectangle r1 = new Rectangle(100, 100, 75, 75);

	Intersection() {
		super("Intersection");
		setSize(250, 250);
		setVisible(true);
	}

	public void paint(Graphics g) {
		Rectangle r2 = r.intersection(r1);
		g.setColor(Color.RED);
		g.drawRect(r.x, r.y, r.width, r.height);
		g.drawRect(r1.x, r1.y, r1.width, r1.height);
		g.fillRect(r2.x, r2.y, r2.width, r2.height);
	}

	public static void main(String[] args) throws InterruptedException {
		JFrame f = new Intersection();
		Graphics g = f.getGraphics();

		while (true) {
			g.setColor(Color.CYAN);
			g.drawRect((int) (Math.random() * 100), 100, 100 + 1, 100 + 1);
			Thread.sleep(1000);
		}
		// g.setColor(backgroundColor);
		// g.fillRect(x + 1, y + 1, width, height);
		// f.paint(g);
	}
}