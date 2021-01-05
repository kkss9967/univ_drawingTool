package Shape;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

public class GEllipse extends GShape {
   private static final long serialVersionUID = 1L;
   
   private Ellipse2D.Double ellipse;
   
   public GEllipse() {
      super();
      this.shape = new Ellipse2D.Double();
      this.ellipse = (Double) this.shape;
   }

   @Override
   public void setPoint(int x, int y) {
      this.ellipse.setFrame(Math.min(x, px), Math.min(y, py), Math.abs(x-px), Math.abs(y-py));

   }
   
   public void addPoint(int x, int y) {}
   public GShape newInstance() {return new GEllipse();}
   public GShape selectedCheck(GShape gShape) {return null;}

@Override
public void setOrigin(Graphics2D g2, int x, int y) {
	 px = x;
     py = y;
     this.ellipse.setFrame(Math.abs(x), Math.abs(y), 0, 0);	
}

}