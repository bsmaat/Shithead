

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class CardLabel  extends JLabel {
	
	public CardLabel(Card c) {
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("/media/billy/HOME/workspace/Shithead/src/cards/" + toName(c)));
		} catch(Exception e) {
			System.out.println(e);
		}
		int scale_factor = 8;
		Image dimg = img.getScaledInstance((int)img.getWidth()/scale_factor, (int)img.getHeight()/scale_factor, Image.SCALE_SMOOTH);
		this.setIcon(new ImageIcon(dimg));
		this.setAlignmentX(SwingConstants.CENTER);
	}
	
	public String toName(Card c) {
		String tmp = c.toString().replaceAll(" ", "_");
		tmp = tmp.toLowerCase();
		tmp = tmp + ".png";
		return tmp;
	}
}
