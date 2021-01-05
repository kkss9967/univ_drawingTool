package Shape;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.util.Vector;

public class GDrawPen extends GShape {
	private static final long serialVersionUID = 1L;

//	   private Ellipse2D.Double ellipse;
	Line2D.Double line;

//	protected Vector<Point> vStart = new Vector<Point>();
//	protected Vector<Line2D> lines;


//	Graphics2D g2;

	public GDrawPen() {
		super();
//		 this.shape = new Ellipse2D.Double();
//	      this.ellipse = (Double) this.shape;
	      this.shape = new Line2D.Double();
	      this.line = (Double) this.shape;
			vStart = new Vector<Point>();
	}

	Point p1;

	@Override
	public void setOrigin(Graphics2D g2, int x, int y) {
//		this.g2 = g2;
		p1 = new Point(x, y);
		vStart.add(p1);
	}

	@Override
	public void setPoint(int x, int y) {		
		Point p2 = new Point(x, y);
		vStart.add(p2);
	
//		drawLine();
	}
//	public void drawLine() {
//		super.drawLine(g2);
//		
//		
//	}
	@Override
	public void addPoint(int x, int y) {

	}

	@Override
	public GShape newInstance() {
		return new GDrawPen();
	}

	@Override
	public GShape selectedCheck(GShape gShape) {
		// TODO Auto-generated method stub
		return null;
	}

}
