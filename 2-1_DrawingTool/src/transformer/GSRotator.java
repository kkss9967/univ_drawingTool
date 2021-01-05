package transformer;

import java.awt.Graphics2D;

import Shape.GAnchors.EAnchors;
import Shape.GShape;

public class GSRotator extends GSelectTransformer {


	@Override
	public void setValues(Graphics2D graphics, int x, int y) {
		
	}
	
	@Override
	public void initTransformer(Graphics2D graphics, int x, int y) {
	if(this.getgShapeVector() == null) {
			this.getgShape().finishRotating(graphics, x, y);
		    this.getgShape().initRotating(graphics, x, y);
		} else {
			for(GShape shape: this.getgShapeVector()) {
				shape.setAnchor(EAnchors.RR);
				shape.finishRotating(graphics, x, y);
				shape.initRotating(graphics, x, y);
			}
		}
	    
	}

	@Override
	public void keepTransformer(Graphics2D graphics, int x, int y) {
		this.getgShape().setTheta(graphics, x, y);
		for (GShape shape : this.getgShapeVector()) {
			shape.setTheta(this.getgShape().getTheta());
			shape.keepRotating(graphics, x, y);
		}
	}

	@Override
	public void finishTransformer(Graphics2D graphics, int x, int y) {
		if(this.getgShapeVector() == null) {
			this.getgShape().setBasic();
			this.getgShape().finishRotating(graphics, x, y);
				} else {
			for(GShape shape: this.getgShapeVector()) {
				shape.setBasic();
				shape.finishRotating(graphics, x, y);
				}
		}
	}

}
