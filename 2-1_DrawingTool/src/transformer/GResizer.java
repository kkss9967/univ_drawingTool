package transformer;

import java.awt.Graphics2D;

public class GResizer extends GTransformer{

	@Override
	public void initTransformer(Graphics2D g2, int x, int y) {
	    this.getgShape().initMoving(g2, x, y);
		
	}

	@Override
	public void keepTransformer(Graphics2D g2, int x, int y) {
		this.getgShape().setResizing(g2, x, y);
		this.getgShape().setOrigin();
		this.getgShape().keepResizing(g2, x, y);
		
	}

	@Override
	public void finishTransformer(Graphics2D g2, int x, int y) {
//		this.getgShape().drawAnchor(g2);
		this.getgShape().finishResizing(g2, x, y);

		
	}

	@Override
	public void continueTransformer(Graphics2D g2, int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
