import java.awt.Image;
import java.awt.image.BufferedImage;

public class PixelColor {
	private int p;
	private int a;
	private int r;
	private int g;
	private int b;

	public int getP() {
		return p;
	}

	public int getA() {
		return a;
	}

	public int getR() {
		return r;
	}

	public int getG() {
		return g;
	}

	public int getB() {
		return b;
	}

	public void getPixelColor(Image img, int x, int y) {
		BufferedImage buffImg = (BufferedImage)img;
		p = buffImg.getRGB(x, y);
		//get alpha
		a = (p>>24) & 0xff;
		//get red
		r = (p>>16) & 0xff;
		//get green
		g = (p>>8) & 0xff;
		//get blue
		b = p & 0xff;
	}

	public void setPixelColor(Image img, int x, int y, int a, int r, int g, int b) {
		p = (a<<24) | (r<<16) | (g<<8) | b;
		BufferedImage buffImg = (BufferedImage)img;
		buffImg.setRGB(x, y, p);
	}
	public void setNegativePicture(Image img) {
		BufferedImage buffImg = (BufferedImage)img;
		//get image width and height
		int width = buffImg.getWidth();
		int height = buffImg.getHeight();
		//convert to negative
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				getPixelColor(img, x, y);
//				int p = buffImg.getRGB(x,y);
//				int a = (p>>24)&0xff;
//				int r = (p>>16)&0xff;
//				int g = (p>>8)&0xff;
//				int b = p&0xff;
				//subtract RGB from 255
				r = 255 - r;
				g = 255 - g;
				b = 255 - b;
				//set new RGB value
				p = (a<<24) | (r<<16) | (g<<8) | b;
				buffImg.setRGB(x, y, p);
			}
		}
	}
}
