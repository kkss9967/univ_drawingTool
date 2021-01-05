package Shape;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

import Shape.GAnchors.EAnchors;

public abstract class GShape implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;

	public enum EOnState {eOnShape, eOnResize, eOnRotate};
	
	protected int px, py;	//처음 점
	protected double dw, dh;	//높이 너비
	protected int mX, mY;	//무브 점
	protected int cX, cY;	//센터
	protected Point centerP;
	private Point2D p1;
	Point2D p = new Point2D.Double();
	Point2D xy = new Point2D.Double();
	
	protected EAnchors eAnchors;
	
	private boolean resize;
	private boolean selected;

	protected Shape shape;
	protected Shape currentShape;

	private GAnchors anchors;
	protected AffineTransform affineTransform;
	
	protected Color lineColor, fillColor;
	protected float dashes;
	
	double preX, preY;
	double nowX, nowY;
	
	double dW, dH;	//델타
	double w, h;
	double x1, y1;
	
	double theta;
	
	public void setxy(Point2D xy) {this.xy = xy;}
	public Point2D getxy() {return xy;}
	
	public boolean isSelected() {return selected;}
	public void setSelected(boolean selected) {
		this.selected = selected;

	}

	public GShape() {
		this.resize = false;
		this.selected = false;
		this.dashes = 1;
		this.anchors = new GAnchors();
		this.affineTransform = new AffineTransform();
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
	public void setStroke(boolean stroke) {
		if(stroke) {
		this.dashes++;
		}else {
			if(dashes > 0) {
			this.dashes--;
			}
		}
	}

	abstract public void setOrigin(Graphics2D g2, int x, int y);
	abstract public void setPoint(int x, int y);
	abstract public void addPoint(int x, int y);

	 public GShape clone() {// 딥 카피
		   try {
			   ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			   ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream); //파일 대신 바이트
			   	objectOutputStream.writeObject(this);
			   	
			   	ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
			   	ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
			   	return (GShape)objectInputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			   return null;
		}
	   }
	 
	abstract public GShape newInstance();

	public void draw(Graphics2D graphics) {
		if (!(this.shape instanceof Line2D.Double)) {
			if (dashes != 0) {
				graphics.setStroke(new BasicStroke(dashes, BasicStroke.CAP_ROUND, 0));
			}
			if (this.fillColor != null) {
				graphics.setColor(this.fillColor);
				graphics.fill(this.shape);
				good(graphics);
			}
			if (this.lineColor != null) {
				graphics.setColor(this.lineColor);
				graphics.draw(this.shape);
				good(graphics);
			}
		}
		drawLine(graphics);
		good(graphics);

	}

	public void good(Graphics2D graphics2d) {
		graphics2d.setComposite(AlphaComposite.Src);
        graphics2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}
	protected Vector<Point> vStart;
	
	public void drawLine(Graphics2D g2) {
		g2.setStroke(new BasicStroke(dashes,BasicStroke.CAP_ROUND,0));	
		g2.setColor(this.lineColor);
		if(vStart != null) {
		for (int i = 1; i < vStart.size(); i++) {
			if (vStart.get(i - 1) == null)
				continue;
			else if (vStart.get(i) == null)
				continue;
			else
		g2.drawLine((int) vStart.get(i - 1).getX(), (int) vStart.get(i - 1).getY(),
				(int) vStart.get(i).getX(), (int) vStart.get(i).getY());
			
		}
		}else {
			g2.draw(this.shape);
		}
	}
	
	public EAnchors getAnchor() {return this.eAnchors;}
	public void setAnchor(EAnchors eAnchors) {this.eAnchors = eAnchors;}

	public void drawAnchor(Graphics2D graphics2d) {
		if (this.selected) {
			this.anchors.setBoundingRect(this.shape.getBounds());
			this.anchors.draw(graphics2d);
		}
	}
	
	public void select(Graphics2D g2) {
		float dashes[] = {4};
		g2.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, dashes, 0));
		g2.draw(this.shape);
		
	}
	
	abstract public GShape selectedCheck(GShape gShape);
		
	public EOnState onShape(int x, int y) {
		if (this.selected) {
			eAnchors = this.anchors.onShape(x, y);
			if (eAnchors == EAnchors.RR) {
				return EOnState.eOnRotate;
			} else if (eAnchors == null) {
				if (this.shape.contains(x, y)) {
					return EOnState.eOnShape;
				}
			} else {
				return EOnState.eOnResize;
			}
		} else {
			if (this.shape.contains(x, y)) {
				this.selected = true;
				return EOnState.eOnShape;
			}
		}
		return null;
	}

	public void setBasic() {
		this.px = this.shape.getBounds().x;
		this.py = this.shape.getBounds().y;
	}
	
	public void initMoving(Graphics2D graphics, int x, int y) {
		this.mX = x;
		this.mY = y;
	}

	public void keepMoving(Graphics2D graphics, int x, int y) {	
		this.currentShape = this.shape;
		this.affineTransform.setToTranslation(x-mX, y-mY);

		this.px = px + (x-mX);
		this.py = py + (y-mY);
		
		this.mX = x;
		this.mY = y;

		this.shape = this.affineTransform.createTransformedShape(this.currentShape);
	}
	
	public void initResizing(Graphics2D graphics, int x, int y) {
		this.mX = x;
		this.mY = y;
		this.preX = x;
		this.preY = y;
		this.nowX = x;
		this.nowY = y;
	}
	
	public void setOrigin() {
		switch(eAnchors) {
		case SE:
			p.setLocation(
					anchors.getxAnchor().elementAt(0),
					anchors.getyAnchor().elementAt(0));
			break;	
		case SS:
			p.setLocation(
				anchors.getxAnchor().elementAt(1),
				anchors.getyAnchor().elementAt(1));
			break;	
		case SW: 
			p.setLocation(
				anchors.getxAnchor().elementAt(2),
				anchors.getyAnchor().elementAt(2));
			break;	
		case WW: 
			p.setLocation(
					anchors.getxAnchor().elementAt(3),
					anchors.getyAnchor().elementAt(3));
			break;	
		case NW:
			p.setLocation(
					anchors.getxAnchor().elementAt(4),
					anchors.getyAnchor().elementAt(4));
			break;	
		case NN: 
			p.setLocation(
					anchors.getxAnchor().elementAt(5),
					anchors.getyAnchor().elementAt(5));
			break;	
		case NE: 
			p.setLocation(
					anchors.getxAnchor().elementAt(6),
					anchors.getyAnchor().elementAt(6));
			break;	
		case EE: 
			p.setLocation(
					anchors.getxAnchor().elementAt(7),
					anchors.getyAnchor().elementAt(7));
			break;	
		default: break;	
		}
		}
	private void setBefore(int index) {
		if (index == 2) {preX = preX + w; preY = preY + h;} 
		else if (index == 1) {preX = preX + w;}
		else if (index == 0) {preY = preY + h;}
	}
	
	public void setResizing(Graphics2D graphics, int x, int y) {
		dW = 0;
		dH = 0;
		
		if(!resize) {
			preX = this.shape.getBounds().x;
			preY = this.shape.getBounds().y;
		}
		
		this.nowX = x;
		this.nowY = y;
		
		 dW =-(nowX-preX);	dH=-(nowY-preY);
			
			w = this.shape.getBounds().getWidth();
			h = this.shape.getBounds().getHeight();
			
			switch(eAnchors) {
			case SE: this.setBefore(2);
				dW =  nowX-preX; dH=  nowY-preY; break;
			case SS: this.setBefore(2);
				dW =  0; dH=  nowY-preY; break;
			case SW: this.setBefore(0);
				dW =-(nowX-preX); dH=  nowY-preY; break;
			case WW: 
				dW =-(nowX-preX); dH=  0;
				this.cY = anchors.getyAnchor().elementAt(3); break;
			case NW: 
				dW =-(nowX-preX); dH=-(nowY-preY); break;
			case NN: 
				dW =  0; dH=-(nowY-preY); 
				this.cX = anchors.getxAnchor().elementAt(5); break;
			case NE: this.setBefore(1);
				dW =  nowX-preX; dH=-(nowY-preY); break;
			case EE: this.setBefore(2);
				dW = nowX-preX;	dH=  0; break;
			default: break;	
			}
			
			x1 = 1.0;
			y1 = 1.0;
			if(w > 0.0) {x1 = dW / w + x1;} 
			if(h > 0.0) {y1 = dH / h + y1;}
			
			xy.setLocation(x1, y1);
	}
	
	public void keepResizing(Graphics2D graphics, int x, int y) {
		this.currentShape = this.shape;
		
		this.affineTransform.setToTranslation(p.getX(), p.getY());
		this.affineTransform.scale(xy.getX(), xy.getY());
		this.affineTransform.translate(-p.getX(), -p.getY());	
			
		this.shape = this.affineTransform.createTransformedShape(this.currentShape);
		
		preX = this.shape.getBounds().x;
		preY = this.shape.getBounds().y;

	}
	
	public void finishResizing(Graphics2D graphics, int x, int y) {
		this.resize =false;
	}
	
	public void initRotating(Graphics2D graphics, int x, int y) {
		 centerP = new Point(cX, cY);
		 this.preX = x;
		 this.preY = y;
	     currentShape = this.shape;
	     
	      p1 = new Point(cX, cY);
	}
	
	public void setTheta(Graphics2D graphics, int x, int y) {
		 Point p2 = new Point(x, y); 
		 theta = Math.atan2(p2.y - this.p1.getY(), p2.x - this.p1.getX());
	}
	
	public double getTheta() {return theta;}
	public void setTheta(double theta) {
		this.theta = theta;
	}
	
	public void keepRotating(Graphics2D graphics, int x, int y) {
		 affineTransform.setToRotation(theta, cX, cY);
		 this.shape = this.affineTransform.createTransformedShape(this.currentShape);
		 
		 this.preX = x;
		 this.preY = y;
	}

	public void finishRotating(Graphics2D graphics, int x, int y) {
		eAnchors = EAnchors.NN;
		this.setResizing(graphics, x, y);
		
		eAnchors = EAnchors.WW;
		this.setResizing(graphics, x, y);
	}



}
