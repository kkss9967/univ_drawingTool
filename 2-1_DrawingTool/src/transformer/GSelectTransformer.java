package transformer;

import java.awt.Graphics2D;
import java.util.Vector;

import Shape.GShape;
import Shape.GShape.EOnState;

public abstract class GSelectTransformer {
	
	private GShape gShape;
	private Vector<GShape> gShapeVector;
	private EOnState eOnState;
	
	public void setState(EOnState eOnState) {this.eOnState = eOnState;}
	
	public GShape getgShape() {return gShape;}
	public void setgShape(GShape gShape) {this.gShape = gShape;}
	
	public Vector<GShape> getgShapeVector() {return gShapeVector;}
	public void setgShapeVector(Vector<GShape> gShapeVector) {this.gShapeVector = gShapeVector;}
	public void addgShapeVector(GShape shape) {this.gShapeVector.add(shape);}
	
	public abstract void setValues(Graphics2D graphics, int x, int y);
	public abstract void initTransformer(Graphics2D graphics, int x, int y);
	public abstract void keepTransformer(Graphics2D graphics, int x, int y);
	public abstract void finishTransformer(Graphics2D graphics, int x, int y);

}
