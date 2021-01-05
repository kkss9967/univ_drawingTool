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
   public void associate(GDrawingPanel drawingPanel) { 	//메뉴별로 메뉴아이템이 다르니까 그것마다 클래스 따로 만들어줘야 함.
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
//일반화 시켜서 루핑 돌리기.
}
   
}