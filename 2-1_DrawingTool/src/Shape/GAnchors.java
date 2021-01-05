package Shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;
import java.util.Vector;

public class GAnchors implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private final int w = 12;
	private final int h = 12;
	private final int dw = w/2;
	private final int dh = h/2;
	
	public enum EAnchors{
		NW, NN, NE, EE, SE, SS, SW, WW, RR;
	}
	
	private Vector<Integer> xAnchor = new Vector<Integer>();
	private Vector<Integer> yAnchor = new Vector<Integer>();
	
	public Vector<Integer> getxAnchor() {return xAnchor;}
	public void setxAnchor(Vector<Integer> xAnchor) {this.xAnchor = xAnchor;}
	public Vector<Integer> getyAnchor() {return yAnchor;}
	public void setyAnchor(Vector<Integer> yAnchor) {this.yAnchor = yAnchor;}
	
	private Vector<Ellipse2D> anchors;
	
	@SuppressWarnings("unused")
	public GAnchors() {
		this.anchors = new Vector<Ellipse2D>();
		this.xAnchor = new Vector<Integer>();
		this.yAnchor = new Vector<Integer>();
		for(EAnchors eAnchor : EAnchors.values()) {
			this.anchors.add(new Ellipse2D.Double(0,0,w,h));
		}
	}
	
	public EAnchors onShape(int x, int y) {
		for(int i = 0; i<EAnchors.values().length; i++) {
			if(this.anchors.get(i).contains(x, y)) {
				return EAnchors.values()[i];
				//앵커가 어느 앵커인지 알아야 나중에 계산 가능. 앵커를 저장해야.
			}
		}
		return null;	//null 까지 총 10개의 상태
	}
	
	public void draw(Graphics2D graphics2D) {
		for(Shape shape: this.anchors) {
			graphics2D.setStroke(new BasicStroke(1,BasicStroke.CAP_ROUND,0));
			graphics2D.setColor(Color.white);
			graphics2D.fill(shape);
			graphics2D.setColor(Color.black);
			graphics2D.draw(shape);	
		}
	}
	
	public void setBoundingRect(Rectangle r) {
		for(EAnchors eAnchor : EAnchors.values()) {
			int x = 0, y = 0;
			switch (eAnchor) {
			case NW:
				x = r.x;
				y = r.y;
				break;
			case NN:
				x = r.x + r.width/2;
				y = r.y;
				break;
			case NE:
				x = r.x + r.width;
				y = r.y;
				break;
			case EE:
				x = r.x + r.width;
				y = r.y + r.height/2;
				break;
			case SE:
				x = r.x + r.width;
				y = r.y + r.height;
				break;
			case SS:
				x = r.x + r.width/2;
				y = r.y + r.height;
				break;
			case SW:
				x = r.x;
				y = r.y + r.height;
				break;
			case WW:
				x = r.x;
				y = r.y + r.height/2;
				break;
			case RR:
				x = r.x + r.width/2;
				y = r.y - 50;
				break;
			}
			this.xAnchor.add(eAnchor.ordinal(), x);
			this.yAnchor.add(eAnchor.ordinal(), y);
			x = x - dw;
			y = y - dh;
			this.anchors.get(eAnchor.ordinal()).setFrame(x, y, w, h); //몇번째냐?
		}
	}
	
}
