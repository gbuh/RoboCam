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
	private static final long serialVersionUID = 7104212723172514803L;
	private volatile Image cameraImg;
	private PixelColor pixelColor = new PixelColor();
	Robot robot = new Robot();

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
		if(cameraImg != null) //  
		{
			if(pixelColor.robotLaserSpot(cameraImg)) //  
			{
				g.drawImage( cameraImg, 0, 0, getWidth(), getHeight(), null );
				g.drawString( "I see a laser spot...", getWidth() / 2 - 50, getHeight() / 2 - 5 );
			} else
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
//			float matrix[] = {1, 1, 1, 1, 1, 1, 1, 1, 1};
//			ConvolveFilter filter = new ConvolveFilter(matrix);
//			filter.filter((BufferedImage)img, (BufferedImage)img);
			
//			PixelColor pixelColor = new PixelColor();
			//	pixelColor.getPixelColor(img, 0, 0);
			//	pixelColor.setPixelColor(img, 0, 0, 255, 255, 255, 255);
//			pixelColor.setNegativePicture(img);
//			pixelColor.saveImage(img);
//			pixelColor.saveImageFrame(img);
//			pixelColor.setGrayPicture(img);
//			pixelColor.setLaserImage(img);
//			pixelColor.setBlackWhitePicture(img);
//			pixelColor.robotLaserBlob(img);
//			pixelColor.videoCapture();	
			
//        	for (int i = 0; i < 1; i++) {
//			if (pixelColor.robotLaserBlob(img))
//			{
//				String hostname = "127.0.0.1:12080";
////				DrivelnCircle robotino = new DrivelnCircle();
//				
//			try
//				{
//					int counter = 0;
////					robot._com.connectToServer(false);
//					robot.connect(hostname, false);
//					while (counter < 1) {
//
//						robot.drive();
//
//						counter++;
//					}
//					robot.disconnect();
////					robot._com.connectToServer(true);
//				}
//				catch (Exception e)
//				{
//					System.out.println(e.toString());
//				}
//			}
//        	}
			cameraImg = img;
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