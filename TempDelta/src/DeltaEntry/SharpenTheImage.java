package DeltaEntry;

import java.io.File;
import java.io.IOException;

public class SharpenTheImage {

	public static String OrigImageFileFolderPath = "";
	
	public static void main(String[] args) throws InterruptedException {
		// absolute path to ImageMagick: Command-line Tools: Convert
		String convert_path = "C:\\Program Files\\ImageMagick-7.0.8-Q16\\magick";
		String size = "200%";
		String sharpen = "0x2";
    	File folder = new File(SharpenTheImage.OrigImageFileFolderPath);
    	File[] listOfImageFiles = folder.listFiles();
    	for (File eachFile : listOfImageFiles) {
    		if (eachFile.getName().toLowerCase().endsWith((".jpg"))) {
    			String old_img_path = eachFile.getPath();
    			String new_img_path = eachFile.getPath().replace(".jpg", "_new.jpg");
    			
    		    ProcessBuilder pb = new ProcessBuilder(
    		            convert_path,
    		            old_img_path,
    		            "-resize",
    		            size,
    		            "-sharpen", 
    		            sharpen,
    		            new_img_path);
    		    Thread.sleep(500);
    		    pb.redirectErrorStream(true);
    		    try {
    				Process p = pb.start();
    				Thread.sleep(500);
    				System.out.println(new_img_path + " -- success");
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}    			
    		}
    	}
	}	

}
