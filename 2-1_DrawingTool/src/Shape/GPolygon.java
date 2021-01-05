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
		//��� �������ִٰ� �׸� �� ī���ؼ� ���� ������ ���� �����Ͱ� �ٲ��� ����.
		//���� �������� �׸��ٰ�, �������� ���� �� ����������. �ٵ� ������ �� ������..
		//������Ʈ�������� ����ؾ� ��. ���� ����� �����. -> �ʹ� �����ϱ� ��� ���״ٰ� ���߿� ����.
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
