
import javax.swing.UIManager;

public class Main
{	
	public static void main(String[] args)
	{
		//Try to use the Look & Feel of the native Operating System
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) { }

		MainFrame frame = new MainFrame(new Robot());
		frame.setTitle( "Robotino Examples - GUI" );

		System.out.println("Welcome to the GUI example.");
	}
}

/*
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.UIManager;

public class Main
{	
	public static void main(String[] args)
	{
		try
		{
			PixelColor pixelColor = new PixelColor();
			for (int i = 1; i <= 16; i++) {
				File inFile = new File("./fotoLaser/laser" + i + ".png");
				BufferedImage buffImg = ImageIO.read(inFile);
				File outFile = new File("./laserOut/lsr" + i + ".png");
//				pixelColor.setLaserImage(buffImg);
				pixelColor.setBlackWhitePicture(buffImg);
//				pixelColor.setBoxBlur(buffImg);
				ImageIO.write(buffImg, "png", outFile);
				pixelColor.getLaserBlob(buffImg);
				System.out.println("Image set Convolve Filter");
			
//			float matrix[] = {-1, 1, -1, 1, 1, 1, -1, 1, -1}; // увеличение резкости
//			float matrix[] = {1, -1, 1, -1, -1, -1, 1, -1, 1}; // нахождение черных пятен
//			float matrix[] = {-0.05f, 0.05f, -0.05f, 0.05f, 0.05f, 0.05f, -0.05f, 0.05f, -0.05f}; // нахождение контуров
//			float matrix[] = {-1, -3, -1, -3, 14, -3, -1, -3, -1}; // нахождение лазерного пятна
//			float matrix[] = {-1, -1, -2, -1, -1,
//							  -1, -1, -2, -1, -1,
//							  -2, -2, 23, -2, -2,
//							  -1, -1, -2, -1, -1,
//							  -1, -1, -2, -1, -1,}; // нахождение лазерного пятна	
//			ConvolveFilter convolveFilter = new ConvolveFilter(5, 5, matrix);
//			BoxBlurFilter boxBlurFilter = new BoxBlurFilter();
//			PixelColor pixelColor = new PixelColor();
//			for (int i = 1; i <= 12; i++) {
//				File inFile = new File("./fotoLaser/lazer" + i + ".png");
//				BufferedImage buffImgIn = ImageIO.read(inFile);
//				Image img = buffImgIn;
//				pixelColor.setLagrange(buffImgIn);
//				File outFile = new File("./laserOut/laser" + i + ".png");
//				BufferedImage buffImgOut = null;
//				BufferedImage buffImgOut = convolveFilter.createCompatibleDestImage(buffImgIn, null);
//				int[] in = new int[buffImgIn.getWidth()*buffImgIn.getHeight()];
//				int[] out = in;
//				boxBlurFilter.blur(in,out,buffImgIn.getWidth(), buffImgIn.getHeight(), 20);
//				buffImgOut = convolveFilter.filter(buffImgIn, buffImgOut);
//				ImageIO.write(buffImgOut, "png", outFile);
//				
//				System.out.println("Image set Convolve Filter");
//
//			}
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
*/
/*
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main
{	
	public static void main(String[] args)
	{
		Image cameraImg = null;
		PixelColor pixelColor = new PixelColor();
		File inFile = new File("./fotoLaser/Laser3.png");
		int x = 0;
		int y = 0;
		for (x = 0; x < 320; x++) {
			for (y = 0; y < 240; y++) {
				try {
					cameraImg = ImageIO.read(inFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (pixelColor.getPixelColorLogic(cameraImg, x, y)) {
				System.out.println("x= " + x + "; y= " + y);
				pixelColor.getPixelColorPrint(cameraImg, x, y);
				}
			}
		}
	}
}
*/