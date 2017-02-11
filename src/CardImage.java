import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;

public class CardImage {

	public static int WIDTH = 62;
	public static int HEIGHT = 90;
	
	BufferedImage img = null;
	Image dimg = null;

	public CardImage() {
		
	}
	public CardImage(Card c) {
		setImage(c);
	}
	
	public Image getImage() {
		return dimg;
	}
	
	
	public Image setImage(Card c) {
		try {
			img = ImageIO.read(new File("src/cards/" + toName(c)));
		} catch(Exception e) {
			System.out.println(e);
		}
		
		
		int scale_factor = 8;
		dimg = img.getScaledInstance((int)img.getWidth()/scale_factor, (int)img.getHeight()/scale_factor, Image.SCALE_SMOOTH);
		//Graphics g = img.createGraphics();
		//g.dispose();
		
		
		return dimg;
	}
	
	public String toName(Card c) {
		String tmp = c.toString().replaceAll(" ", "_");
		tmp = tmp.toLowerCase();
		tmp = tmp + ".png";
		return tmp;
	}

}
