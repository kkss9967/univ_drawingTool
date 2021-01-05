package transformer;

import java.awt.Graphics2D;

public class GMover extends GTransformer {

	public GMover() {
		
	}

	@Override
	public void initTransformer(Graphics2D graphics, int x, int y) {
		this.getgShape().initMoving(graphics, x, y);	
		}

	@Override
	public void keepTransformer(Graphics2D graphics, int x, int y) {
		this.getgShape().keepMoving(graphics, x, y);
		}

	@Override
	public void finishTransformer(Graphics2D graphics, int x, int y) {
		this.getgShape().setBasic();
		}

	@Override
	public void continueTransformer(Graphics2D graphics, int x, int y) {}
}
