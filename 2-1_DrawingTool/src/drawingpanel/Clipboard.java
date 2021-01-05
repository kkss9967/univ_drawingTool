package drawingpanel;

import java.util.Vector;

import Shape.GShape;

public class Clipboard {
	private Vector<GShape> shapes;
	private Vector<GShape> selectedShapes;

	public Clipboard() {
		this.shapes = new Vector<GShape>();
		this.selectedShapes = new Vector<GShape>();
	}
	public void setContents(Vector<GShape> shapes) {
		this.shapes.clear();
		this.shapes.addAll(shapes);	//어레이 한꺼번에 들어감.
	}
	public Vector<GShape> getContents() {
		Vector<GShape> clonedShapes = new Vector<GShape>();
		for(GShape shape: this.shapes) {
			clonedShapes.add(shape.clone());
		}
		return clonedShapes;
	}
	public Vector<GShape> undo() {
		return null;
	}
	public Vector<GShape> redo() {
		return null;
	}
	
}

