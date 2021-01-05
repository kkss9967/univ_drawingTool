package Shape;

import java.awt.Graphics2D;
import java.util.Vector;

public class GPolygon extends GShape {
	private static final long serialVersionUID = 1L;
	private java.awt.Polygon polygon;
	
	 public GPolygon() {
	      super();
	      this.polygon = new java.awt.Polygon();
	      this.shape = this.polygon;
	   }
	 
		@Override
		public GShape newInstance() {
			return new GPolygon();
		}
		
	public void setOrigin(Graphics2D g2, int x, int y) {
		this.polygon.addPoint(x, y);
		this.polygon.addPoint(x, y);
	}

	@Override
	public void setPoint(int x, int y) {
		this.polygon.xpoints[this.polygon.npoints-1] = x;
		this.polygon.ypoints[this.polygon.npoints-1] = y;		
		//어레이 가지고있다가 그릴 때 카피해서 쓰기 때문에 원래 데이터가 바뀌지 않음.
		//폴리 라인으로 그리다가, 마지막에 끝낼 때 폴리곤으로. 근데 움직일 땐 폴리곤..
		//어파인트랜스폼을 사용해야 함. 지금 방법은 편법임. -> 너무 어려우니까 잠시 놔뒀다가 나중에 쓰자.
	}
	public void addPoint(int x, int y) {
		this.polygon.addPoint(x, y);
	}

//	@Override
//	public void setCenter() {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public GShape selectedCheck(GShape gShape) {
		// TODO Auto-generated method stub
		return null;
	}


	}
