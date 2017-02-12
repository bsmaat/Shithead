import java.util.List;
import java.util.Map;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class CardPng {
	
	
	static Map<String, BufferedImage> img = new HashMap<String, BufferedImage>();
	
	public static void init() {
		int index = 0;
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
				img.put(c.toString(), tmp);
				
				index++;
				
			}
		}
	}
	
	public static BufferedImage getCardImg(Card c) {
		BufferedImage img = null;
		img = CardPng.img.get(c.toString());
		return img;
	}
	
	public static String toName(Card c) {
		String tmp = c.toString().replaceAll(" ", "_");
		tmp = tmp.toLowerCase();
		tmp = tmp + ".png";
		return tmp;
	}

	
}
