import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PixelColor {
	private int p;
	private int a;
	private int r;
	private int g;
	private int b;
	private int width;
	private int height;
	private int porog = 200;
	int light = 240;
	private boolean laserSpot = false;
	private BufferedImage buffImg;
	protected Robot robot;
///////////////////////////////////////////////////////////////////	

	public int getP() {return p;}

	public int getA() {return a;}

	public int getR() {return r;}

	public int getG() {return g;}

	public int getB() {return b;}
	
	public int getWidth() {return width;}
	
	public int getHeight() {return height;}
	
	public int getPorog() {return porog;}
	
	public boolean isLaserSpot() { return laserSpot; }
	
	public BufferedImage getBuffImg() {return buffImg;}

	public void setP(int p) {this.p = p;}

	public void setA(int a) {this.a = a;}

	public void setR(int r) {this.r = r;}

	public void setG(int g) {this.g = g;}

	public void setB(int b) {this.b = b;}
	
	public void setWidth(int width) {this.width = width;}
	
	public void setHeight(int height) {this.height = height;}
	
	public void setPorog(int porog) {this.porog = porog;}
	
	public void setLaserSpot(boolean laserSpot) { this.laserSpot = laserSpot;}
	
	public void setBuffImg(BufferedImage buffImg) {this.buffImg = buffImg;}

	public void getPixelColor(Image img, int x, int y) {
		buffImg = (BufferedImage)img;
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
	
	public void getPixelColorPrint(Image img, int x, int y) {
		buffImg = (BufferedImage)img;
		p = buffImg.getRGB(x, y);
		//get alpha
		a = (p>>24) & 0xff;
		//get red
		r = (p>>16) & 0xff;
		//get green
		g = (p>>8) & 0xff;
		//get blue
		b = p & 0xff;
		if (r > 205 && g > 165 && b > 175) {
		System.out.println("r= " + r + "\ng= "+ g + "\nb= " + b);
		}
	}
	
	public boolean getPixelColorLogic(Image img, int x, int y) {
		buffImg = (BufferedImage)img;
		p = buffImg.getRGB(x, y);
		//get alpha
		a = (p>>24) & 0xff;
		//get red
		r = (p>>16) & 0xff;
		//get green
		g = (p>>8) & 0xff;
		//get blue
		b = p & 0xff;
		if (r > 205 && g > 165 && b > 175) {
		return true;
		} else return false;
	}

	public void setPixelColor(Image img, int x, int y, int a, int r, int g, int b) {
		p = (a<<24) | (r<<16) | (g<<8) | b;
		buffImg = (BufferedImage)img;
		buffImg.setRGB(x, y, p);
	}
	
	public void setNegativePicture(Image img) {
		buffImg = (BufferedImage)img;
		//get image width and height
		width = buffImg.getWidth();
		height = buffImg.getHeight();
		//convert to negative
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				getPixelColor(img, x, y);
				//subtract RGB from 255
				r = 255 - r;
				g = 255 - g;
				b = 255 - b;
				//set new RGB value
				p = (a<<24) | (r<<16) | (g<<8) | b;
				setPixelColor(img, x, y, a, r, g, b); //buffImg.setRGB(x, y, p); 
			}
		}
	}
	
	public void setBlackWhitePicture(Image img) {
		buffImg = (BufferedImage)img;
		//get image width and height
		width = buffImg.getWidth();
		height = buffImg.getHeight();
//		porog = 200;
		//convert to blackWhite
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				getPixelColor(img, x, y);
				//subtract RGB from 255
				if (r > porog || g > porog || b > porog) {
				r = 255;
				g = 255;
				b = 255;
				p = (a<<24) | (r<<16) | (g<<8) | b;
				setPixelColor(img, x, y, a, r, g, b); //buffImg.setRGB(x, y, p);
				} else {
					r = 0;
					g = 0;
					b = 0;
					p = (a<<24) | (r<<16) | (g<<8) | b;
					setPixelColor(img, x, y, a, r, g, b); //buffImg.setRGB(x, y, p);
				}
			}
		}
	}
	
	public void setGrayPicture(Image img) {
		buffImg = (BufferedImage)img;
//		DataBuffer dataBuffer = null;
//		int i = dataBuffer.getNumBanks();
//		int j = dataBuffer.getDataType();
//		System.out.println("i= " + i + " j= " + j);
		
		//get image width and height
		int width = buffImg.getWidth();
		int height = buffImg.getHeight();
		//convert to negative 
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				getPixelColor(img, x, y);
				//subtract RGB from 255
				r = (int)(0.56 * r);
				g = (int)(0.33 * g);
				b = (int)(0.11 * b);
				//set new RGB value
				p = (a<<24) | (r<<16) | (g<<8) | b;
				setPixelColor(img, x, y, a, r, g, b); //buffImg.setRGB(x, y, p); 
			}
		}
	}
/*
	public void setLaserImage(Image img) {
//		float matrix[] = {-1, 1, -1, 1, 1, 1, -1, 1, -1}; // увеличение резкости
//		float matrix[] = {1, -1, 1, -1, -1, -1, 1, -1, 1}; // нахождение черных пятен
//		float matrix[] = {-0.05f, 0.05f, -0.05f, 0.05f, 0.05f, 0.05f, -0.05f, 0.05f, -0.05f}; // нахождение контуров
		float matrix[] = {-1, -1, -2, -1, -1,
						  -1, -1, -2, -1, -1,
						  -2, -2, 23, -2, -2,
						  -1, -1, -2, -1, -1,
						  -1, -1, -2, -1, -1,}; // нахождение лазерного пятна	
		convolveFilter = new ConvolveFilter(5, 5, matrix);
		buffImg = (BufferedImage)img;
		buffImg = convolveFilter.filter(buffImg, buffImg);
	}
*/	

	public boolean robotLaserSpot(Image img) {
		buffImg = (BufferedImage)img;
		//get image width and height
		int width = buffImg.getWidth();
		int height = buffImg.getHeight();
		// find red point
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				getPixelColor(img, x, y);
//				if (r > 200 && g > 120 && g < 150 && b > 120 && b < 150)
				if (r > 200 && g > 120 && g < 150 && b > 120 && b < 150) //(r > light && g > light && b > light)// for Robotino® SIM Demo: (r > 200 && g < 10 && b < 10) find red cylinder
					return true;
			}
		}
		return false;
	}
	
	public int getLaserSpot(Image img) {
		buffImg = (BufferedImage)img;
		//get image width and height
		int width = buffImg.getWidth();
		int height = buffImg.getHeight();
		// find red point
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				getPixelColor(img, x, y);
//				if (r > 200 && g > 120 && g < 150 && b > 120 && b < 150) find red laser spot
				if (r > 200 && g < 10 && b < 10)  {// for Robotino® SIM Demo: (r > 200 && g < 10 && b < 10) find red cylinder
					System.out.println("X =" + x + " Y =" + y);
					laserSpot = true;
					return x;
				}
			} laserSpot = false;
		} return 0;
	}
	
/*	
	public int getLaserSpot(Image img) {
		buffImg = (BufferedImage)img;
		//get image width and height
		int width = buffImg.getWidth();
		int height = buffImg.getHeight();
		// find red point
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				getPixelColor(img, x, y);
//				if (r > 200 && g > 120 && g < 150 && b > 120 && b < 150)
				if (r > 200 && g > 120 && g < 150 && b > 120 && b < 150)  {// (r > light && g > light && b > light)// for Robotino® SIM Demo: (r > 200 && g < 10 && b < 10) find red cylinder
					System.out.println("X =" + x + " Y =" + y);
					return x;
				}
			}
		} return 0;
	}
*/
	public void setLagrange(Image img) {
		buffImg = (BufferedImage)img;
		int width = buffImg.getWidth();
		int height = buffImg.getHeight();
		int x1[] = new int[width];
		int y1[] = new int[height];
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				x1[x] = x;
				y1[y] = y;
			}
		}
		lagrange(buffImg, x1, y1, true);
	}
	
	public void lagrange(BufferedImage bi, int x[], int y[], boolean mode) {
		int k = x[0];
		int start = y[0];
		int end;
		while (k <= (y.length - 1)) { // while (k <= x[3]) {
			double r = 0;
			for (int l = 0; l < y.length; l++) { // for (int l = 0; l < 4; l++) {
				double buf = 1;
				for (int m = 0; m < y.length; m++) { // for (int m = 0; m < 4; m++) {
					if (l != m && x[l] != x[m]) {
						buf *= ((double) k - x[m]) / ((double) x[l] - x[m]);
					}
				}
				r += buf * y[l];
			}
			if (start > (int) r) {
				end = start;
				start = (int) r;
			} else {
				end = (int) r;
			}
			if (start < 0) {
				start = (y.length - 1); // start = 3;
			}
			if (mode) {
				while (start <= end) {
					bi.setRGB(start++, k, -1);
				}
			} else {
				while (start <= end) {
					bi.setRGB(k, start++, -1);
				}
			}
			start = (int) r;
			k++;
		}
	}

	
	public void saveImage(Image img) {
		int index = 10;
			try {
				File file = new File("out" + index + ".jpg"); //C:\\Users\\Администратор\\eclipse-workspace\\Camera\\
				ImageIO.write((RenderedImage)img, "jpg", file);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public void saveImageFrame(Image img) {
		int index = 1;
//			while(true) {
			try {
				File file = new File("out" + (index++) + ".jpg"); //C:\\Users\\Администратор\\eclipse-workspace\\Camera\\
				ImageIO.write((BufferedImage)img, "jpg", file);
			} catch (IOException e) {
				e.printStackTrace();
			}
//		}
	}
}
