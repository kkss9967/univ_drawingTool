package menu;
import java.awt.Color;

import javax.swing.JMenuBar;

import drawingpanel.GDrawingPanel;
import global.GConstants.EMenu;

public class GMenuBar extends JMenuBar {

   private static final long serialVersionUID = 1L;

   private GFileMenu fileMenu;
   private GEditMenu EditMenu;
   
   private GDrawingPanel drawingPanel; 
   public void associate(GDrawingPanel drawingPanel) { 	//�޴����� �޴��������� �ٸ��ϱ� �װ͸��� Ŭ���� ���� �������� ��.
      this.drawingPanel = drawingPanel;
   }
   
   public GMenuBar() {
	   this.setBackground(Color.WHITE);
	   
      this.fileMenu = new GFileMenu(EMenu.fileMenu.getText());
      this.add(this.fileMenu);
      
      this.EditMenu = new GEditMenu(EMenu.editMenu.getText());
      this.add(this.EditMenu);
 
   }

public void initialize() {
	//associate
    this.fileMenu.associate(drawingPanel);
    this.EditMenu.associate(drawingPanel);

    //initialize
	this.fileMenu.initialize();
	this.EditMenu.initialize();
//�Ϲ�ȭ ���Ѽ� ���� ������.
}
   
}