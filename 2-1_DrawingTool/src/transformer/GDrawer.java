package transformer;

import java.awt.Graphics2D;

public class GDrawer extends GTransformer{
	
	public GDrawer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initTransformer(Graphics2D g2, int x, int y) {
		this.getgShape().setOrigin(g2, x, y);
//		g2.drawRect(x, y, 0, 0);			
		
	}

	@Override
	public void keepTransformer(Graphics2D g2, int x, int y) {
		this.getgShape().setPoint(x, y);
		
	}

	@Override
	public void finishTransformer(Graphics2D g2, int x, int y) {

	}

	@Override
	public void continueTransformer(Graphics2D g2, int x, int y) {
		this.getgShape().addPoint(x, y);
		
	}
}
