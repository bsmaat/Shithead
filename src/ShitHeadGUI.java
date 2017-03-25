import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/*
 * 
 */
public class ShitHeadGUI extends JPanel {//implements java.util.Observer {

	
	public static Color BGCOLOR = Color.GREEN;
	public static int FRAME_WIDTH = 1000;
	public static int FRAME_HEIGHT = 600;
	
	public static int TABLE_WIDTH = 600;
	public static int TABLE_HEIGHT = 600;
	
	JFrame frame = new JFrame("Shithead");

	
	TablePanel tablePanel;
	DisplayPanel displayPanel;
	MessagePanel messagePanel;
	
	JMenuBar menuBar;
	JMenu menu, submenu;
	JMenuItem menuItem;
	
	
	public ShitHeadGUI() {
		init();
	}

	public void display(String s) {
		Game.display(s);
		displayPanel.setText(s);
		messagePanel.addItemToList(s);
	}
	
	public void init() {
		this.setLayout(new BorderLayout());
		tablePanel = new TablePanel();
		displayPanel = new DisplayPanel();
		messagePanel = new MessagePanel();
		this.setPreferredSize(new Dimension(ShitHeadGUI.FRAME_WIDTH,ShitHeadGUI.FRAME_HEIGHT));
		this.add(tablePanel, BorderLayout.CENTER);
		this.add(displayPanel, BorderLayout.PAGE_END);
		this.add(messagePanel, BorderLayout.EAST);
		
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");;
		menuBar.add(menu);
		
		menuItem = new JMenuItem("A text-only menu item",KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		
		menu.add(menuItem);
		
		frame.setJMenuBar(menuBar);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(this);
		frame.pack();
	}
	
}
