
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;  

import org.opencv.core.Core;  
import org.opencv.core.CvType;  
import org.opencv.core.Mat;  
import org.opencv.core.MatOfPoint;  
import org.opencv.core.Rect;  
import org.opencv.core.Scalar;  
import org.opencv.core.Size;  
import org.opencv.imgcodecs.Imgcodecs;  
import org.opencv.imgproc.Imgproc;  
import org.opencv.objdetect.CascadeClassifier;  
import org.opencv.videoio.VideoCapture;  
import org.opencv.videoio.Videoio;  

public class Main {  
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");  

	*//** 
	 * @param args 
	 *//*  
	public static void main(String[] args) {  
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);      
		VideoCapture camera = new VideoCapture(0);      
		camera.set(Videoio.CV_CAP_PROP_FRAME_WIDTH, 1280);      
		camera.set(Videoio.CV_CAP_PROP_FRAME_HEIGHT, 720);      

		if(!camera.isOpened()){      
			System.out.println("Error");      
		}      
		else {      
			int sensivity = 30;    
			double maxArea = 30;    
			int index = 0;      
			Mat frame = new Mat(720, 1280, CvType.CV_8UC3);      
			Mat frame_current = new Mat(720, 1280, CvType.CV_8UC3);      
			Mat frame_previous = new Mat(720, 1280, CvType.CV_8UC3);      
			Mat frame_result = new Mat(720, 1280, CvType.CV_8UC3);      
			Size size = new Size(3, 3);  
			Mat v = new Mat();    
			Scalar scalar1 = new Scalar(0, 0, 255);  
			Scalar scalar2 = new Scalar(0, 255, 0);  

			while(true){      
				if (camera.read(frame)){      
					frame.copyTo(frame_current);    

					Imgproc.GaussianBlur(frame_current, frame_current, size, 0);    

					if (index > 1) {    
						Core.subtract(frame_previous, frame_current, frame_result);    

						Imgproc.cvtColor(frame_result, frame_result, Imgproc.COLOR_RGB2GRAY);    

						Imgproc.threshold(frame_result, frame_result, sensivity, 255, Imgproc.THRESH_BINARY);        

						List<MatOfPoint>contours = new ArrayList<MatOfPoint>();    
						Imgproc.findContours(frame_result, contours, v, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);    
						v.release();    


						boolean found = false;    
						for(int idx = 0; idx < contours.size(); idx++) {                              
							Mat contour = contours.get(idx);                              
							double contourarea = Imgproc.contourArea(contour);                            
							if(contourarea > maxArea) {    
								found = true;    

								Rect r = Imgproc.boundingRect(contours.get(idx));    
								Imgproc.drawContours(frame, contours, idx, scalar1);    
								Imgproc.rectangle(frame, r.br(), r.tl(), scalar2, 1);    
							}    
							contour.release();  
						}  

						if (found) {    
							System.out.println("Moved");    
							Imgcodecs.imwrite("new" + (sdf.format(new Date())) + ".jpg", frame);      
						}    
					}    

					index++;    

					frame_current.copyTo(frame_previous);   
					frame.release();  
					frame_result.release();  
					frame_current.release();  

					try {    
						Thread.currentThread().sleep(500);    
					} catch (InterruptedException e) {}    
				}      
			}         
		}      
		camera.release();    
	}  
}
*/
/*
import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.IOException;  

import javax.imageio.ImageIO;  

import org.opencv.core.Core;  
import org.opencv.core.Mat;  
import org.opencv.imgcodecs.Imgcodecs;  

public class Main {  
	*//** 
	 * @param args 
	 * @throws IOException  
	 *//*
	public static void main(String[] args) throws IOException {  
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);    

		ImageIO.write(convertMatToBufferedImage(Imgcodecs.imread("out6.png")), "png", new File("test1.png"));  
	}  

	private static BufferedImage convertMatToBufferedImage(Mat mat) {  
		byte[] data = new byte[mat.width() * mat.height() * (int)mat.elemSize()];  
		int type;  
		mat.get(0, 0, data);  

		switch (mat.channels()) {    
		case 1:    
			type = BufferedImage.TYPE_BYTE_GRAY;    
			break;    
		case 3:    
			type = BufferedImage.TYPE_3BYTE_BGR;    
			// bgr to rgb    
			byte b;    
			for(int i=0; i<data.length; i=i+3) {    
				b = data[i];    
				data[i] = data[i+2];    
				data[i+2] = b;    
			}    
			break;    
		default:    
			throw new IllegalStateException("Unsupported number of channels");  
		}    

		BufferedImage out = new BufferedImage(mat.width(), mat.height(), type);  

		out.getRaster().setDataElements(0, 0, mat.width(), mat.height(), data);  

		return out;  
	}  
}
*/