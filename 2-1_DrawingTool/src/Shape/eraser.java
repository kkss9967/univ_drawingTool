package Shape;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

public class eraser extends GShape {

	private Ellipse2D.Double ellipse;
	GShape s;
	public eraser() {
		super();
		this.shape = new Ellipse2D.Double();
		this.ellipse = (Double) this.shape;
	}

	@Override
	public void setOrigin(Graphics2D g2, int x, int y) {
		 px = x;
	     py = y;
	     this.ellipse.setFrame(Math.abs(x), Math.abs(y), 40, 40);	
	}

	@Override
	public void setPoint(int x, int y) {
//	      this.ellipse.setFrame(Math.min(x, basicx), Math.min(y, basicy), Math.abs(x-basicx), Math.abs(y-basicy));
		     this.ellipse.setFrame(Math.abs(x), Math.abs(y), 40, 40);
		     
	}

	@Override
	public void addPoint(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public GShape newInstance() {
		// TODO Auto-generated method stub
		return new eraser();
	}

	@Override
	public GShape selectedCheck(GShape gShape) {
		return null;
//		s = gShape;
//		for(int i = vStart.size()-1; i>=0; i--) {
//			if(this.ellipse.contains(vStart.elementAt(i))) {
//				return null;
//			}
//		}
//
//		return s;
	}

}
