package transformer;

import java.awt.Graphics2D;

import Shape.GShape;

public class GSResizer extends GSelectTransformer {


	@Override
	public void setValues(Graphics2D graphics, int x, int y) {
		for (GShape shape : this.getgShapeVector()) {
			shape.setAnchor(this.getgShape().getAnchor());
		}
	}

	@Override
	public void initTransformer(Graphics2D graphics, int x, int y) {	
		for (GShape shape : this.getgShapeVector()) {
			shape.initResizing(graphics, x, y);	
		}
	}

	@Override
	public void keepTransformer(Graphics2D graphics, int x, int y) {
		this.getgShape().setResizing(graphics, x, y);
		for (GShape shape : this.getgShapeVector()) {
			shape.setOrigin();
			shape.setxy(this.getgShape().getxy());
			shape.keepResizing(graphics, x, y);
		}
	}

	@Override
	public void finishTransformer(Graphics2D graphics, int x, int y) {
		for (GShape shape : this.getgShapeVector()) {
			shape.finishResizing(graphics, x, y);
		}
	}
}
