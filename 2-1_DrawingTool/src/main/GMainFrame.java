package main;
import java.awt.BorderLayout;

import javax.swing.JFrame;

import drawingpanel.GDrawingPanel;
import global.GConstants.EMainFrame;
import menu.GMenuBar;
import toolbar.GTools;
import toolbar.GToolBar;

public class GMainFrame extends JFrame {
   
   private static final long serialVersionUID = 1L;
   
   //components
   private GMenuBar menuBar;
   private GToolBar toolBar;
   private GDrawingPanel drawingPanel;	
   private GTools colorMenu;

   
   public GMainFrame() {
      //attributes
      this.setLocation(EMainFrame.x.getInt(), EMainFrame.y.getInt());
      this.setSize(EMainFrame.width.getInt(), EMainFrame.height.getInt());
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      //components	(new 해야 자식)
      this.menuBar = new GMenuBar();
      this.setJMenuBar(this.menuBar);
   
      this.setLayout(new BorderLayout());
      this.toolBar = new GToolBar();
      this.add(this.toolBar, BorderLayout.WEST);
      
      this.colorMenu = new GTools("Color");
      this.add(this.colorMenu, BorderLayout.NORTH);
      
      this.drawingPanel = new GDrawingPanel();
      this.add(this.drawingPanel, BorderLayout.CENTER);
      
      //associates
      this.menuBar.associate(this.drawingPanel);
      this.toolBar.associate(this.drawingPanel);
      this.colorMenu.associate(drawingPanel);
   }


public void initialize() {
	this.menuBar.initialize();
	this.toolBar.initialize();
	this.drawingPanel.initialize();	
	this.colorMenu.initialize();
}
   
}