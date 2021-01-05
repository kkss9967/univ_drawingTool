package drawingpanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.Vector;

import javax.swing.JPanel;

import Shape.GDrawPen;
import Shape.GPolygon;
import Shape.GShape;
import Shape.GShape.EOnState;
import Shape.Select;
import Shape.eraser;
import global.GConstants.EToolBar;
import transformer.GDrawer;
import transformer.GMover;
import transformer.GSMover;
import transformer.GSResizer;
import transformer.GSRotator;
import transformer.GResizer;
import transformer.GRotator;
import transformer.GSelectTransformer;
import transformer.GTransformer;

public class GDrawingPanel extends JPanel implements Printable {

	private static final long serialVersionUID = 1L;

	private MouseHandler mouseHandler;
	private Clipboard clipboard;
	
	private Vector<GShape> selectV;
	private Vector<Integer> cv;
	
	private Vector<GShape> shapeVector;
//	private Vector<GShape> lineVector;

	public Vector<GShape> getShapeVector() {
		return shapeVector;}
	
//	public void clearshape() {this.currentShape = null; this.repaint();}
	@SuppressWarnings("unchecked")
	public void restoreShapeVector(Object shapeVector) {
		if(shapeVector == null) {
			this.shapeVector.clear();
			this.currentShape = null;
		}else {
			this.shapeVector = (Vector<GShape>)shapeVector;
		}
		this.repaint();
	}
	
	private enum EActionState {eReady, eTransforming, ePAction}; // 상태
	private EActionState eActionState;
	
	private boolean updated; //오염 됐는지?
	public boolean isUpdated() {return this.updated;}
	public void setUpdated(boolean updated) { this.updated = updated; }
	
	private boolean check;
	private boolean firstClick;
	private boolean showAnchor;
	boolean wSelect;
	
	private GShape currentShape;
	private GShape currentTool;
	private GTransformer gTransformer;
	private GSelectTransformer selectT;

	private Color lineColor, fillColor;
	private float dashes;
	
	public void setCurrentTool(EToolBar currentTool) {
		this.currentTool = currentTool.getShape();
	}
	public void setCurrentTool(GShape shape) {
		this.currentTool = shape;
	}
	
	public GDrawingPanel() {
		this.eActionState = EActionState.eReady;
		this.updated = false;
		this.check = false;
		this.firstClick = false;
		this.showAnchor = false;
		this.wSelect = false;
		
		this.setForeground(Color.BLACK);
		this.setBackground(Color.WHITE);

		this.mouseHandler = new MouseHandler();
		this.addMouseListener(this.mouseHandler);
		this.addMouseMotionListener(this.mouseHandler);

		this.clipboard = new Clipboard();

		this.shapeVector = new Vector<GShape>();
//		this.lineVector = new Vector<GShape>();
		this.gTransformer = null;
		this.selectT = null;	
		this.cv = null;
		
		initializeGraphicsAttributes();
	}

	public void initialize() {
	}
	
	public void paint(Graphics graphics) {
		super.paint(graphics);
		this.clearAnchor();
		
		for (GShape shape : this.shapeVector) {	
				shape.drawLine((Graphics2D) graphics);
				shape.draw((Graphics2D) graphics);
		}		
		//라인일 때 for문
//		for(GShape line: this.lineVector) {
//			line.drawLine((Graphics2D) graphics);
//		}
		if (currentShape != null) {
			this.currentShape.setSelected(true);
			if(this.gTransformer.getgShape() instanceof Select) {
				currentShape.select((Graphics2D) graphics);	//점선				
			}else {
					this.currentShape.drawLine((Graphics2D) graphics);
					this.currentShape.draw((Graphics2D) graphics);
				}
	}
		if(this.selectV != null) {
		for(GShape shape: this.selectV) {
			shape.setSelected(true);
			if(showAnchor) {
			shape.drawAnchor((Graphics2D) graphics);
			}
		}
		}
	}

	private EOnState onShape(int x, int y) { // 그림 그릴때마다 쉐잎벡터에 넣음. 밑에 누가 있냐 없냐를 판단
		// this.currentShape = null;
		for (GShape shape : this.shapeVector) {
			EOnState eOnState = shape.onShape(x, y);
			if (eOnState != null) { // 위에있다
				this.currentShape = shape;
				return eOnState;
			}
		}
		return null;
	}

	public void setLineColor(Color lineColor) {
		if (selectedSetColor(true, lineColor)) {
			return;
		}
		this.lineColor = lineColor;
	}

	public void setFillColor(Color fillColor) {
		if (selectedSetColor(false, fillColor)) {
			return;
		}
		this.fillColor = fillColor;
	}

	public void setStroke(boolean flag) {
		if(currentShape != null) {
		currentShape.setStroke(flag);
		repaint();
		}
	}
	
	private boolean selectedSetColor(boolean flag, Color color) {
		if (currentShape != null) {
			if (flag) {
				currentShape.setLineColor(color);
			} else {
				currentShape.setFillColor(color);
			}
			repaint();
			return true;
		}
		return false;
	}

	private void initializeGraphicsAttributes() {
		lineColor = Color.BLACK;
		fillColor = Color.WHITE;
		float dashes = 4;
		this.dashes = dashes;
	}

	private void defineActionState(int x, int y) {
//		this.showAnchor = false;
		if (currentTool instanceof GPolygon) {
			this.eActionState = EActionState.ePAction;
		} else {
			this.eActionState = EActionState.eTransforming;
		}
		
		EOnState eOnState = onShape(x, y);
		if (eOnState == null) { // 드로잉
			this.gTransformer = new GDrawer();
			if(selectV != null)
				selectV.clear();
			}else {
				
				if(selectV != null) {
					if(!selectV.contains(currentShape)) {
						selectV.clear();
					}
				}
				this.eActionState = EActionState.eTransforming;
				switch(eOnState) {
				case eOnShape:
					this.gTransformer = new GMover(); 
					this.selectT = new GSMover();
					break;
				case eOnResize:
					this.gTransformer = new GResizer();
					this.selectT = new GSResizer();
					break;
				case eOnRotate:
					this.gTransformer= new GRotator();
					this.selectT = new GSRotator();
					break;
				default:
					eActionState =  null;
					break;
			}
		}
	}
	private void selectOnShape() {
		this.selectV = new Vector<GShape>();
		int count = 0;
		cv = new Vector<Integer>();
		GShape gS;
		for(GShape gShape: this.shapeVector) {
			gS = this.gTransformer.getgShape().selectedCheck(gShape);
			if(gS != null) {
			this.selectV.add(gS);
			gS.setSelected(true);
			cv.add(count);
			}
			count++;
		}
		for(int i = 0; i<cv.size(); i++) {
			shapeVector.setElementAt(selectV.elementAt(i), cv.elementAt(i));
		}
	}

	boolean select;
	private void initTransforming(int x, int y) {
		this.clearAnchor();
		repaint();

		if (this.gTransformer instanceof GDrawer) {
			select = false;
			this.currentShape = this.currentTool.newInstance();
			this.currentShape.setLineColor(lineColor);
			this.currentShape.setFillColor(fillColor);
				this.gTransformer.setgShape(this.currentShape);
			this.gTransformer.initTransformer((Graphics2D) this.getGraphics(), x, y);
		} else {
			if (selectV == null) {
				select = false;
				this.gTransformer.setgShape(this.currentShape);
				this.gTransformer.initTransformer((Graphics2D) this.getGraphics(), x, y);
			} else if(selectV.size() == 0) {
				select = false;
				this.gTransformer.setgShape(this.currentShape);
				this.gTransformer.initTransformer((Graphics2D) this.getGraphics(), x, y);
			} else if (selectV.size() == 1){
				select = false;
				this.currentShape = this.selectV.elementAt(0);
				this.gTransformer.setgShape(this.currentShape);
				this.gTransformer.initTransformer((Graphics2D) this.getGraphics(), x, y);	
			}else {
				select = true;
				this.selectT.setgShape(currentShape);
				this.selectT.setgShapeVector(selectV);
				this.selectT.setValues((Graphics2D) this.getGraphics(), x, y);
				this.selectT.initTransformer((Graphics2D) this.getGraphics(), x, y);
			}
		
		}
	}
	private void keepTransforming(int x, int y) {
		Graphics2D graphics2d = (Graphics2D) this.getGraphics();
		this.showAnchor = false;
		repaint();
		if (!select) {
			this.gTransformer.keepTransformer(graphics2d, x, y);
		} else {
			this.selectT.keepTransformer(graphics2d, x, y);
		}

	}

	private void continueTransforming(int x, int y) {
		Graphics2D g2 = (Graphics2D) this.getGraphics();
		this.gTransformer.continueTransformer(g2, x, y);
	}
	
	private void finishTransforming(int x, int y) {
		if(!select) {
			this.gTransformer.finishTransformer((Graphics2D)this.getGraphics(), x, y);
			if(!(this.gTransformer.getgShape() instanceof Select) && !(this.gTransformer.getgShape() instanceof eraser)) {
				if(this.gTransformer instanceof GDrawer) {
//					if (!(this.gTransformer.getgShape() instanceof GDrawPen)) {
						this.shapeVector.add(this.currentShape);
//					}else {
//						this.lineVector.add(this.currentShape);
//					}
				}	
				if(!(this.gTransformer.getgShape() instanceof GDrawPen)) {
				this.currentShape.drawAnchor((Graphics2D) this.getGraphics());
				}
				} else {
					selectOnShape();
					this.wSelect = true;
					currentShape = null;
					repaint();
				}
		} else {
			this.selectT.finishTransformer((Graphics2D)this.getGraphics(), x, y);
			Vector<GShape> selectTrans = this.selectT.getgShapeVector();
			int index = selectTrans.size() - 1;
			for(int i = this.shapeVector.size() - 1; i >= 0; i--) {
				if(this.shapeVector.elementAt(i).equals(selectV.elementAt(index))) {
					this.shapeVector.set(i, selectTrans.elementAt(index));
					this.shapeVector.elementAt(i).drawAnchor((Graphics2D)this.getGraphics());
					repaint();
				}
			}
		}
		this.updated = true;
		this.showAnchor = true;
	}

	public void cut() {
		Vector<GShape> selectedShapes = new Vector<GShape>();
		for(int i = this.shapeVector.size()-1; i>=0; i--) {
			if(this.shapeVector.get(i).isSelected()) {
				selectedShapes.add(this.shapeVector.get(i));	//선택된 도형 선택, 새로운 벡터에 옮김
				this.shapeVector.remove(i);	//기존 벡터에서 없앰.
			}
		}
		this.currentShape = null;
		this.clipboard.setContents(selectedShapes);	//클립보드에 저장?
		this.repaint();
	}
	public void copy() {
		Vector<GShape> selectedShapes = new Vector<GShape>();
		for(int i = this.shapeVector.size()-1; i>=0; i--) {
			if(this.shapeVector.get(i).isSelected()) {
				selectedShapes.add(this.shapeVector.get(i));	//선택된 도형 선택, 새로운 벡터에 옮김
			}
		}
		this.clipboard.setContents(selectedShapes);	//클립보드에 저장?
		this.repaint();
	}
	public void paste() {
		Vector<GShape> shapes = this.clipboard.getContents();
		this.shapeVector.addAll(shapes);
		this.repaint();
	}

	public void group() {}
	public void unGroup() {}

	public void undo() {
	
	}

	public void redo() {
	
	}
	public void clearAnchor() {
		for (GShape shape : this.shapeVector) {	
			shape.setSelected(false);
		}
	}
	
	public void deleteAnchor() {
		for (GShape shape : this.shapeVector) {
			if(shape != currentShape) {
				shape.setSelected(false);
			}else {
				shape.setSelected(true);
				this.check = true;
			}
		}
		repaint();
	}	

	private class MouseHandler implements MouseListener, MouseMotionListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (eActionState == EActionState.ePAction) {
				if (e.getClickCount() == 1) {
					mouse1Clicked(e);
				} else if (e.getClickCount() == 2) {
					mouse2Clicked(e);
				}
			}
		}

		public void mouse2Clicked(MouseEvent e) {
			finishTransforming(e.getX(), e.getY());
			eActionState = EActionState.eReady;
			firstClick = false;
		}

		public void mouse1Clicked(MouseEvent e) {
			if (!firstClick) {
				firstClick = true;
			} else {
				continueTransforming(e.getX(), e.getY());
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (eActionState == EActionState.ePAction) {
					keepTransforming(e.getX(), e.getY());
			}
		}

		public void mousePressed(MouseEvent e) {
			if (eActionState == EActionState.eReady) {
				defineActionState(e.getX(), e.getY());
				initTransforming(e.getX(), e.getY());
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (eActionState == EActionState.eTransforming) {
				finishTransforming(e.getX(), e.getY());
				eActionState = EActionState.eReady;
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (eActionState == EActionState.eTransforming) {
				keepTransforming(e.getX(), e.getY());
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	}

	@Override
	public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
		if (page > 0) {
			return NO_SUCH_PAGE;
		}
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());
		for (GShape shape: this.shapeVector) {
			shape.draw((Graphics2D)g);
		}
		return PAGE_EXISTS;
	}

	
	

}
