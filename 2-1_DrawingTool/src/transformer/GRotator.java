package transformer;

import java.awt.Graphics2D;

public class GRotator extends GTransformer {

	@Override
	public void initTransformer(Graphics2D graphics, int x, int y) {
		this.getgShape().finishRotating(graphics, x, y);
	    this.getgShape().initRotating(graphics, x, y);
	    }

	@Override
	public void keepTransformer(Graphics2D graphics, int x, int y) {
		this.getgShape().setTheta(graphics, x, y);
		this.getgShape().keepRotating(graphics, x, y);
		}

	@Override
	public void finishTransformer(Graphics2D graphics, int x, int y) {
		this.getgShape().setBasic();
		this.getgShape().finishRotating(graphics, x, y);
		}
	@Override
	public void continueTransformer(Graphics2D graphics, int x, int y) {}
}
