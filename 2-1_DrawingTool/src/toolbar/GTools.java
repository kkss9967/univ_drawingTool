package toolbar;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JToolBar;

import drawingpanel.GDrawingPanel;
import global.GConstants.EMenuItems;

public class GTools extends JToolBar{
	private static final long serialVersionUID = 1L;
	
	private GDrawingPanel drawingPanel;
	private ColorMenuHandler colorMenuHandler;

	private Vector<JButton> buttons;
	
	public void associate(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}
	
	public GTools(String text) {
		super(text);
		colorMenuHandler = new ColorMenuHandler();
		ImageIcon image;
		this.setBackground(Color.WHITE);
		this.buttons = new Vector<JButton>();
		for (EMenuItems items: EMenuItems.values()) {
			JButton button = new JButton();
			button.addActionListener(colorMenuHandler);
			image = new ImageIcon(items.getImage());
			button.setIcon(image);
			button.setActionCommand(items.name());
			this.buttons.add(button);
			this.add(button);
			button.setBorderPainted(false);
			button.setContentAreaFilled(false);
			button.setFocusable(false);
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					ImageIcon imagePressed;
					imagePressed = new ImageIcon(items.getImageP());			
					button.setIcon(imagePressed);
					button.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					ImageIcon image;
					image = new ImageIcon(items.getImage());			
					button.setIcon(image);
					button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
				});
			}		
	}
	public void initialize() {
	}
	private void undo() {
		drawingPanel.undo();		
	}
	private void redo() {
		drawingPanel.redo();
	}
	private void setLineColor() {
		Color lineColor = JColorChooser.showDialog(null, "Selected Fill Color", null);
		if (lineColor != null) {
			drawingPanel.setLineColor(lineColor);
		}
	}

	private void setFillColor() {
		Color fillColor = JColorChooser.showDialog(null, "Selected Line Color", null);
		if (fillColor != null) {
			drawingPanel.setFillColor(fillColor);
		}
	}

	private void LineColorBlack() {
		drawingPanel.setLineColor(Color.BLACK);
	}
	private void LineColorBlue() {
		drawingPanel.setLineColor(Color.BLUE);
	}
	private void LineColorRed() {
		drawingPanel.setLineColor(Color.RED);
	}
	
	private void setThickStroke() {	
		drawingPanel.setStroke(true);
	}
	private void setThinStroke() {
		drawingPanel.setStroke(false);
	}

	private class ColorMenuHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			switch (EMenuItems.valueOf(e.getActionCommand())) {
			case redo:
				redo();
				break;
			case undo:
				undo();
				break;
			case LineColorBlack:
				LineColorBlack();
				break;
			case LineColorBlue:
				LineColorBlue();
				break;
			case LineColorRed:
				LineColorRed();
				break;
			case SetLineColor:
				setLineColor();
				break;
			case SetFillColor:
				setFillColor();
				break;	
			case thinStroke:
				setThinStroke();
				break;
			case thickStroke:
				setThickStroke();
				break;
			default:
				break;
			}
		}
	
	}


}
