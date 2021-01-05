package transformer;

import java.awt.Graphics2D;

import Shape.GShape;

public class GSMover extends GSelectTransformer {

	@Override
	public void setValues(Graphics2D graphics, int x, int y) {}

	@Override
	public void initTransformer(Graphics2D graphics, int x, int y) {
		for (GShape shape : this.getgShapeVector()) {
			shape.initMoving(graphics, x, y);
		}
	}

	@Override
	public void keepTransformer(Graphics2D graphics, int x, int y) {
		for (GShape shape : this.getgShapeVector()) {
			shape.keepMoving(graphics, x, y);
		}
	}

	@Override
	public void finishTransformer(Graphics2D graphics, int x, int y) {
		for (GShape shape : this.getgShapeVector()) {
			shape.setBasic();
		}
	}
}
