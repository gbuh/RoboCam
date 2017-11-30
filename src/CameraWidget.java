import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

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
			cameraImg = img;
			File f = new File("C:\\Users\\Администратор\\eclipse-workspace\\Camera\\out.jpg");
            try {
				ImageIO.write((RenderedImage)img, "jpg", f);
			} catch (IOException e) {
				// TODO Auto-generated catch block
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