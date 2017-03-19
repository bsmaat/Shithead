import java.util.List;
import java.util.Map;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class CardPng {
	
	
	static Map<String, Image> img = new HashMap<String, Image>();
	
	public static void init() {
		int scale_factor = 8;

		for (int i = 2; i < 15; i++) {
			for (int j = 1; j < 5; j++) {
				Card c = new Card(j,i);
				BufferedImage tmp = null;
				try {
					tmp = ImageIO.read(new File("src/cards/" + toName(c)));
				} catch (Exception e) {
					System.out.println(c.toString());
					Game.display("ERROR: " + e);
				}
				
				Image dimg = tmp.getScaledInstance((int)tmp.getWidth()/scale_factor, (int)tmp.getHeight()/scale_factor, Image.SCALE_SMOOTH);
			
				//img.put(c.toString(), tmp);
				img.put(c.toString(), dimg);
			}
		}
		
		BufferedImage tmp = null;
		try {
			tmp = ImageIO.read(new File("src/cards/back.png"));
			Image dimg = tmp.getScaledInstance((int)tmp.getWidth()/scale_factor, (int)tmp.getHeight()/scale_factor, Image.SCALE_SMOOTH);
			img.put("back",  dimg);

		} catch (Exception e) {
			Game.display("ERROR: " + e);
		}
	}
	
	public static Image getCardImg(Card c) {
		Image img = null;
		img = CardPng.img.get(c.toString());
		return img;
	}
	
	public static Image getCardImg(String str) {
		Image img = null;
		img = CardPng.img.get(str);
		return img;
	}
	
	public static String toName(Card c) {
		String tmp = c.toString().replaceAll(" ", "_");
		tmp = tmp.toLowerCase();
		tmp = tmp + ".png";
		return tmp;
	}

	
}
