package toolbar;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import drawingpanel.GDrawingPanel;
import global.GConstants.EToolBar;

public class GToolBar extends JToolBar {	//도형, 텍스트 크기, 색, 삽입 등 도구툴!
	private static final long serialVersionUID = 1L;

	//components
//	private Vector<JRadioButton> buttons;
	private Vector<JButton> buttons;
	private Vector<String> v;
	private String a;
	
	private JButton button;
	
	//associations(친구. new 안했기 때문. 메인프레임이 만들어서 연결시켜준 것.)
	private GDrawingPanel drawingPanel;
	public void associate(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}
	
	public GToolBar() {
		ActionHandler actionHandler = new ActionHandler();
		ImageIcon imageBasic;
		this.setBackground(Color.white);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.buttons = new Vector<JButton>();
			this.v = new Vector<String>();			
		for(EToolBar eToolBar: EToolBar.values()) {
			JButton button = new JButton();
			button.setActionCommand(eToolBar.name());	//이넘클래스 텍스트를 커맨드로 받음 - 커맨드 일치하면 버튼 작동.
			button.addActionListener(actionHandler);
				this.v.add(eToolBar.getImage());
				imageBasic = new ImageIcon(eToolBar.getImage());
			button.setIcon(imageBasic);
			this.buttons.add(button);	//벡터에 버튼 넣음
			this.add(button);	//버튼 넣음
			button.setBorderPainted(false);
			button.setContentAreaFilled(false);
			button.setFocusable(false);
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int count = 0;
					for(JButton button: buttons) {
						ImageIcon imageBasic;
						imageBasic = new ImageIcon(v.elementAt(count));
						button.setIcon(imageBasic);
						count++;
					}
					ImageIcon imagePressed;
					imagePressed = new ImageIcon(eToolBar.getImageP());
					button.setIcon(imagePressed);
					a = eToolBar.getText();
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					if (eToolBar.getText() != a) {
						ImageIcon imagePressed;
						imagePressed = new ImageIcon(eToolBar.getImageP());
						button.setIcon(imagePressed);
						button.setCursor(new Cursor(Cursor.HAND_CURSOR));
					}
				}

				@Override
				public void mouseExited(MouseEvent e) {
					if (eToolBar.getText() != a) {
						ImageIcon imageBasic;
						imageBasic = new ImageIcon(eToolBar.getImage());
						button.setIcon(imageBasic);
						button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}
				}
			});
		}
	}
	public void initialize() {
		this.buttons.get(EToolBar.select.ordinal()).doClick(); //미리 네모 클릭되어 있게 함
	}

	private class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			drawingPanel.setCurrentTool(EToolBar.valueOf(e.getActionCommand()));
				//텍스트가 몇번째 enum인지 구해서 모양을 가져옴.
			}
		}
		
	}
	
