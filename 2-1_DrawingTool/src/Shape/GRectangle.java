package Shape;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Vector;

public class GRectangle extends GShape {
	private static final long serialVersionUID = 1L;

	// private java.awt.Rectangle rectangle;
	private Rectangle rectangle;

	public GRectangle() {
		super();
		this.shape = new java.awt.Rectangle();
		this.rectangle = (java.awt.Rectangle) this.shape;
		// this.shape = this.rectangle;
	}

	@Override
	public GShape newInstance() {
		return new GRectangle();
	}

	public void setOrigin(Graphics2D g2, int x, int y) {
		px = x;
		py = y;
		this.rectangle.setBounds(Math.abs(x), Math.abs(y), 0, 0);
	}

	public void setPoint(int x, int y) {
		this.rectangle.setFrame(Math.min(x, px), Math.min(y, py), Math.abs(x - px), Math.abs(y - py));

	}

	public void addPoint(int x, int y) {

	}

	@Override
	public GShape selectedCheck(GShape gShape) {
		return null;
	}

}
