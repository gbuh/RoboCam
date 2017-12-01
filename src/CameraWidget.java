import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * This widget listens for camera events and displays incoming images.
 */
public class CameraWidget extends JComponent
{
	private volatile Image cameraImg;

	public CameraWidget(Robot robot)
	{
		robot.addListener( new RobotListenerImpl() );

		setMinimumSize( new Dimension(32, 24) );
		setPreferredSize( new Dimension(320, 240) );
		setMaximumSize( new Dimension(Short.MAX_VALUE, Short.MAX_VALUE) );

		setDoubleBuffered( true );
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		if(cameraImg != null)
		{
			g.drawImage( cameraImg, 0, 0, getWidth(), getHeight(), null );
		}
		else
		{
			g.setColor( Color.BLACK );
			g.fillRect( 0, 0, getWidth(), getHeight() );
			g.setColor( Color.WHITE );
			g.drawString( "No camera image", getWidth() / 2 - 50, getHeight() / 2 - 5 );
		}
	}

	private class RobotListenerImpl implements RobotListener
	{
		@Override
		public void onImageReceived(Image img)
		{
//			BufferedImage bimg = (BufferedImage)img;
//			bimg.setRGB(101, 101, 0);
//			bimg.setRGB(102, 102, 0);
//			bimg.setRGB(103, 103, 0);
//			img = bimg;
//		    int width = ((BufferedImage)img).getWidth();
//		    int height = ((BufferedImage)img).getHeight();
		  //get pixel value
/*			
			BufferedImage img1 = (BufferedImage)img; 
		    int p = img1.getRGB(0,0);
		    //get alpha
		    int a = (p>>24) & 0xff;
		    //get red
		    int r = (p>>16) & 0xff;
		    //get green
		    int g = (p>>8) & 0xff;
		    //get blue
		    int b = p & 0xff;
		    a = 255;
		    r = 255;
		    g = 255;
		    b = 255;

		    //set the pixel value
		    p = (a<<24) | (r<<16) | (g<<8) | b;
		    img1.setRGB(0, 0, p);

			cameraImg = img1;
*/
			
			try {
				PixelColor pixelColor = new PixelColor();
				pixelColor.getPixelColor(img, 0, 0);
//				pixelColor.setPixelColor(img, 0, 0, 255, 255, 255, 255);
				pixelColor.setNegativePicture(img);
				cameraImg = img;
					int i = 1;					
					File file = new File("out" + i + ".jpg"); //C:\\Users\\Администратор\\eclipse-workspace\\Camera\\
					ImageIO.write((RenderedImage)cameraImg, "jpg", file); // img
					
			} catch (IOException e) {
				e.printStackTrace();
			}
			repaint();
		}	

		@Override
		public void onConnected()
		{
		}

		@Override
		public void onDisconnected()
		{
		}

		@Override
		public void onError(String error)
		{
		}

		@Override
		public void onOdometryReceived(double x, double y, double phi)
		{			
		}
	}
}