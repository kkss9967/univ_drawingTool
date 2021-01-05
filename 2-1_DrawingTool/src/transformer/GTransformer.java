package transformer;

import java.awt.Graphics2D;

import Shape.GShape;

public abstract class GTransformer {
	
	private GShape gShape;
	
	public GTransformer() {
		this.setgShape(null);

	}
	public GShape getgShape() { return gShape; }
	public void setgShape(GShape gShape) { this.gShape = gShape; }

	abstract public void initTransformer(Graphics2D g2, int x, int y);
	abstract public void keepTransformer(Graphics2D g2, int x, int y);
	abstract public void finishTransformer(Graphics2D g2, int x, int y);
	abstract public void continueTransformer(Graphics2D g2, int x, int y);

}
