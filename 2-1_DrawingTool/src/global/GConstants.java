package global;

import Shape.GDrawPen;
import Shape.GEllipse;
import Shape.GLine;
import Shape.GPolygon;
import Shape.GRectangle;
import Shape.GShape;
import Shape.Select;
import Shape.eraser;

public class GConstants {

	public enum EMainFrame {
		x(650),
		y(250),
		width(900),
		height(650)
		;
		private int value;
		private EMainFrame(int value) {
			this.value = value;
		}
		public int getInt() {
			return this.value;
		}
	}
	public static enum EMenuItems { 
		undo("images/undoBasic.png", "images/undoPressed.png"),
		redo("images/redoBasic.png", "images/redoPressed.png"),
		LineColorBlack("images/colorBlackPressed.png", "images/colorBlackBasic.png") , 
		LineColorBlue("images/colorBluePressed.png", "images/colorBlueBasic.png") , 
		LineColorRed("images/colorRedPressed.png", "images/colorRedBasic.png") , 
		SetLineColor("images/lineColorBasic.png", "images/lineColorPressed.png") , 
		SetFillColor("images/PaletteBasic.png", "images/PalettePressed.png"),
		thinStroke("images/minusStrokeBasic.png", "images/minusStrokePressed.png"),
		thickStroke("images/plusStrokeBasic.png", "images/plusStrokePressed.png")
		;
		
		private String image;
		private String imageP;
		
		private EMenuItems(String image, String imageP) {
			this.image = image;
			this.imageP = imageP;

		}
		public String getImage() {
			return this.image;
		}
		public String getImageP() {
			return this.imageP;
		}
	}
		
	public enum EToolBar {
		drawPen("drawPen", new GDrawPen(), "images/penBasic.png", "images/penPressed.png"),
		eraser("eraser", new eraser(), "images/eraserBasic.png", "images/eraserPressed.png"),
		rectangle("rectangle", new GRectangle(), "images/RectangleBasic.png", "images/RectanglePressed.png"),
		polygon("polygon", new GPolygon(), "images/PolygonBasic.png", "images/PolygonPressed.png"),
		ellipse("ellipse", new GEllipse(), "images/EllipseBasic.png", "images/EllipsePressed.png"),
		line("line", new GLine(), "images/lineBasic.png", "images/linePressed.png"),
		select("select", new Select(), "images/selectionBasic.png", "images/selectionPressed.png")
		;
		
		private String text;
		private GShape shape;
		private String image;
		private String imageP;
		
		private EToolBar(String text, GShape shape, String image, String imageP) {
			this.text = text;
			this.shape = shape;
			this.image = image;
			this.imageP = imageP;
			
		}
		public GShape getShape() {
			return this.shape;
		}
		public String getText() {
			return this.text;
		}
		public String getImage() {
			return this.image;
		}
		public String getImageP() {
			return this.imageP;
		}
	}
	
	public enum EDrawSome {
		rectangle("Rectangle"),
		ellipse("Ellipse"),
		line("Line")
		;
		private String text;
		private EDrawSome(String text) {
			this.text = text;
		}
		public String getText() {
			return this.text;
		}
	}
	
	public enum EMenu {
		fileMenu("File"),
		editMenu("Edit")
		;
		private String text;
		private EMenu(String text) {
			this.text = text;
		}
		public String getText() {
			return this.text;
		}
	}
	
	public enum EFileMenu {
		newItem("���θ����", "nNew", "������ ���� ����ϴ�."),
		openItem("Open", "open", "������ ���ϴ�."),
		saveItem("Save", "save", "������ �����մϴ�."),
		saveAsItem("�ٸ� �̸����� ����", "saveAs", "������ �ٸ� �̸����� �����մϴ�."),
		seperate("�и�", "seperate", null),
		printItem("�μ�", "print", "������ �μ��մϴ�."),
		closeItem("�ݱ�", "close", "���α׷��� �����մϴ�.")
		;
		private String text;
		private String method;
		private String toolTip;
		
		private EFileMenu(String text, String method, String toolTip) {
			this.text = text;
			this.method = method;
			this.toolTip = toolTip;
		}
		public String getText() {
			return this.text;
		}
		public String getMethod() {
			return this.method;
		}
		public String getToolTip() {
			return this.toolTip;
		}

	}
	
	public enum EEditMenu {
		undo("�ǵ�����", "undo"),
		redo("�ٽý���", "redo"),
		seperate1("�и�", "seperate"),
		cut("�߶󳻱�", "cut"),
		copy("����", "copy"),
		paste("�ٿ��ֱ�", "paste"),
		seperate2("�и�", "seperate"),
		group("�׷�", "group"),
		ungroup("�׷�����", "ungroup")
		;
		private String text;
		private String method;
		
		private EEditMenu(String text, String method) {
			this.text = text;
			this.method = method;
		}
		public String getText() {
			return this.text;
		}
		public String getMethod() {
			return this.method;
		}
	}
//	public static enum EColorMenuItems { 
//		SetLineColor , 
//		ClearLineColor , 
//		SetFillColor , 
//		ClearFillColor };
	

//	public static final int ANCHOR_W = 6;
//	public static final int ANCHOR_H = 6;
//	public static final int RR_OFFSET = 6;
//	public static final Color ANCHOR_LINECOLOR = Color. BLACK; 
//	public static final Color ANCHOR_FILLCOLOR = Color. WHITE; 
//	public enum EAnchorTypes{
//		NW, NN, NE, WW, EE, SW, SS, SE, RR, NONE;
//	}
}
