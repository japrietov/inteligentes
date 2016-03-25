package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Window extends JFrame {

	private static Window instance;

	public Window() {
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}

	public void paint(Graphics g) {
	}

	public void drawRect(double x, double y, double width, double height, Color backgroundColor) {
		Graphics g = getGraphics();
		int offset = 50;

		g.setColor(Color.BLACK);
		g.drawRect((int) x + offset, (int) y + offset, (int) width, (int) height);
		g.setColor(backgroundColor);
		g.fillRect((int) x + offset + 1, (int) y + offset + 1, (int) width - 1, (int) height - 1);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
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
