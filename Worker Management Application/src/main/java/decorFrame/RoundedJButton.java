package decorFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

public class RoundedJButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Shape shape;

	public RoundedJButton(String label) {
		super(label);
        
		setContentAreaFilled(false);
		setFont(new Font("serif", Font.BOLD, 13));
	}

	protected void paintComponent(Graphics g) {
		if (getModel().isArmed()) {
			g.setColor(Color.PINK);
		} else {
			g.setColor(getBackground());
		}
		g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
		super.paintComponent(g);
	}

	protected void paintBorder(Graphics g) {
		g.setColor(getBackground());
		g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
	}

	public boolean contains(int x, int y) {
		if (shape == null || !shape.getBounds().equals(getBounds())) {
			shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20);
		}
		return shape.contains(x, y);
	}
}
