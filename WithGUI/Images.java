package bonusassignment;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;



public class Images implements Runnable {

	public static BufferedImage DOGE_IMAGE;
	private static final String IMAGE_PATH = "Images/rsz_2erable.jpg";
	
	public void initImages() throws FileNotFoundException, IOException{
		
		BufferedImage imageSrc = ImageIO.read(
				new FileInputStream(IMAGE_PATH)
			);
		
		DOGE_IMAGE = toBufferedImage(imageSrc.getScaledInstance(
				(int) (imageSrc.getWidth()),
				(int) (imageSrc.getHeight()),
				Image.SCALE_DEFAULT));
		
	}
	
	
	public static BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
	
	@Override
	public void run() {
		try {
			initImages();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

}
