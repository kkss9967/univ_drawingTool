package Shape;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Select extends GShape {
	private static final long serialVersionUID = 1L;

	private Rectangle rectangle;
//	private int rw, rh;
	
	public Select() {
		super();
		this.shape = new java.awt.Rectangle();
		this.rectangle = (java.awt.Rectangle) this.shape;	
		}
	
	@Override
	public GShape newInstance() {
		// TODO Auto-generated method stub
		return new Select();
	}
	
	@Override
	public void setOrigin(Graphics2D g2, int x, int y) {
		px = x;
		py = y;
		this.rectangle.setBounds(Math.abs(x), Math.abs(y), 0, 0);	
		
	}

	@Override
	public void setPoint(int x, int y) {
//		rw = x-this.rectangle.x;
//		rh = y-this.rectangle.y;
		this.rectangle.setFrame(Math.min(x, px), Math.min(y, py), Math.abs(x-px), Math.abs(y-py));
		
	}
	
	public GShape selectedCheck(GShape gShape) {
			if (this.rectangle.contains(gShape.shape.getBounds())) {
				return gShape;
			}
			return null;
	}

	@Override
	public void addPoint(int x, int y) {	}
//	@Override
//	public void setCenter() {}


	

}
