

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ShitGUI extends JPanel {
	public static void main(String[] args) {
		ShitGUI gui = new ShitGUI();
	}

	
	JFrame frame = new JFrame();
	JPanel mainPanel = new JPanel();
	public ShitGUI() {
		mainPanel.setPreferredSize(new Dimension(500,500));
		mainPanel.setBorder(new EmptyBorder(10,10,10,10));
		mainPanel.setBackground(Color.BLACK);

		/*
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("/media/billy/HOME/workspace/Shithead/src/gui/cards/2_of_hearts.png"));
		} catch(Exception e) {
			
		}
		int scale_factor = 8;
		Image dimg = img.getScaledInstance((int)img.getWidth()/scale_factor, (int)img.getHeight()/scale_factor, Image.SCALE_SMOOTH);
		JLabel picLabel = new JLabel(new ImageIcon(dimg));
		*/
		/*
		CardLabel picLabel = new CardLabel();
		mainPanel.add(picLabel);
		*/
		frame.add(mainPanel);
		frame.pack();
		frame.setVisible(true);
	}
	
	

}
