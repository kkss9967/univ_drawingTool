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

public class GToolBar extends JToolBar {	//����, �ؽ�Ʈ ũ��, ��, ���� �� ������!
	private static final long serialVersionUID = 1L;

	//components
//	private Vector<JRadioButton> buttons;
	private Vector<JButton> buttons;
	private Vector<String> v;
	private String a;
	
	private JButton button;
	
	//associations(ģ��. new ���߱� ����. ������������ ���� ��������� ��.)
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
			button.setActionCommand(eToolBar.name());	//�̳�Ŭ���� �ؽ�Ʈ�� Ŀ�ǵ�� ���� - Ŀ�ǵ� ��ġ�ϸ� ��ư �۵�.
			button.addActionListener(actionHandler);
				this.v.add(eToolBar.getImage());
				imageBasic = new ImageIcon(eToolBar.getImage());
			button.setIcon(imageBasic);
			this.buttons.add(button);	//���Ϳ� ��ư ����
			this.add(button);	//��ư ����
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
		this.buttons.get(EToolBar.select.ordinal()).doClick(); //�̸� �׸� Ŭ���Ǿ� �ְ� ��
	}

	private class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			drawingPanel.setCurrentTool(EToolBar.valueOf(e.getActionCommand()));
				//�ؽ�Ʈ�� ���° enum���� ���ؼ� ����� ������.
			}
		}
		
	}
	
