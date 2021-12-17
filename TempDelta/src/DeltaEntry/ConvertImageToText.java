package DeltaEntry;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ConvertImageToText {

	public static String JpgFileFolderPath = "";
	
	public static void main(String[] args) throws InterruptedException {
    	File folder = new File(ConvertImageToText.JpgFileFolderPath);
    	File[] listOfFiles = folder.listFiles();
    	for (File eachFile : listOfFiles) {
    		if (eachFile.getName().toLowerCase().endsWith(("_new.jpg"))) {
    			File imageFile = new File(eachFile.getPath());
    			//System.out.println("Pa Image 00 " + imageFile);
    			Tesseract instance = new Tesseract();
    			instance.setDatapath("C:\\Program Files (x86)\\Tesseract-OCR\\");
    			String result;
    			try {
    				result = instance.doOCR(imageFile);
    				String textFileName = imageFile.getPath().replace(".jpg", ".txt");
    				System.out.println("Changed to text : " + textFileName);
    				FileOutputStream out;
					try {
						out = new FileOutputStream(textFileName);
	    				try {
							out.write(result.replaceAll("(?m)^[ \t]*\r?\n", "").getBytes());
		    				out.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			} catch (TesseractException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
		}
	}

}
